package bravo.mail.emailapp.ui.leftmenu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.R
import bravo.mail.emailapp.data.model.MenuLeft
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import bravo.mail.emailapp.ui.leftmenu.menu.adapter.MenuAdapter
import bravo.mail.emailapp.ui.leftmenu.menu.adapter.MenuLeftAdapter
import bravo.mail.emailapp.ui.leftmenu.menu.viewmodel.MenuLeftViewModel
import bravo.mail.emailapp.ui.leftmenu.moremenu.adapter.MoreMenuAdapter
import kotlinx.android.synthetic.main.activity_edit_left_menu.*


class EditMenuLeftActivity : AppCompatActivity() {

    private val menuAdapter by lazy { MenuAdapter(::onItemClickMenu,::onItemMoveMenu) }
    private val moreMenuAdapter by lazy { MoreMenuAdapter(::onItemClickMoreMenu) }

    private val menuViewModel by lazy { ViewModelProvider(this)[MenuLeftViewModel::class.java] }
    private val listMoreMenu: ArrayList<MenuLeft> by lazy { arrayListOf() }

    private val listMenuAll: ArrayList<MenuLeft> by lazy { arrayListOf() }
    private val listMenu: ArrayList<MenuLeftEntity> by lazy { arrayListOf() }
    private lateinit var itemMenu: MenuLeftEntity
    private lateinit var itemMoreMenu: MenuLeft

    private val itemTouchHelper by lazy {
        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(UP or DOWN or START or END, 0) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
//                val adapter = recyclerView.adapter as MenuLeftAdapter
                val adapter = recyclerView.adapter as MenuAdapter
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                adapter.moveItemInRecyclerViewList(from, to)
                adapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

            override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
                super.onSelectedChanged(viewHolder, actionState)

                if (actionState == ACTION_STATE_DRAG) {
                    viewHolder?.itemView?.apply {
//                        alpha = 0.8f
                        setBackgroundColor(ContextCompat.getColor(this@EditMenuLeftActivity,R.color.bg_item_move))
                    }
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ) {
                super.clearView(recyclerView, viewHolder)
                viewHolder.itemView.apply {
//                    alpha = 1f
                    setBackgroundColor(ContextCompat.getColor(this@EditMenuLeftActivity,R.color.white))
                }
            }
        }
        ItemTouchHelper(itemTouchCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_left_menu)

        initData()
        initView()
        initHandles()
    }

    private fun initHandles() {
        img_back_menu_edit.setOnClickListener {
            finish()
        }

        btn_save.setOnClickListener {
            menuViewModel.apply {
                deleteAll()
                insertMenuList(listMenu)
                Log.e("Edit12345 onclick save",listMenu.size.toString())
            }
            finish()
        }
    }

    private fun initView() {
        itemTouchHelper.attachToRecyclerView(rcv_list_menu)
//        menuAdapter.submitList(menuViewModel.readAllDataLive.value)

        rcv_list_menu.apply {
            adapter = menuAdapter
        }
        rcv_list_more_menu.apply {
            adapter = moreMenuAdapter
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
        }
    }

    private fun initData() {
        menuViewModel.readAllDataLive.observe(this) {
            listMenu.addAll(it.toMutableList())
//            menuAdapter.submitList(listMenu)
//            menuAdapter.addAllData(listMenu)
            menuAdapter.setData(listMenu)
            Log.e("Edit12345 init data",listMenu.size.toString())
        }

        listMenuAll.addAll(
            listOf(
                MenuLeft("Send", R.drawable.ic_menu_send),
                MenuLeft("Drafts", R.drawable.ic_menu_drafts),
                MenuLeft("Pins", R.drawable.ic_menu_pins),
                MenuLeft("Archive", R.drawable.ic_menu_archive),
                MenuLeft("Trash", R.drawable.ic_menu_trash),
                MenuLeft("Snoozed", R.drawable.ic_menu_snoozed),
                MenuLeft("Spam", R.drawable.ic_menu_spam),
                MenuLeft("Recently Seen", R.drawable.ic_menu_recently),
                MenuLeft("Shared", R.drawable.ic_menu_share),
                MenuLeft("Shared Drafts", R.drawable.ic_menu_share),
                MenuLeft("Delegated", R.drawable.ic_menu_delegated),
                MenuLeft("Reminders", R.drawable.ic_menu_reminders)
            )
        )

        for (item in listMenuAll) {
            if (!menuViewModel.checkIsExists(item.name)) {
                listMoreMenu.add(item)
            }
        }
        moreMenuAdapter.updateData(listMoreMenu)
    }

    private fun onItemClickMenu(menuLeftEntity: MenuLeftEntity, position: Int) {
        listMenu.remove(menuLeftEntity)
        Log.e("Edit12345 onclick menu",listMenu.size.toString())
        menuAdapter.removeDiff(menuLeftEntity)
        menuAdapter.submitList(listMenu)
//        menuAdapter.removeData(menuLeftEntity)

        itemMoreMenu = MenuLeft(menuLeftEntity.name, menuLeftEntity.image)
        listMoreMenu.add(itemMoreMenu)
        moreMenuAdapter.addItemData(itemMoreMenu)
    }

    private fun onItemClickMoreMenu(menuLeft: MenuLeft) {

        //remove item list menu
        listMoreMenu.remove(menuLeft)
        moreMenuAdapter.updateData(listMoreMenu)

        //add item list more menu
        itemMenu = MenuLeftEntity(menuLeft.name, menuLeft.image)
        listMenu.add(itemMenu)
        Log.e("Edit12345 onclick more menu",listMenu.size.toString())
//        menuAdapter.addItemData(itemMenu)
        menuAdapter.addDiff(itemMenu)
//        menuAdapter.submitList(listMenu)
    }

    private fun onItemMoveMenu(menuLeftEntity: MenuLeftEntity) {

    }
}
