package bravo.mail.emailapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import bravo.mail.emailapp.R
import bravo.mail.emailapp.extension.setAnimationCloud
import bravo.mail.emailapp.ui.homemain.HomeActivity
import kotlinx.android.synthetic.main.activity_welcom_login.*

class WelcomeLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcom_login)

        initView()
    }

    private fun initView() {
        img_cloud_welcome_middle.setAnimationCloud(1500)
        img_cloud_welcome_top.setAnimationCloud(2000)

        btn_next.setOnClickListener {
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
