package live.adabe.findmyblood.viewmodels

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import live.adabe.findmyblood.models.network.LoginRequest
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.network.AuthRepository
import live.adabe.findmyblood.network.RetrofitProvider
import live.adabe.findmyblood.utils.Preferences

class AuthViewModel(activity: Activity) : ViewModel() {
    private var preferences: Preferences = Preferences(activity)
    private var authRepository: AuthRepository = AuthRepository(preferences)

    val isLoginSuccessful = MutableLiveData<Boolean>(false)
    val isSignUpSuccessful = MutableLiveData<Boolean>(false)

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
}