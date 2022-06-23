package bravo.mail.emailapp.ui.leftmenu.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.R
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import kotlinx.android.synthetic.main.item_left_menu_delete.view.*

class MenuLeftAdapter(
    private val onItemClick: (MenuLeftEntity) -> Unit
) : RecyclerView.Adapter<MenuLeftAdapter.MenuLeftViewHolder>() {

    private var listMenuDelete = ArrayList<MenuLeftEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuLeftViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_left_menu_delete, parent, false)
        return MenuLeftViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MenuLeftViewHolder, position: Int) {
        holder.onBind(listMenuDelete[position])
    }

    override fun getItemCount(): Int = listMenuDelete.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAllData(entity: List<MenuLeftEntity>) {
        this.listMenuDelete.clear()
        this.listMenuDelete.addAll(entity)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemData(entity: MenuLeftEntity) {
        this.listMenuDelete.add(entity)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeData(entity: MenuLeftEntity) {
        this.listMenuDelete.remove(entity)
        notifyDataSetChanged()
    }

    private val differCallback = object: DiffUtil.ItemCallback<MenuLeftEntity>() {
        override fun areItemsTheSame(oldItem: MenuLeftEntity, newItem: MenuLeftEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MenuLeftEntity, newItem: MenuLeftEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    fun moveItem(fromPosition: Int, toPosition: Int) {
        val list = differ.currentList.toMutableList()
        val fromItem = list[fromPosition]
        list.removeAt(fromPosition)
        if (toPosition < fromPosition) {
            list.add(toPosition + 1 , fromItem)
        } else {
            list.add(toPosition - 1, fromItem)
        }
        differ.submitList(list)
    }

    class MenuLeftViewHolder(
        itemView: View,
        private val onItemClick: (MenuLeftEntity) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun onBind(entity: MenuLeftEntity) {
            with(itemView) {
                img_icon_item_left_menu.setImageResource(entity.image)
                tv_name_item_left_menu.text = entity.name

                setOnClickListener {
                    animate().alpha(0F).setDuration(500).withEndAction {
                        onItemClick(entity)
                        animate().alpha(1F)
                    }
                }
            }
        }
    }
}
