package br.com.promotion.ui_component.extension

import android.util.Patterns
import java.util.regex.Pattern

fun String.isEmailValid() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

