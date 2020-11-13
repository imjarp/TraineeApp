package mx.com.traineeapp.core

data class Error (val errorCode: String,   // "NOT_FUNDS"
             val errorMessage: String,// "La tarjetas no tiene fondos"
             val target: String = "",
             val innerErrorList: List<Error> = emptyList(),
             val exception: Exception? = null) {
}