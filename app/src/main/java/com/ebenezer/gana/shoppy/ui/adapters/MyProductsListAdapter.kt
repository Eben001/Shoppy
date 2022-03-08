package com.ebenezer.gana.shoppy.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ebenezer.gana.shoppy.R
import com.ebenezer.gana.shoppy.databinding.ListItemProductBinding
import com.ebenezer.gana.shoppy.models.Products
import com.ebenezer.gana.shoppy.ui.activities.ProductDetailsActivity
import com.ebenezer.gana.shoppy.ui.fragments.ProductsFragment
import com.ebenezer.gana.shoppy.utils.Constants
import com.ebenezer.gana.shoppy.utils.GlideLoader

class MyProductsListAdapter(
    private val context: Context,
    private var list: ArrayList<Products>,
    private val fragment: ProductsFragment
) : RecyclerView.Adapter<MyProductsListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var products: Products

        fun bind(products: Products) {
            this.products = products

            GlideLoader(context).loadProductPicture(
                products.image,
                binding.ivItemImage
            )
            binding.tvItemName.text = products.title
            binding.tvItemPrice.text = "₦${products.price}"
            binding.tvItemDescription.text =
                products.description

            binding.ibDeleteProduct.setOnClickListener {

                fragment.deleteProduct(products.product_id)
            }
            itemView.setOnClickListener {
                val intent = Intent(context, ProductDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_PRODUCT_ID, products.product_id)
                intent.putExtra(Constants.EXTRA_PRODUCT_OWNER_ID, products.user_id)

                context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemProductBinding.inflate(
            LayoutInflater.from(context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size


}