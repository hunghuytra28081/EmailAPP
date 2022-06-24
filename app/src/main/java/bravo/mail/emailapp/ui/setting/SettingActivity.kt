package bravo.mail.emailapp.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bravo.mail.emailapp.R
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initHandles()
    }

    private fun initHandles() {

        img_back_setting.setOnClickListener {
            finish()
        }
    }
}
