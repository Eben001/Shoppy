package com.ebenezer.gana.shoppy.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebenezer.gana.shoppy.databinding.ListItemProductBinding
import com.ebenezer.gana.shoppy.models.SoldProduct
import com.ebenezer.gana.shoppy.ui.activities.SoldProductsDetailsActivity
import com.ebenezer.gana.shoppy.ui.fragments.SoldProductsFragment
import com.ebenezer.gana.shoppy.utils.Constants
import com.ebenezer.gana.shoppy.utils.GlideLoader

class SoldProductListAdapter(
    private val context: Context,
    private val soldProductList: ArrayList<SoldProduct>,
    private val fragment: SoldProductsFragment
) : RecyclerView.Adapter<SoldProductListAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var soldProduct: SoldProduct

        fun bind(soldProduct: SoldProduct) {
            this.soldProduct = soldProduct
            GlideLoader(context).loadProductPicture(
                soldProduct.image,
                binding.ivItemImage
            )

            binding.tvItemName.text = soldProduct.title
            binding.tvItemPrice.text =
                "₦${soldProduct.total_amount}"
            binding.ibDeleteProduct.visibility =
                View.VISIBLE



            binding.ibDeleteProduct.setOnClickListener {
                fragment.deleteASoldProduct(soldProduct.id)
            }

            itemView.setOnClickListener {
                val intent = Intent(context, SoldProductsDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_SOLD_PRODUCTS_DETAILS, soldProduct)

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
        holder.bind(soldProductList[position])
    }

    override fun getItemCount() = soldProductList.size
}