package bravo.mail.emailapp.ui.leftmenu.menu.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import bravo.mail.emailapp.databinding.ItemLeftMenuDeleteBinding
import bravo.mail.emailapp.ui.leftmenu.menu.adapter.MenuAdapter.*
import java.util.concurrent.Executors

class MenuAdapter(
    private val onItemClick: (MenuLeftEntity, position: Int) -> Unit,
    private val onItemMove: (MenuLeftEntity) -> Unit
) : ListAdapter<MenuLeftEntity, MenuViewHolder>(
    AsyncDifferConfig.Builder(MenuLeftEntity.differCallback)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding =
            ItemLeftMenuDeleteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding, onItemClick, onItemMove)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    fun removeDiff(entity: MenuLeftEntity) {
        val currentList = currentList.toMutableList()
        currentList.remove(entity)
        submitList(currentList)
    }

    override fun submitList(list: MutableList<MenuLeftEntity>?) {
        super.submitList(list?.let { ArrayList(it)})
    }

    fun setData(data: MutableList<MenuLeftEntity>?) {
        data?.let {
            if (it == currentList) {
                notifyDataSetChanged()
            } else {
                submitList(it)
            }
        }
    }

    fun addDiff(entity: MenuLeftEntity) {
        val currentList = currentList.toMutableList()
        currentList.add(entity)
        submitList(currentList)
    }

    fun moveItemInRecyclerViewList(from: Int, to: Int) {
        val list = currentList.toMutableList()
        val fromLocation = list[from]
        list.removeAt(from)
        if (to < from) {
            //+1 because it start from 0 on the upside. otherwise it will not change the locations accordingly
            list.add(to + 1 , fromLocation)
        } else {
            //-1 because it start from length + 1 on the down side. otherwise it will not change the locations accordingly
            list.add(to - 1, fromLocation)
        }
        submitList(list)
    }

    class MenuViewHolder(
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