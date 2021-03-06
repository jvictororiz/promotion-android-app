package br.com.promotion.firebaseservice.util

import br.com.promotion.firebaseservice.LogService
import br.com.promotion.firebaseservice.extension.ResultNullNotExpected
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import io.reactivex.Single
import java.io.IOException


internal fun <TaskType> Task<TaskType>.complete() = Completable.create { emitter ->
    addOnSuccessListener {
        emitter.onComplete()
    }.addOnCompleteListener {
        emitter.onComplete()
    }.addOnFailureListener(emitter::onError)
}

internal fun <TaskType> Task<TaskType>.complete(emitter: CompletableEmitter) {
    addOnSuccessListener { emitter.onComplete() }
        .addOnCompleteListener { emitter.onComplete() }
        .addOnFailureListener(emitter::onError)
}

internal inline fun <reified T> Task<DataSnapshot>.single() = Single.create<T> { emitter ->
    addOnSuccessListener { dataSnapshot ->
        val result = dataSnapshot.getValue(T::class.java)
        if (result != null) {
            emitter.onSuccess(result)
        } else {
            throw ResultNullNotExpected(null)
        }
    }.addOnFailureListener(emitter::onError)
}

internal fun Completable.handleDefaultErrors() =
    onErrorResumeNext {
        return@onErrorResumeNext Completable.error(
            when (it) {
                is IOException -> Exception(it.localizedMessage, it.cause)
                else -> Exception(it.localizedMessage, it.cause)
            }
        )
    }

internal fun <T> Single<T>.handleDefaultErrors() =
    onErrorResumeNext {
        return@onErrorResumeNext Single.error<T>(
            when (it) {
                is IOException -> Exception(it.localizedMessage, it.cause)
                else -> Exception(it.localizedMessage, it.cause)
            }
        )
    }
