package br.com.promotion.firebaseservice

import br.com.promotion.firebaseservice.service.UserServiceImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val userServiceModule = module {
    factory {
        FirebaseAuth.getInstance()
    }
    factory {
        FirebaseFirestore.getInstance()
    }
    factory<UserService> {
        UserServiceImpl(
            authentication = FirebaseAuth.getInstance(),
            database = FirebaseFirestore.getInstance()
        )
    }
}