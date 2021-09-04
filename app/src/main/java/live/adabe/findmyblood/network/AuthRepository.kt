package live.adabe.findmyblood.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import live.adabe.findmyblood.models.network.Hospital
import live.adabe.findmyblood.models.network.login.LoginRequest
import live.adabe.findmyblood.models.network.signup.SignUpRequest
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.utils.Resource
import okhttp3.MultipartBody

class AuthRepository(private val preferences: Preferences) {

    suspend fun loginHospital(loginRequest: LoginRequest): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.authApi.loginHospital(loginRequest)
                preferences.saveToken(response.token)
                preferences.setHospitalName(response.data.name)
                preferences.setIsLoggedIn(true)
                preferences.saveId(response.data._id)
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

    suspend fun updateHospital(updateRequest: MultipartBody): Resource<Hospital> {
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

    suspend fun getHospitalInfo(): Hospital?{
        return withContext(Dispatchers.IO){
            try {
                RetrofitProvider.authApi.getHospitalInfo(preferences.getId()!!, preferences.getToken()!!).data
            }catch (t: Throwable){
                null
            }
        }
    }
}