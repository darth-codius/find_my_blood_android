package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.Hospital
import live.adabe.findmyblood.models.network.LoginRequest
import live.adabe.findmyblood.models.network.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("hospital/signup")
    suspend fun signUpHospital(@Body signUpRequest: SignUpRequest): Hospital

    @POST("hospital/login")
    suspend fun loginHospital(@Body loginRequest: LoginRequest): Hospital
}