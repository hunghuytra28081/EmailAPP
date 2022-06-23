package bravo.mail.emailapp.ui.homemain

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import bravo.mail.emailapp.R
import bravo.mail.emailapp.data.resource.local.entity.MenuLeftEntity
import bravo.mail.emailapp.extension.addFragment
import bravo.mail.emailapp.ui.home.HomeFragment
import bravo.mail.emailapp.ui.leftmenu.EditMenuLeftActivity
import bravo.mail.emailapp.ui.leftmenu.menu.viewmodel.MenuLeftViewModel
import bravo.mail.emailapp.utils.Constant.PRE_ROOM
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.drawer_view.*


class HomeActivity : AppCompatActivity() {

    private lateinit var preferences: SharedPreferences
    private val mainMenuViewModel by lazy { ViewModelProvider(this)[MenuLeftViewModel::class.java] }
    private val mainMenuAdapter by lazy { MainMenuAdapter(::onItemClick) }
    private val listMenuLeft: ArrayList<MenuLeftEntity> by lazy { arrayListOf() }

    private val toggle by lazy {
        ActionBarDrawerToggle(
            this,
            drawer_layout_home_main,
            toolbar,
            R.string.main_drawer_open_cd,
            0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initData()
        initView()
        initHandles()
    }

    private fun initView() {
        addFragment(R.id.frame_content_main, HomeFragment.newInstance())
        rcv_menu_left_main.adapter = mainMenuAdapter
        toggle.apply {
            syncState()
            drawerArrowDrawable.color =
                ContextCompat.getColor(this@HomeActivity, R.color.blue_theme)
        }
    }

    private fun initData() {
        preferences = getSharedPreferences(PRE_ROOM, MODE_PRIVATE)
        val isFirst = preferences.getBoolean(preferences_name, true)

        mainMenuViewModel.readAllDataLive.observe(this) {
            mainMenuAdapter.addData(it)
        }

        listMenuLeft.addAll(
            listOf(
                MenuLeftEntity("Send", R.drawable.ic_menu_send),
                MenuLeftEntity("Drafts", R.drawable.ic_menu_drafts),
                MenuLeftEntity("Pins", R.drawable.ic_menu_pins),
                MenuLeftEntity("Archive", R.drawable.ic_menu_archive),
                MenuLeftEntity("Trash", R.drawable.ic_menu_trash)
            )
        )

        if (isFirst) {
            mainMenuViewModel.insertMenuList(listMenuLeft)
            preferences.edit().putBoolean(preferences_name, false).apply()
        } else return
    }

    private fun initHandles() {
        linear_edit_list.setOnClickListener {
            val intent = Intent(this, EditMenuLeftActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onItemClick(menuLeftEntity: MenuLeftEntity) {

    }

    companion object {
        const val preferences_name = "preference_room"
    }
}
