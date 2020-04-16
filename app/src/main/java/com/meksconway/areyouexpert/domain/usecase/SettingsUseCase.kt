package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.domain.repository.SettingsRepository
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUseCase
@Inject constructor(private val repository: SettingsRepository) {

    fun getSettingsList(): Observable<List<SettingsModel>> {
        val arr = arrayListOf<SettingsModel>()
        arr.add(
            SettingsModel(
                settingsId = 0,
                settingsTitle = "Reset Your Progress",
                type = SettingsItemType.RESET_PROGRESS
            )
        )
        arr.add(
            SettingsModel(
                settingsId = 1,
                settingsTitle = "Vote App",
                type = SettingsItemType.VOTE
            )
        )
//        arr.add(
//            SettingsModel(
//                settingsId = 2,
//                settingsTitle = "Open Source",
//                type = SettingsItemType.OPEN_SOURCE
//            )
//        )
        arr.add(
            SettingsModel(
                settingsId = 3,
                settingsTitle = "Make Suggestion",
                type = SettingsItemType.MAKE_SUGGESTION
            )
        )

        return Observable.create<List<SettingsModel>> { emitter ->
            emitter.onNext(arr)
        }
    }

    fun resetProgress(): Observable<Res<Boolean>> {
        return Observable.create<Res<Boolean>> { emitter ->
            emitter.onNext(Res.loading())
            repository.dropDatabase()
                .doOnComplete {
                    emitter.onNext(Res.success(true))
                }
                .doOnError {
                    emitter.onNext(Res.error(it))
                }
                .subscribe()
        }
    }

}


enum class SettingsItemType {
    RESET_PROGRESS,
    VOTE,
    OPEN_SOURCE,
    MAKE_SUGGESTION
}

data class SettingsModel(
    val settingsId: Int,
    val settingsTitle: String,
    val type: SettingsItemType
)