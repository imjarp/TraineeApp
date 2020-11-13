package mx.com.traineeapp.features.pin.services

import android.content.Context
import androidx.core.content.edit
import java.util.*


/// Esta implementación podria cambiar a BD sin problemas
class PinLocalRepository(context: Context) : IPinLocalRepository {

    private val preferences =
        context.getSharedPreferences("Shared", Context.MODE_PRIVATE)

    private val editor = preferences.edit()

    override fun save(lastModification: Date) {
        preferences.edit(true){
            putLong(KEY_LAST_MODIF, lastModification.time)
        }

    }

    override fun clearValues() {
        preferences.edit().clear().apply()
    }

    companion object {
        const val KEY_LAST_MODIF = "KEY_LAST_MODIF"

    }
}

interface IPinLocalRepository {

    fun save(lastModification: Date)

    fun clearValues()
}