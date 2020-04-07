package com.meksconway.areyouexpert.ui.fragment.notification

import androidx.annotation.NavigationRes
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.domain.usecase.NotificationUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import com.meksconway.areyouexpert.viewmodel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


interface NotificationViewModelInput : Input {

    fun getNotifications()
    fun deleteAllNotifications()
    fun clickNotification(navigation: NotificationNavigation)
}

data class NotificationNavigation(
    val notification: NotificationEntity?,
    @NavigationRes
    val navId: Int
)

interface NotificationViewModelOutput : Output {

    var notificationData: MutableLiveData<Res<List<NotificationEntity>>>
    var deleteOutput: SingleLiveEvent<Boolean>
    var clickOutput: SingleLiveEvent<NotificationNavigation>
}

class NotificationViewModel
@Inject constructor(private val useCase: NotificationUseCase) :
    BaseViewModel<NotificationViewModelInput, NotificationViewModelOutput>(),
    NotificationViewModelInput, NotificationViewModelOutput {
    override val input: NotificationViewModelInput = this
    override val output: NotificationViewModelOutput = this

    override fun getNotifications() {
        useCase.getLocalNotificationList().observeOn(AndroidSchedulers.mainThread())
            .doOnNext {notificationData.value = it}
            .subscribe()
            .addTo(compositeDisposable)
    }

    override fun deleteAllNotifications() {
        useCase.deleteAllNotificationList().observeOn(AndroidSchedulers.mainThread())
            .subscribe{deleteOutput.value = true}
            .addTo(compositeDisposable)
    }

    override fun clickNotification(navigation: NotificationNavigation) {
        clickOutput.value = navigation
    }

    override var notificationData = MutableLiveData<Res<List<NotificationEntity>>>()

    override var deleteOutput = SingleLiveEvent<Boolean>()

    override var clickOutput = SingleLiveEvent<NotificationNavigation>()

}
