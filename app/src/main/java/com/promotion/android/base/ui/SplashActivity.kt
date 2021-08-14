package com.promotion.android.base.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.common.login.ui.activity.UserActivity
import com.promotion.android.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        val intent = Intent()
//        intent.setClassName(packageName, UserActivity::class.java)
        startActivity(Intent(this, UserActivity::class.java))
    }
}