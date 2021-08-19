package br.com.promotion.firebaseservice.di

import br.com.promotion.firebaseservice.UserService
import br.com.promotion.firebaseservice.service.UserServiceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val userServiceModule = module {
    factory {
        FirebaseAuth.getInstance()
    }
    factory {
        FirebaseDatabase.getInstance()
    }
    factory<UserService> {
        UserServiceImpl(
            authentication = get(),
            database = get()
        )
    }
}