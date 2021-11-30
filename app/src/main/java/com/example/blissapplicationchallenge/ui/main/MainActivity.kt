package com.example.blissapplicationchallenge.ui.main

import android.content.Intent
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
import com.example.blissapplicationchallenge.ui.avatarList.AvatarListActivity
import com.example.blissapplicationchallenge.ui.emojiList.EmojiListActivity
import com.example.blissapplicationchallenge.ui.googleRepos.GoogleReposActivity
import com.example.blissapplicationchallenge.utils.randomInt
import dagger.hilt.android.AndroidEntryPoint

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

    fun navToEmojiListActivity() {
        val intent = Intent(this, EmojiListActivity::class.java)
        startActivity(intent)
    }

    fun navToAvatarListActivity() {
        val intent = Intent(this, AvatarListActivity::class.java)
        startActivity(intent)
    }

    fun navToGoogleReposActivity() {
        val intent = Intent(this, GoogleReposActivity::class.java)
        startActivity(intent)
    }

    fun onSearchAvatar() {
        if (this.binding.editTextTextPersonName.text.isNullOrBlank()) {
            return
        }

        getAvatar(this.binding.editTextTextPersonName.text.toString())
    }

    private fun getRandomEmoji() {
        viewModel.getEmojis().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        Glide.with(this)
                            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                            .load(it.data?.get(randomInt(it.data.size))?.url)
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

    private fun getAvatar(avatar: String) {
        this.viewModel.getAvatar(avatar).observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        Glide.with(this)
                            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
                            .load(it.data?.avatarUrl)
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