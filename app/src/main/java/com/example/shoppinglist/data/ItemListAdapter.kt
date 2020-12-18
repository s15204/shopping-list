package com.example.shoppinglist.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.util.IClickListener

class ItemListAdapter(val itemClickListener : IClickListener) : ListAdapter<Item, ItemListAdapter.ItemViewValues>(ItemComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewValues {
        return ItemViewValues.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewValues, position: Int) {
        val current = getItem(position)
        holder.bind(current, itemClickListener)
    }

    class ItemViewValues(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val itemId: TextView = itemView.findViewById(R.id.item_id)
        private val itemName: TextView = itemView.findViewById(R.id.item_name)
        private val itemAmount: TextView = itemView.findViewById(R.id.item_amount)
        private val itemPrice: TextView = itemView.findViewById(R.id.item_price)
        private val itemIsBought: CheckBox = itemView.findViewById(R.id.item_is_bought)

        fun bind(item : Item, itemClickListener: IClickListener) {
            itemView.setOnClickListener {
                itemClickListener.onItemClicked(item)
            }
            itemId.text = item.id.toString()
            itemName.text = item.name
            itemAmount.text = item.amount.toString()
            itemPrice.text = item.price.toString()
            itemIsBought.isChecked = item.isBought
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewValues {
                val view: View = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
                return ItemViewValues(view)
            }
        }
    }

    class ItemComparator : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}