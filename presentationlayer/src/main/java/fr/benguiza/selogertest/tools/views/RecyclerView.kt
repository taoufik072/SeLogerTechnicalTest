package fr.benguiza.selogertest.tools.views

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import fr.benguiza.selogertest.tools.extensions.inflate
import fr.benguiza.selogertest.tools.extensions.removeOnClickListener


class ListAdapter<T>(
    private var items: List<T>,
    @LayoutRes private val layout: Int,
    private val createViewHolder: (View) -> ViewHolder<T>,
    private val shouldDrawSelector: Boolean = false,
    private val originBg: Int = -1,
    private val selectedBg: Int = -1
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var selectedItemPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return createViewHolder(parent.inflate(layout))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            (holder as ViewHolder<T>).bind(items[position])
            holder.itemView.setOnClickListener {
                (holder as ViewHolder<T>).onClick(items[position], position)
                if (shouldDrawSelector) {
                    (holder as ViewHolder<T>)
                        .drawSelector(true, originBg, selectedBg)
                    notifyItemChanged(position)
                    notifyItemChanged(selectedItemPosition)
                    selectedItemPosition = position
                }
            }
            if (shouldDrawSelector) {
                (holder as ViewHolder<T>)
                    .drawSelector(position == selectedItemPosition, originBg, selectedBg)
            }
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        (holder as ViewHolder<T>).unbind()
        holder.itemView.removeOnClickListener()
    }

    fun addItems(itemToAdd: List<T>) {
        items = items.plus(itemToAdd)
        notifyDataSetChanged()
    }

    fun keepItems(count: Int) {
        items = items.take(count)
        notifyDataSetChanged()
    }

    fun updateItems(updatedItems: List<T>) {
        items = items.drop(items.size).plus(updatedItems)
        notifyDataSetChanged()
    }


}

abstract class ViewHolder<in T>(val view: View) : RecyclerView.ViewHolder(view) {
    open fun bind(item: T) {}
    open fun bindPosition(item: T, position: Int) {}
    open fun onClick(item: T, position: Int) {}
    open fun unbind() {}
    fun drawSelector(isSelected: Boolean, originBg: Int, selectedBg: Int) {
        view.setBackgroundResource(if (isSelected) selectedBg else originBg)
    }
}
