package br.com.promotion.firebaseservice.service

import br.com.promotion.firebaseservice.UserService
import br.com.promotion.firebaseservice.extension.InternetConnectionException
import br.com.promotion.firebaseservice.extension.UserAlreadyRegistered
import br.com.promotion.firebaseservice.util.complete
import br.com.promotion.firebaseservice.util.handleDefaultErrors
import br.com.promotion.firebaseservice.util.single
import br.com.promotion.model.data.UserDTO
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.Completable
import io.reactivex.Single
import java.io.IOException

internal class UserServiceImpl(
    private val authentication: FirebaseAuth,
    database: FirebaseDatabase
) : UserService {
    private val userTable = database.getReference(TABLE_USERS)

    override fun doLogin(email: String, password: String): Completable {
        return authentication
            .signInWithEmailAndPassword(email, password)
            .complete()
            .handleDefaultErrors()
    }

    override fun registerUser(user: UserDTO): Completable = Completable.create { emitter ->
        authentication.createUserWithEmailAndPassword(
            user.email, user.password
        ).addOnSuccessListener {
            userTable
                .child(user.email)
                .setValue(user)
                .complete(emitter)
        }.addOnFailureListener {
            emitter.tryOnError(
                when (it) {
                    is IOException -> InternetConnectionException(it.message)
                    else -> UserAlreadyRegistered(it.message)
                }
            )
        }
    }

    override fun sendPasswordResetEmail(email: String): Completable {
        return authentication
            .sendPasswordResetEmail(email)
            .complete()
            .handleDefaultErrors()
    }

    override fun updateUser(userDTO: UserDTO): Completable {
        return userTable
            .child(userDTO.email)
            .setValue(userDTO)
            .complete()
            .handleDefaultErrors()
    }

    override fun getUser(email: String): Single<UserDTO> {
        return userTable
            .child(email)
            .get()
            .single<UserDTO>()
            .handleDefaultErrors()
    }

    companion object {
        internal const val TABLE_USERS = "users"
    }
}