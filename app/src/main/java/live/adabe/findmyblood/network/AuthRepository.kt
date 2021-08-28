package live.adabe.findmyblood.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import live.adabe.findmyblood.models.network.Hospital
import live.adabe.findmyblood.models.network.LoginRequest
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.models.network.UpdateRequest
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.utils.Resource

class AuthRepository(private val preferences: Preferences) {

    suspend fun loginHospital(loginRequest: LoginRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.authApi.loginHospital(loginRequest)
                preferences.saveToken(response.token)
                preferences.setHospitalName(response.data.name)
                preferences.setIsLoggedIn(true)
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

    suspend fun updateHospital(updateRequest: UpdateRequest): Resource<Hospital> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.authApi.updateHospital(
                    preferences.getId()!!,
                    preferences.getToken()!!,
                    updateRequest = updateRequest
                )
                Resource.success(response.data)
            } catch (t: Throwable) {
                Resource.error(null, t.message)
            }
        }
    }
}