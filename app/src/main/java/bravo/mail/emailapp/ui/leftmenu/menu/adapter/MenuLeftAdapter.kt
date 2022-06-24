package bravo.mail.emailapp.ui.leftmenu.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import bravo.mail.emailapp.databinding.ItemLeftMenuDeleteBinding

class MenuLeftAdapter(
    private val onItemClick: (MenuLeftEntity, position: Int) -> Unit,
    private val onItemMove: (MenuLeftEntity) -> Unit
) : RecyclerView.Adapter<MenuLeftAdapter.MenuLeftViewHolder>() {

    private var listMenuLeft = ArrayList<MenuLeftEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuLeftViewHolder {
        val binding =
            ItemLeftMenuDeleteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuLeftViewHolder(binding, onItemClick, onItemMove)
    }

    override fun onBindViewHolder(holder: MenuLeftViewHolder, position: Int) {
//        val menuItem = differ.currentList[position]
        holder.onBind(listMenuLeft[position])
    }

    override fun getItemCount(): Int = listMenuLeft.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAllData(entity: List<MenuLeftEntity>) {
        this.listMenuLeft.clear()
        this.listMenuLeft.addAll(entity)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemData(entity: MenuLeftEntity) {
        this.listMenuLeft.add(entity)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeData(entity: MenuLeftEntity) {
        this.listMenuLeft.remove(entity)
        notifyDataSetChanged()
    }

    fun removeDiff(entity: MenuLeftEntity) {
        val currentList = differ.currentList.toMutableList()
        currentList.remove(entity)
        differ.submitList(currentList)
    }

    fun addDiff(entity: MenuLeftEntity) {
        val currentList = differ.currentList.toMutableList()
        currentList.add(entity)
        differ.submitList(currentList)
    }

    private val differCallback = object : DiffUtil.ItemCallback<MenuLeftEntity>() {
        override fun areItemsTheSame(oldItem: MenuLeftEntity, newItem: MenuLeftEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MenuLeftEntity, newItem: MenuLeftEntity): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    fun moveItemInRecyclerViewList(from: Int, to: Int) {
        val list = differ.currentList.toMutableList()
        val fromLocation = list[from]
        list.removeAt(from)
        if (to < from) {
            //+1 because it start from 0 on the upside. otherwise it will not change the locations accordingly
            list.add(to + 1 , fromLocation)
        } else {
            //-1 because it start from length + 1 on the down side. otherwise it will not change the locations accordingly
            list.add(to - 1, fromLocation)
        }
        differ.submitList(list)
    }

    class MenuLeftViewHolder(
        private val binding: ItemLeftMenuDeleteBinding,
        private val onItemClick: (MenuLeftEntity, position: Int) -> Unit,
        private val onItemMove: (MenuLeftEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(entity: MenuLeftEntity) {
            with(binding) {
                imgIconItemLeftMenu.setImageResource(entity.image)
                tvNameItemLeftMenu.text = entity.name

                root.alpha = 1F
                viewOnClick.setOnClickListener {
                    root.animate().alpha(0F).setDuration(500).withEndAction {
                        onItemClick(entity, adapterPosition)
                    }
                }

                imgMoveItemMenu.setOnClickListener {
                    onItemMove(entity)
                }
            }
        }
    }
}
