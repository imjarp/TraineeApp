package mx.com.traineeapp.features.pin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jraska.livedata.test
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import mx.com.traineeapp.core.IDispatcher
import mx.com.traineeapp.core.Result
import mx.com.traineeapp.features.pin.model.PinModel
import mx.com.traineeapp.features.pin.model.PinResponse
import mx.com.traineeapp.features.pin.usecase.IPostPinUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class PinViewModelTest {


    lateinit var useCase: IPostPinUseCase

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        useCase = Mockito.mock(IPostPinUseCase::class.java)
    }

    @Test
    fun whenActionIsCalledGetsExpectedHistory() {

        //Arrange

        val history = arrayOf(
            PinViewModel.ViewState.Loading,
            pinViewStateResult
        )
        val sut = getSUT()
        val sutTester = sut.mutableLiveData.test()
        Mockito.`when`(useCase.execute(pinModel)).thenReturn(resultPinResponse)

        // Act
        sut.dispatch(PinViewModel.Actions.SavePinAction("1234"))


        // Assert
        sutTester.assertValueHistory(*history)

    }


    private fun getSUT(): PinViewModel {
        val dispatcher = object : IDispatcher {
            override fun getMainThread(): CoroutineDispatcher = Dispatchers.Unconfined

            override fun getIOThread(): CoroutineDispatcher = Dispatchers.Unconfined

        }
        return PinViewModel(useCase, dispatcher)
    }

    val resultPinResponse = Result(
        PinResponse("1234")
    )
    val pinViewStateResult = PinViewModel.ViewState.SavePinResult(resultPinResponse)
    val pinModel = PinModel("1234")


}