package com.psi.ticket.collector.common.di.modules

import com.psi.ticket.collector.common.network.TicketCollectorRepository
import com.psi.ticket.collector.common.network.TicketCollectorRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ApiModule {

    @Singleton
    @Binds
    abstract fun bindsTicketCollectorRepository(
        ticketCollectorRepository: TicketCollectorRepositoryImpl
    ): TicketCollectorRepository
}