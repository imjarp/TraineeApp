package mx.com.traineeapp.features.pin.policy

import mx.com.traineeapp.core.Result

class PinPolicy {

    companion object {
        @JvmStatic
        fun validate(pin: String?): Result<Boolean> {
            // TODO: cambiar implementacion real
            return Result(true)
        }
    }
}