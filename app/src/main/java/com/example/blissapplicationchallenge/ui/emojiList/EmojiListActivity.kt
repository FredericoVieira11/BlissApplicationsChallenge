package com.example.blissapplicationchallenge.ui.emojiList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.ActivityEmojiListBinding
import com.example.blissapplicationchallenge.network.model.EmojiModel
import com.example.blissapplicationchallenge.network.resource.Status
import com.example.blissapplicationchallenge.ui.emojiList.adapter.EmojiListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmojiListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmojiListBinding
    private lateinit var viewModel: EmojiListViewModel
    private lateinit var adapter: EmojiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_emoji_list)
        this.viewModel = ViewModelProvider(this)[EmojiListViewModel::class.java]
        getEmojis()
        this.binding.swipeToRefresh.setOnRefreshListener {
            this.viewModel.setIsToReloadData(true)
            getEmojis()
            this.binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun getEmojis() {
        this.viewModel.getEmojis().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        hideProgressBar()
                        if (this.viewModel.getIsToReloadData()) {
                            this.adapter.clearData()
                            this.viewModel.setIsToReloadData(false)
                            it.data?.let { data ->
                                setupRv(data)
                            }
                        } else {
                            it.data?.let { data ->
                                setupRv(data)
                            }
                        }
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        Toast.makeText(this, this.getString(R.string.error_message), Toast.LENGTH_SHORT).show()                    }
                    Status.LOADING -> {
                        showProgressBar()
                    }
                }
            }
        })
    }

    private fun setupRv(data: List<EmojiModel>) {
        this.binding.rvEmoji.layoutManager = GridLayoutManager(this, 4)
        this.adapter = EmojiListAdapter(data as MutableList<EmojiModel>, this)
        this.binding.rvEmoji.adapter = this.adapter
    }

    private fun showProgressBar() {
        this.binding.progressBarEmojiList.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        this.binding.progressBarEmojiList.visibility = View.GONE
    }

}