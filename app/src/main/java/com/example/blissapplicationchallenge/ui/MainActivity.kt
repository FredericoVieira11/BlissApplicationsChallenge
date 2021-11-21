package com.example.blissapplicationchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.ActivityMainBinding
import com.example.blissapplicationchallenge.network.resource.Status
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.binding.function = this
        this.viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        getRandomEmoji()
    }

    fun onGetRandomEmoji() {
        getRandomEmoji()
    }

    private fun getRandomEmoji() {
        viewModel.getEmojis().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        val rand = Random()
                        val index = rand.nextInt(it.data!!.size - 1)
                        Glide.with(this)
                            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                            .load(it.data[index].url)
                            .into(binding.imageView)
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

}