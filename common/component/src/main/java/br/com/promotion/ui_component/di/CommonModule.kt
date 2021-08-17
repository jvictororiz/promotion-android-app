package br.com.promotion.ui_component.di

import br.com.promotion.ui_component.AppPreferences
import br.com.promotion.ui_component.AppPreferencesImpl
import org.koin.dsl.module


val commonModule = module {
    factory<AppPreferences> { AppPreferencesImpl(get()) }
}