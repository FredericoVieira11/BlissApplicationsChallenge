package com.example.blissapplicationchallenge.ui.emojiList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            //TODO: refreshing data
        }
    }

    private fun getEmojis() {
        this.viewModel.getEmojis().observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        this.binding.rvEmoji.layoutManager = GridLayoutManager(this, 4)
                        this.adapter = EmojiListAdapter(it.data as MutableList<EmojiModel>, this)
                        this.binding.rvEmoji.adapter = this.adapter
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