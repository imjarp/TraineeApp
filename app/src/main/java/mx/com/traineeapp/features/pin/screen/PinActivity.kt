package mx.com.traineeapp.features.pin.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.com.traineeapp.R
import mx.com.traineeapp.databinding.ActivityPinBinding
import mx.com.traineeapp.features.pin.viewmodel.PinViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PinActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pinViewModel.liveData.observe(this, { it.let(::process) })
        binding.btn.setOnClickListener {
            pinViewModel.dispatch(PinViewModel.Actions.SavePinAction(binding.ed1.text.toString()))
        }
    }

    private fun process(viewState: PinViewModel.ViewState?) {
        when(viewState){
            PinViewModel.ViewState.Loading -> TODO()
            is PinViewModel.ViewState.SavePinResult -> TODO()
            null -> TODO()
        }
    }

    private val pinViewModel: PinViewModel by viewModel()
}