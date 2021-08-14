package br.com.common.login.ui.adapter.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.common.login.R
import br.com.promotion.model.domain.User

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    var users = emptyList<User>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_user_item, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
//        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size
}