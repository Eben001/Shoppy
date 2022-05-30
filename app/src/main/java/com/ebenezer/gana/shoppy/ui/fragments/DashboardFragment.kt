package com.ebenezer.gana.shoppy.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.SyncStateContract
import android.view.*
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebenezer.gana.shoppy.R
import com.ebenezer.gana.shoppy.databinding.FragmentDashboardBinding
import com.ebenezer.gana.shoppy.firestore.FirestoreClass
import com.ebenezer.gana.shoppy.models.Products
import com.ebenezer.gana.shoppy.ui.activities.CartListActivity
import com.ebenezer.gana.shoppy.ui.activities.SettingsActivity
import com.ebenezer.gana.shoppy.ui.adapters.DashboardListAdapter
import com.ebenezer.gana.shoppy.utils.Constants
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore


class DashboardFragment : BaseFragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var rvDashboardItems: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        rvDashboardItems = binding.rvDashboardItems
        getDashboardItemList()


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.dashboard_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {

                startActivity(Intent(activity, SettingsActivity::class.java))

                return true
            }
            R.id.action_cart -> {

                startActivity(Intent(activity, CartListActivity::class.java))
                return true
            }
        }


        return super.onOptionsItemSelected(item)

    }


    fun successDashboardItemsList(dashboardItemList: ArrayList<Products>) {
        hideProgressDialog()
        /* for (item in dashboardItemList){
             Log.i("Item Title", item.title)
         }*/


        if (dashboardItemList.size > 0) {
            rvDashboardItems.visibility = View.VISIBLE
            binding.tvNoDashboardItemsFound.visibility = View.GONE

            // spanCount is set to 2 after every 5th item
            val layoutManager =
                GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((position + 1) % 5 == 0) 2 else 1
                }
            }
            rvDashboardItems.layoutManager = layoutManager

            rvDashboardItems.adapter = DashboardListAdapter(requireActivity(), dashboardItemList)

            (rvDashboardItems.adapter as DashboardListAdapter).submitList(dashboardItemList)

            rvDashboardItems.setHasFixedSize(true)


        } else {
            rvDashboardItems.visibility = View.GONE

            binding.tvNoDashboardItemsFound.visibility = View.VISIBLE
        }


    }


    private fun getDashboardItemList() {
        //show the progress dialog.
        showProgressDialog(resources.getString(R.string.please_wait))

        FirestoreClass().getDashboardItemsList(this@DashboardFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}