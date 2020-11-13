package mx.com.traineeapp.features.pin.module

import mx.com.traineeapp.R
import mx.com.traineeapp.core.AppDispatchers
import mx.com.traineeapp.core.IDispatcher
import mx.com.traineeapp.features.pin.usecase.IPostPinUseCase
import mx.com.traineeapp.features.pin.usecase.PostPinUseCase
import mx.com.traineeapp.features.pin.viewmodel.PinViewModel
import mx.com.traineeapp.services.IPINNetworkService
import mx.com.traineeapp.services.PINNetworkService
import mx.com.traineeapp.services.PinRetrofitDefinition
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit


val pinModule = module {

    single { PINNetworkService(get()) as IPINNetworkService }

    single {
        Retrofit.Builder()
            .baseUrl(androidApplication().getString(R.string.base_url))
            .build()
            .create(PinRetrofitDefinition::class.java)
                as PinRetrofitDefinition
    }

    factory { PostPinUseCase(get(), get()) as IPostPinUseCase }

    viewModel { PinViewModel(get(),get()) }

    single { AppDispatchers() as IDispatcher }


}