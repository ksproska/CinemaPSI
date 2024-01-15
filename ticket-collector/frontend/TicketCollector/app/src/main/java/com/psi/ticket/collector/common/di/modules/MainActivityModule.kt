package com.psi.ticket.collector.common.di.modules

import com.psi.ticket.collector.MainActivity
import com.psi.ticket.collector.common.di.subcomponents.MainActivitySubcomponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [MainActivitySubcomponent::class])
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ClassKey(MainActivity::class)
    abstract fun bindAndroidInjectorFactory(
        factory: MainActivitySubcomponent.Factory
    ): AndroidInjector.Factory<*>
}