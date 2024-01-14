package com.psi.ticket.collector

import android.app.Application
import com.psi.ticket.collector.common.di.components.DaggerTicketCollectorApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import timber.log.Timber.Forest.plant
import javax.inject.Inject

class TicketCollectorApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerTicketCollectorApplicationComponent
            .builder()
            .application(this.applicationContext)
            .build()
            .inject(this)
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}