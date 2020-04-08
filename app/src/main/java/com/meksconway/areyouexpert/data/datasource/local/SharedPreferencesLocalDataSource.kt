package com.meksconway.areyouexpert.data.datasource.local

import io.reactivex.Completable
import io.reactivex.Observable

interface SharedPreferencesLocalDataSource {

    fun saveToken(token: String?): Completable
    fun getToken(): Observable<String?>
    fun clearToken(): Completable
    fun clear(): Completable

}