package br.com.promotion.login.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.promotion.login.R
import br.com.promotion.login.databinding.ItemPromotionBinding
import br.com.promotion.model.domain.Promotion
import com.bumptech.glide.Glide

class PromotionAdapter(
    private val promotionList: List<Promotion>,
    private val context: Context
) : RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return PromotionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PromotionViewHolder, position: Int) {
        holder.bind(promotionList[position])
    }

    override fun getItemCount(): Int = promotionList.size


    inner class PromotionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPromotionBinding.bind(itemView)

        fun bind(promotion: Promotion) {
            with(binding) {
                nameEmployer.text = promotion.nameEmployee
                addresEmployer.text = promotion.address
                promotionEmployer.text = promotion.discount.toString()
                promotion.image.let {
                    Glide.with(context)
                        .load(promotion.image)
                        .centerCrop()
                        .into(binding.imageEmployer)
                }
            }
        }
    }
}