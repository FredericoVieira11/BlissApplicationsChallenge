package com.example.blissapplicationchallenge.ui.googleRepos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.GoogleItemListBinding
import com.example.blissapplicationchallenge.network.model.GoogleRepoModel

class GoogleReposAdapter(
    private val list: List<GoogleRepoModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GoogleReposViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.google_item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GoogleReposViewHolder).bind(this.list[position])
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}

class GoogleReposViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = GoogleItemListBinding.bind(itemView)

    fun bind(googleRepoModel: GoogleRepoModel) {
        this.binding.googleRepoItem.text = googleRepoModel.name
    }

}