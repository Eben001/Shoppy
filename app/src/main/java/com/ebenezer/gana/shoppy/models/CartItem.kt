package com.ebenezer.gana.shoppy.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val user_id: String = "",
    val product_owner_id: String = "",
    val product_id: String = "",
    val title: String = "",
    val price: String = "",
    val image: String = "",
    var cart_quantity: String = "",
    var stock_quantity: String = "",
    var id: String = "",
    val product_shipping_charge:String = ""
) : Parcelable