package com.example.blissapplicationchallenge.ui.avatarList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.ActivityAvatarListBinding
import com.example.blissapplicationchallenge.network.model.AvatarModel
import com.example.blissapplicationchallenge.network.resource.Status
import com.example.blissapplicationchallenge.ui.avatarList.adapter.AvatarListAdapter
import com.example.blissapplicationchallenge.ui.avatarList.adapter.IAvatarAdapterListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvatarListActivity : AppCompatActivity(), IAvatarAdapterListener {

    private lateinit var binding: ActivityAvatarListBinding
    private lateinit var viewModel: AvatarListViewModel
    private lateinit var adapter: AvatarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_avatar_list)
        this.viewModel = ViewModelProvider(this)[AvatarListViewModel::class.java]
        getAvatarList()
    }

    private fun getAvatarList() {
        this.viewModel.getAvatarList().observe(this, {
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

    override fun onDeleteAvatar(avatar: AvatarModel) {
        this.viewModel.deleteAvatar(avatar).observe(this, {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                    }
                    Status.ERROR -> {
                        Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
                        return@observe
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "LOADING", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun setupRv(data: List<AvatarModel>) {
        this.binding.rvAvatar.layoutManager = GridLayoutManager(this, 4)
        this.adapter = AvatarListAdapter(data as MutableList<AvatarModel>, this, this)
        this.binding.rvAvatar.adapter = this.adapter
    }

}