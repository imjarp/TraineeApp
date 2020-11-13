package mx.com.traineeapp.features.pin.policy

import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

class PinPolicyTest {

    @Test
    fun whenPinIsValidResultIsTrue() {

        // Arrange
        val pin = ""

        // Act
        val result = PinPolicy.validate(pin)


        // Assert
        Assert.assertTrue(result.isSuccess)
    }


}