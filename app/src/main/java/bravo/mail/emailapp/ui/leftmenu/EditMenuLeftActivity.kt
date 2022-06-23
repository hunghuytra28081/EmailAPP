package bravo.mail.emailapp.ui.leftmenu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import bravo.mail.emailapp.R
import bravo.mail.emailapp.data.model.MenuLeft
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import bravo.mail.emailapp.ui.leftmenu.menu.adapter.MenuLeftAdapter
import bravo.mail.emailapp.ui.leftmenu.menu.viewmodel.MenuLeftViewModel
import bravo.mail.emailapp.ui.leftmenu.moremenu.adapter.MoreMenuAdapter
import kotlinx.android.synthetic.main.activity_edit_left_menu.*


class EditMenuLeftActivity : AppCompatActivity() {

    private val menuAdapter by lazy { MenuLeftAdapter(::onItemClickMenu) }
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
                val recyclerviewAdapter = recyclerView.adapter as MenuLeftAdapter
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                recyclerviewAdapter.moveItem(fromPosition, toPosition)
                recyclerviewAdapter.notifyItemMoved(fromPosition,toPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        }
        ItemTouchHelper(itemTouchCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_left_menu)

        initView()
        initData()
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
            }
            finish()
        }
    }

    private fun initView() {
        itemTouchHelper.attachToRecyclerView(rcv_list_menu)
        menuAdapter.differ.submitList(menuViewModel.readAllDataLive.value)

        rcv_list_menu.apply {
            adapter = menuAdapter
            setHasFixedSize(true)
        }
        rcv_list_more_menu.apply {
            adapter = moreMenuAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                true
            )
        }
    }

    private fun initData() {
        menuViewModel.readAllDataLive.observe(this) {
            listMenu.addAll(it)
            menuAdapter.addAllData(listMenu)
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
        moreMenuAdapter.addData(listMoreMenu)
    }

    private fun onItemClickMenu(menuLeftEntity: MenuLeftEntity) {
        listMenu.remove(menuLeftEntity)
        menuAdapter.removeData(menuLeftEntity)

        itemMoreMenu = MenuLeft(menuLeftEntity.name, menuLeftEntity.image)
        moreMenuAdapter.addItemData(itemMoreMenu)
    }

    private fun onItemClickMoreMenu(menuLeft: MenuLeft) {

        //remove item list menu
        listMoreMenu.remove(menuLeft)
        moreMenuAdapter.addData(listMoreMenu)

        //add item list more menu
        itemMenu = MenuLeftEntity(menuLeft.name, menuLeft.image)
        listMenu.add(itemMenu)
        menuAdapter.addItemData(itemMenu)
    }
}
