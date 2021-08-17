package br.com.promotion.login.ui.fragment

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import br.com.promotion.login.R
import com.google.android.material.tabs.TabLayout


fun TabLayout.addTab(@StringRes idText: Int, event: () -> Unit) {
    addTab(
        newTab().setText(context.getString(R.string.text_login_title)).apply {
            setOnClickListener {
                event.invoke()
            }
        }
    )
}