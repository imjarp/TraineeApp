package mx.com.traineeapp.features.pin.usecase

import mx.com.traineeapp.core.Result
import mx.com.traineeapp.features.pin.model.PinModel
import mx.com.traineeapp.features.pin.model.PinResponse
import mx.com.traineeapp.features.pin.policy.PinPolicy
import mx.com.traineeapp.features.pin.services.IPinLocalRepository
import mx.com.traineeapp.services.IPINNetworkService
import java.util.*

class PostPinUseCase(
    private val repository: IPinLocalRepository,
    private val networkService: IPINNetworkService
) : IPostPinUseCase {

    override fun execute(pinModel: PinModel): Result<PinResponse> {
        val pinValidation = PinPolicy.validate(pinModel.pin)
        if (pinValidation.hasError) {
            return Result(null, pinValidation.error)
        }

        val pinResponse = networkService.savePin(pinModel)

        repository.save(Date())

        return Result(pinResponse)

    }
}

interface IPostPinUseCase {

    fun execute(pinModel: PinModel): Result<PinResponse>
}
