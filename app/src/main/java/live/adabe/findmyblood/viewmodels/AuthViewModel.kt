package live.adabe.findmyblood.viewmodels

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import live.adabe.findmyblood.models.network.LoginRequest
import live.adabe.findmyblood.models.network.SignUpRequest
import live.adabe.findmyblood.network.AuthRepository
import live.adabe.findmyblood.network.RetrofitProvider
import live.adabe.findmyblood.utils.Preferences

class AuthViewModel(@SuppressLint("StaticFieldLeak") private val activity: Activity) : ViewModel() {
    private var preferences: Preferences = Preferences(activity)
    private var authRepository: AuthRepository = AuthRepository(preferences)

    fun loginHospital(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepository.loginHospital(loginRequest)
        }
    }

    fun signUpHospital(signUpRequest: SignUpRequest) {
        viewModelScope.launch {
            authRepository.signUpHospital(signUpRequest)
        }
    }
}