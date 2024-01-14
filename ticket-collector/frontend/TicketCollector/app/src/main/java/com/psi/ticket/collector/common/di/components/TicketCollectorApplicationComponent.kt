package com.psi.ticket.collector.common.di.components

import android.content.Context
import com.psi.ticket.collector.TicketCollectorApplication
import com.psi.ticket.collector.codeCheck.presentation.CodeCheckViewModelFactory
import com.psi.ticket.collector.common.di.modules.ApiModule
import com.psi.ticket.collector.common.di.modules.MainActivityModule
import com.psi.ticket.collector.common.di.modules.NetworkModule
import com.psi.ticket.collector.ticketDetails.presentation.TicketDetailsViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        MainActivityModule::class,
        NetworkModule::class,
        ApiModule::class
    ]
)
interface TicketCollectorApplicationComponent {

    fun inject(application: TicketCollectorApplication)
    fun codeCheckViewModelFactory(): CodeCheckViewModelFactory
    fun ticketDetailsViewModelFactory(): TicketDetailsViewModelFactory

    @Component.Builder
    interface Builder {

        fun build(): TicketCollectorApplicationComponent

        @BindsInstance
        fun application(application: Context): Builder
    }
}