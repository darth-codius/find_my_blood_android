package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.Hospital
import live.adabe.findmyblood.models.network.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthApi {

    @POST("hospital/signup")
    suspend fun signUpHospital(@Body signUpRequest: SignUpRequest): SignupResponse

    @POST("hospital/login")
    suspend fun loginHospital(@Body loginRequest: LoginRequest): LoginResponse

    @POST("hospital/edit/:id")
    suspend fun updateHospital(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body updateRequest: UpdateRequest
    )
}