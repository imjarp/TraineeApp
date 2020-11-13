package mx.com.traineeapp.features.pin.usecase

import mx.com.traineeapp.core.Result
import mx.com.traineeapp.features.pin.model.PinModel
import mx.com.traineeapp.features.pin.model.PinResponse
import mx.com.traineeapp.features.pin.services.IPinLocalRepository
import mx.com.traineeapp.services.IPINNetworkService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class PostPinUseCaseTest {

    @Test
    fun whenPinIsValidGetsCorrectResult() {

        // Arrange
        val pinModel = PinModel("1234")
        val response = PinResponse("1234")
        val expected = Result(response, null)
        //Mockito.`when`(mockRepository.save(date)).then { }
        Mockito.`when`(mockNetworkService.savePin(pinModel)).thenReturn(response)
        val sut = getSUT(mockRepository, mockNetworkService)

        // Act
        val result = sut.execute(pinModel)

        // Assert
        Mockito.verify(mockNetworkService, Mockito.atLeast(1)).savePin(pinModel)
        Assert.assertEquals(expected, result)
    }

    @Mock
    lateinit var mockRepository: IPinLocalRepository

    @Mock
    lateinit var mockNetworkService: IPINNetworkService

    val date = Date()

    private fun getSUT(
        repository: IPinLocalRepository,
        networkService: IPINNetworkService
    ): PostPinUseCase {
        return PostPinUseCase(repository, networkService)
    }
}