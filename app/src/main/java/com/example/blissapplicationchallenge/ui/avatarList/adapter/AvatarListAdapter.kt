package com.example.blissapplicationchallenge.ui.avatarList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.ItemListBinding
import com.example.blissapplicationchallenge.network.model.AvatarModel

class AvatarListAdapter(
    private val list: MutableList<AvatarModel>,
    private val context: Context,
    private val listener: IAvatarAdapterListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AvatarListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is AvatarListViewHolder -> {
                holder.bind(list[position], context)
            }
        }

        holder.itemView.setOnClickListener {
            this.listener.onDeleteAvatar(this.list[position])
            val newPosition: Int = holder.adapterPosition
            list.removeAt(newPosition)
            notifyItemRemoved(newPosition)
            notifyItemRangeChanged(newPosition, list.size)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

}

class AvatarListViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = ItemListBinding.bind(itemView)

    fun bind(avatarModel: AvatarModel, context: Context) {
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .load(avatarModel.avatarUrl)
            .into(binding.itemImage)
    }

}