package live.adabe.findmyblood.viewmodels

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.adabe.findmyblood.models.network.Hospital
import live.adabe.findmyblood.models.network.login.LoginRequest
import live.adabe.findmyblood.models.network.signup.SignUpRequest
import live.adabe.findmyblood.network.AuthRepository
import live.adabe.findmyblood.utils.Preferences
import live.adabe.findmyblood.utils.Resource
import okhttp3.MultipartBody

class AuthViewModel(activity: Activity) : ViewModel() {
    private var preferences: Preferences = Preferences(activity)
    private var authRepository: AuthRepository = AuthRepository(preferences)

    val isLoginSuccessful = MutableLiveData<Boolean>(false)
    val isSignUpSuccessful = MutableLiveData<Boolean>(false)
    val updateResponseResource = MutableLiveData<Resource<Hospital>>()

    fun loginHospital(loginRequest: LoginRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            isLoginSuccessful.postValue(authRepository.loginHospital(loginRequest))
        }
    }

    fun signUpHospital(signUpRequest: SignUpRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            isSignUpSuccessful.postValue(authRepository.signUpHospital(signUpRequest))
        }
    }

    fun updateHospital(updateRequest: MultipartBody){
        viewModelScope.launch {
            updateResponseResource.postValue(Resource.loading(null))
            updateResponseResource.postValue(authRepository.updateHospital(updateRequest))
        }
    }
}