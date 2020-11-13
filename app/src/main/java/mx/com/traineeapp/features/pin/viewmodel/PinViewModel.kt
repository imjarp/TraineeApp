package mx.com.traineeapp.features.pin.viewmodel;

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.com.traineeapp.core.IDispatcher
import mx.com.traineeapp.core.Result
import mx.com.traineeapp.features.pin.model.PinModel
import mx.com.traineeapp.features.pin.model.PinResponse
import mx.com.traineeapp.features.pin.usecase.IPostPinUseCase

class PinViewModel(private val useCase: IPostPinUseCase, private val iDispatcher: IDispatcher) : ViewModel() {

    @VisibleForTesting
    val mutableLiveData = MutableLiveData<ViewState>()
    val liveData: LiveData<ViewState> get() = mutableLiveData

    fun dispatch(action: Actions) {
        when (action) {
            is Actions.SavePinAction -> {
                mutableLiveData.postValue(ViewState.Loading)
                viewModelScope.launch(iDispatcher.getIOThread()) {
                    val result = useCase.execute(PinModel(action.pin))
                    mutableLiveData.postValue(ViewState.SavePinResult(result))
                }
            }
        }
    }

    sealed class ViewState {
        object Loading : ViewState()
        data class SavePinResult(val result: Result<PinResponse>) : ViewState()
    }


    sealed class Actions {
        data class SavePinAction(val pin: String?) : Actions()
    }
}