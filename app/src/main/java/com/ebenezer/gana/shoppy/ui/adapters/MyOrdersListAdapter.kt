package com.ebenezer.gana.shoppy.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ebenezer.gana.shoppy.databinding.ListItemProductBinding
import com.ebenezer.gana.shoppy.models.Order
import com.ebenezer.gana.shoppy.ui.activities.MyOrderDetailsActivity
import com.ebenezer.gana.shoppy.ui.fragments.OrdersFragment
import com.ebenezer.gana.shoppy.utils.Constants
import com.ebenezer.gana.shoppy.utils.GlideLoader

class MyOrdersListAdapter(
    private val context: Context,
    private val ordersList: ArrayList<Order>,
    private val fragment: OrdersFragment,

    ) :
    RecyclerView.Adapter<MyOrdersListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ListItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var orders: Order
        fun bind(orders: Order) {
            this.orders = orders

            GlideLoader(context).loadProductPicture(
                orders.image,
                binding.ivItemImage
            )

            binding.tvItemName.text = orders.title
            binding.tvItemPrice.text = "₦${orders.total_amount}"
            binding.ibDeleteProduct.visibility = View.VISIBLE

            binding.ibDeleteProduct.setOnClickListener {
                fragment.deleteAllOrders(orders.id)
            }


            itemView.setOnClickListener {
                val intent = Intent(context, MyOrderDetailsActivity::class.java)
                intent.putExtra(Constants.EXTRA_MY_ORDER_DETAILS, orders)
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
        holder.bind(ordersList[position])

    }

    override fun getItemCount() = ordersList.size
}