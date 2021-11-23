package com.example.blissapplicationchallenge.ui.emojiList.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.blissapplicationchallenge.R
import com.example.blissapplicationchallenge.databinding.EmojiItemBinding
import com.example.blissapplicationchallenge.network.model.EmojiModel

class EmojiListAdapter(
    private val list: MutableList<EmojiModel>,
    private val context: Context
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EmojisListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.emoji_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is EmojisListViewHolder -> {
                holder.bind(list[position], context)
            }
        }

        holder.itemView.setOnClickListener {
            val newPosition: Int = holder.adapterPosition
            list.removeAt(newPosition)
            notifyItemRemoved(newPosition)
            notifyItemRangeChanged(newPosition, list.size)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class EmojisListViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = EmojiItemBinding.bind(itemView)

    fun bind(emojiModel: EmojiModel, context: Context) {
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .load(emojiModel.url)
            .into(binding.emojiItem)

    }
}