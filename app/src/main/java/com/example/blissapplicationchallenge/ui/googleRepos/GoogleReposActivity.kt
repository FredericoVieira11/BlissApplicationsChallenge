package com.example.blissapplicationchallenge.ui.googleRepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.ActivityGoogleReposBinding
import com.example.blissapplicationchallenge.network.model.GoogleRepoModel
import com.example.blissapplicationchallenge.network.resource.Status
import com.example.blissapplicationchallenge.ui.googleRepos.adapter.GoogleReposAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoogleReposActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoogleReposBinding
    private lateinit var viewModel: GoogleReposViewModel
    private lateinit var adapter: GoogleReposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_google_repos)
        this.viewModel = ViewModelProvider(this)[GoogleReposViewModel::class.java]
        getGoogleRepos()
    }

    private fun getGoogleRepos() {
        this.viewModel.getGoogleRepos().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        setupRv(it.data!!)
                        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRv(data: List<GoogleRepoModel>) {
        this.binding.rvGoogleRepos.layoutManager = LinearLayoutManager(this)
        this.adapter = GoogleReposAdapter(data)
        this.binding.rvGoogleRepos.adapter = this.adapter
    }

}