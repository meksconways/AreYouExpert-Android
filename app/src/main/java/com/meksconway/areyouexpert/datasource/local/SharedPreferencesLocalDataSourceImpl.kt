package com.meksconway.areyouexpert.datasource.local

import android.content.SharedPreferences
import com.meksconway.areyouexpert.data.datasource.local.SharedPreferencesLocalDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class SharedPreferencesLocalDataSourceImpl
@Inject constructor(
    sharedPreferences: SharedPreferences
) : SharedPreferencesLocalDataSource {

    private val TOKEN_KEY = "TOKEN_KEY"
    private val prefSubject = BehaviorSubject.createDefault(sharedPreferences)

    private val prefChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, _ ->
            prefSubject.onNext(sharedPreferences)
        }

    init {
        sharedPreferences.registerOnSharedPreferenceChangeListener(prefChangeListener)
    }

    override fun saveToken(token: String?): Completable = prefSubject
        .firstOrError()
        .editSharedPreferences {
            putString(TOKEN_KEY, token)
        }

    override fun getToken(): Observable<String?> = prefSubject
        .map { it.getString(TOKEN_KEY, null) }

    override fun clearToken(): Completable = prefSubject
        .firstOrError()
        .clearSharedPreferences {
            remove(TOKEN_KEY)
        }

    override fun clear(): Completable = prefSubject
        .firstOrError()
        .clearSharedPreferences {
            this.clear()
        }

    private fun Single<SharedPreferences>.editSharedPreferences(batch: SharedPreferences.Editor.() -> Unit): Completable =
        flatMapCompletable {
            Completable.fromAction {
                it.edit().also(batch).apply()
            }
        }

    private fun Single<SharedPreferences>.clearSharedPreferences(batch: SharedPreferences.Editor.() -> Unit): Completable =
        flatMapCompletable {
            Completable.fromAction {
                it.edit().also(batch).apply()
            }
        }


}