package br.com.promotion.ui_component.extension

import android.widget.EditText
import com.google.android.material.tabs.TabLayout

fun TabLayout.addOnSelecteListener(block: (TabLayout.Tab) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            block.invoke(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}

        override fun onTabReselected(tab: TabLayout.Tab) {
            block.invoke(tab)
        }
    })
}

fun EditText.setOnTouchOrFocusListener(block: () -> Unit) {
    setOnFocusChangeListener { _, _ ->
        block.invoke()
    }
    setOnClickListener {
        block.invoke()
    }
}