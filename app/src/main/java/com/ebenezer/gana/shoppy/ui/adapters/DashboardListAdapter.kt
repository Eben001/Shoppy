package com.ebenezer.gana.shoppy.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ebenezer.gana.shoppy.R
import com.ebenezer.gana.shoppy.databinding.ItemDashboardLayoutBinding
import com.ebenezer.gana.shoppy.models.Products
import com.ebenezer.gana.shoppy.ui.activities.ProductDetailsActivity
import com.ebenezer.gana.shoppy.utils.Constants
import com.ebenezer.gana.shoppy.utils.GlideLoader

class DashboardListAdapter(
    private val context: Context,
    private var allProducts: ArrayList<Products>
):ListAdapter<Products,
        DashboardListAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(val binding: ItemDashboardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var products: Products


        fun bind(products: Products) {
            this.products = products


            GlideLoader(context).loadProductPicture(
                products.image,
                binding.ivDashboardItemImage
            )

            binding.tvDashboardItemTitle.text = products.title
            binding.tvDashboardItemPrice.text =
                "₦${products.price}"
            binding.tvDashboardItemDescription.text =
                products.description

            itemView.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_ID, products.product_id)

                intent.putExtra(
                    Constants.EXTRA_PRODUCT_OWNER_ID,
                    products.user_id
                ) // the id of the user who uploaded the products
                context.startActivity(intent)

            }

           // animateView(itemView)
        }

        private fun animateView(viewToAnimate: View) {
            if (viewToAnimate.animation == null) {
                val animation = AnimationUtils.loadAnimation(
                    viewToAnimate.context, R.anim.scale_xy
                )
                viewToAnimate.animation = animation
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDashboardLayoutBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )

        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allProducts[position])
    }


}

class DiffCallback:DiffUtil.ItemCallback<Products>(){
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem == newItem
    }
}

