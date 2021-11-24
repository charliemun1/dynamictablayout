package com.charliemun.dynamictablayout.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charliemun.dynamictablayout.R
import com.charliemun.dynamictablayout.databinding.PlaceholderItemBinding
import org.json.JSONArray
import org.json.JSONObject

class PlaceholderItemsAdapter() : RecyclerView.Adapter<PlaceholderItemsAdapter.PlaceholderItemsViewHolder>() {

    /**
     * The items that our Adapter will show
     */
    private var items: JSONArray = JSONArray()

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceholderItemsViewHolder {
        val binding: PlaceholderItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.placeholder_item, parent, false
        )
        return PlaceholderItemsViewHolder(binding)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: PlaceholderItemsViewHolder, position: Int) {
        val item = items.getJSONObject(position)
        holder.bind(item)

        holder.placeholderItemBinding.expandIcon.setOnClickListener {
            holder.placeholderItemBinding.details.visibility = View.VISIBLE
            holder.placeholderItemBinding.expandIcon.visibility = View.GONE
            holder.placeholderItemBinding.collapseIcon.visibility = View.VISIBLE
        }
        holder.placeholderItemBinding.collapseIcon.setOnClickListener {
            holder.placeholderItemBinding.details.visibility = View.GONE
            holder.placeholderItemBinding.collapseIcon.visibility = View.GONE
            holder.placeholderItemBinding.expandIcon.visibility = View.VISIBLE
        }
    }

    fun updateList(list: JSONArray) {
        items = list

        // Notify any registered observers that the data set has changed. This will cause every
        // element in our RecyclerView to be invalidated.
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.length()

    /**
     * ViewHolder for items.
     */
    class PlaceholderItemsViewHolder(var placeholderItemBinding: PlaceholderItemBinding) :
        RecyclerView.ViewHolder(
            placeholderItemBinding.root
        ) {
        fun bind(item: JSONObject) {
            placeholderItemBinding.txtName.text = item.getString("name")
            placeholderItemBinding.txtStart.text = "Start Time: ${item.getString("start_time")}"
            placeholderItemBinding.txtEnd.text = "End Time: ${item.getString("end_time")}"
        }
    }
}