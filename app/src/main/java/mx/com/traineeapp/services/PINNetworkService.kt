package mx.com.traineeapp.services

import mx.com.traineeapp.features.pin.model.PinModel
import mx.com.traineeapp.features.pin.model.PinResponse
import retrofit2.Call
import retrofit2.http.POST

class PINNetworkService(private val retrofit: PinRetrofitDefinition) : IPINNetworkService {

    override fun savePin(pinModel: PinModel): PinResponse =
        retrofit.savePin().execute().body()!!

}

interface IPINNetworkService {
    fun savePin(pinModel: PinModel): PinResponse
}

interface PinRetrofitDefinition {
    @POST("")
    fun savePin(): Call<PinResponse>
}