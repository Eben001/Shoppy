package com.ebenezer.gana.shoppy.models

import android.os.Parcelable
import android.widget.ImageButton
import kotlinx.parcelize.Parcelize

@Parcelize
data class Order(
    val user_id:String = "",
    val items:ArrayList<CartItem> = ArrayList(),
    val address: Address = Address(),
    val title:String = "",
    val image:String = "",
    val sub_total_amount:String = "",
    val shipping_charge:String = "",
    val total_amount:String = "",
    val order_datetime:Long = 0L,
    var id:String = "",
):Parcelable