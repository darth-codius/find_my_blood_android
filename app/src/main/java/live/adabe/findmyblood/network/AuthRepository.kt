package live.adabe.findmyblood.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import live.adabe.findmyblood.models.network.LoginRequest
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.utils.Preferences

class AuthRepository(private val preferences: Preferences) {

    suspend fun loginHospital(loginRequest: LoginRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.authApi.loginHospital(loginRequest)
                preferences.saveToken(response.token)
                true
            } catch (t: Throwable) {
                Log.d("REPOSITORY", t.message.toString())
                false
            }
        }
    }


    suspend fun signUpHospital(signUpRequest: SignUpRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.authApi.signUpHospital(signUpRequest)
                preferences.saveId(response.data._id)
                true
            } catch (t: Throwable) {
                Log.d("REPOSITORY", t.message.toString())
                false
            }
        }
    }
}