package com.example.blissapplicationchallenge.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
        val avatarField = this.binding.editTxtAvatar.text
        if (avatarField.isNullOrBlank()) {
            return
        }
        getAvatar(avatarField.toString())
    }

    private fun getRandomEmoji() {
        this.viewModel.getEmojis().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        setupGlide(it.data?.get(randomInt(it.data.size))?.url)
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

    private fun getAvatar(avatar: String) {
        this.viewModel.getAvatar(avatar).observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        setupGlide(it.data?.avatarUrl)
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

    private fun showProgressBar() {
        this.binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        this.binding.progressBar.visibility = View.GONE
    }

    private fun setupGlide(url: String?) {
        Glide.with(this)
            .load(url)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideProgressBar()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideProgressBar()
                    return false
                }
            })
            .error(R.drawable.ic_launcher_background)
            .into(this.binding.imageView)
    }

}