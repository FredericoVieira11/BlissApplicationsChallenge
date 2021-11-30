package com.example.blissapplicationchallenge.ui.googleRepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                        hideProgressBar()
                        setupRv(it.data!!)
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        Toast.makeText(this, this.getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
                        showProgressBar()
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

    private fun showProgressBar() {
        this.binding.progressBarGoogleRepos.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        this.binding.progressBarGoogleRepos.visibility = View.GONE
    }


}