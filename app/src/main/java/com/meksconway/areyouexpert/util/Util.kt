package com.meksconway.areyouexpert.util

import android.content.res.Resources
import android.util.DisplayMetrics
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.schedulers.Schedulers


//Transformers
fun <T> Observable<T>.remote(): Observable<Res<T>> =
    map { Res.success(it) }
        .networkErrorHandler()
        .subscribeOn(Schedulers.io())
        .compose(concatWithLoading())


fun <T> Observable<T>.local(): Observable<Res<T>> =
    map { Res.success(it) }
        .localErrorHandler()
        .subscribeOn(Schedulers.io())
        .compose(concatWithLoading())

fun <T> concatWithLoading(): ObservableTransformer<Res<T>,
        Res<T>> = ObservableTransformer {
    Observable.just(Res.loading<T>()).concatWith(it)
}

fun <T> Observable<Res<T>>.networkErrorHandler(): Observable<Res<T>> {
    val transformer: ObservableTransformer<Res<T>, Res<T>> =
        ObservableTransformer { upstream ->
            upstream.onErrorReturn {
                //                val handleInfo = when(throwable) {
//                    is HttpException -> RetrofitErrorHandlerFactory.getHttpErrorHandler()
//                    else -> throwable
//
//                }
                Res.error(it)
            }
        }
    return compose(transformer)
}

fun <T> Observable<Res<T>>.localErrorHandler(): Observable<Res<T>> {
    val transformer: ObservableTransformer<Res<T>, Res<T>> =
        ObservableTransformer { upstream ->
            upstream.onErrorReturn {
                //                val handleInfo = when(throwable) {
//                    is HttpException -> RetrofitErrorHandlerFactory.getHttpErrorHandler()
//                    else -> throwable
//
//                }
                Res.error(it)
            }
        }
    return compose(transformer)
}

data class Res<T> constructor(
    val status: Status,
    var data: T?,
    val error: Throwable? = null
) {

    companion object {

        @JvmStatic
        fun <T> error(throwable: Throwable): Res<T> {
            return Res(Status.ERROR, null, throwable)
        }

        @JvmStatic
        fun <T> success(data: T): Res<T> {
            return Res(Status.SUCCESS, data, null)
        }

        @JvmStatic
        fun <T> loading(): Res<T> {
            return Res(Status.LOADING, null, null)
        }

    }

}

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}


object RetrofitErrorHandlerFactory {

    fun getHttpErrorHandler() {

    }
}

val Float.dp: Float
    get() = (this / Resources.getSystem().displayMetrics.density)
val Float.px: Float
    get() = (this * Resources.getSystem().displayMetrics.density)

fun convertPixelsToDp(px: Float): Float {
    return px / (Resources.getSystem().displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)
}
//sealed class Resource<T>(
//    val data: T? = null,
//    val message: String? = null
//) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Loading<T>(data: T? = null) : Resource<T>(data)
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
//}


