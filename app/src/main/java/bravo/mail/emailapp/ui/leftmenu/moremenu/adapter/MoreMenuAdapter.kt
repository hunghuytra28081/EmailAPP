package bravo.mail.emailapp.ui.leftmenu.moremenu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.R
import bravo.mail.emailapp.data.model.MenuLeft
import kotlinx.android.synthetic.main.item_left_menu_delete.view.*

class MoreMenuAdapter(
    private val onItemClick: (MenuLeft) -> Unit
) : RecyclerView.Adapter<MoreMenuAdapter.MoreMenuViewHolder>() {

    private var listMenuDelete = ArrayList<MenuLeft>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreMenuViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_menu_more, parent, false)
        return MoreMenuViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: MoreMenuViewHolder, position: Int) {
        holder.onBind(listMenuDelete[position])
    }

    override fun getItemCount(): Int = listMenuDelete.size

    @SuppressLint("NotifyDataSetChanged")
    fun addData(menuLeft: List<MenuLeft>) {
        this.listMenuDelete.clear()
        this.listMenuDelete.addAll(menuLeft)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItemData(menuLeft: MenuLeft) {
        this.listMenuDelete.add(menuLeft)
        notifyDataSetChanged()
    }

    class MoreMenuViewHolder(
        itemView: View,
        private val onItemClick: (MenuLeft) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun onBind(entity: MenuLeft) {
            with(itemView) {
                img_icon_item_left_menu.setImageResource(entity.image)
                tv_name_item_left_menu.text = entity.name

                alpha = 1F

                setOnClickListener {
                    animate().alpha(0F).setDuration(500).withEndAction {
                        onItemClick(entity)
//                        animate().alpha(1F)
                    }
                }
            }
        }
    }
}
