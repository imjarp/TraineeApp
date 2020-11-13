package mx.com.traineeapp.application

import android.app.Application
import mx.com.traineeapp.features.pin.module.pinModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class TraineeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        val modules = listOf(
            pinModule
        )
        startKoin {
            KoinApplication.init().modules(modules)
        }
    }
}