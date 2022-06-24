package bravo.mail.emailapp.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import bravo.mail.emailapp.R
import bravo.mail.emailapp.extension.customTextViewAgree
import bravo.mail.emailapp.extension.isValidEmail
import bravo.mail.emailapp.extension.setAnimationCloud
import bravo.mail.emailapp.ui.homemain.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initHandles()
    }

    private fun initView() {
        img_cloud_login_middle.setAnimationCloud(1500)
        img_cloud_login_top.setAnimationCloud(2000)
        checkEmailButton()
    }

    private fun initHandles() {
        customTextViewAgree(this, terms_and_privacy, constraint_terms, constraint_privacy)

        img_back_terms.setOnClickListener {
            constraint_terms.animate().translationY(3500F).duration = 800
        }

        img_back_privacy.setOnClickListener {
            constraint_privacy.animate().translationY(3500F).duration = 800
        }
    }

    private fun checkEmailButton() {
        edt_email.doOnTextChanged { text, start, before, count ->
            if (text!!.isValidEmail()) {
                layout_email.endIconDrawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
                btn_start.setBackgroundResource(R.drawable.bg_btn_email_correct)
            } else {
                layout_email.endIconDrawable = null
                btn_start.setBackgroundResource(R.drawable.bg_btn_email_wrong)
            }
        }
        btn_start.setOnClickListener {
            val intent = Intent(this,WelcomeLoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (constraint_terms.translationY == 0F) {
            constraint_terms.animate().translationY(3500F).duration = 1000
        } else if (constraint_privacy.translationY == 0F) {
            constraint_privacy.animate().translationY(3500F).duration = 1000
        } else {
            finish()
        }
    }
}
