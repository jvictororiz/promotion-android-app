package com.promotion.android.login.ui.adapter.users

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.promotion.android.databinding.ListUserItemBinding
import com.promotion.android.login.domain.model.User


class UserListItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val binding = ListUserItemBinding.bind(itemView)

    fun bind(user: User) {
        binding.user = user
        binding.progressBar.isVisible = true
        Glide.with(binding.root.context)
            .load(user.img)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.isVisible = false
                    return false
                }

            })
            .into(binding.picture)
    }
}