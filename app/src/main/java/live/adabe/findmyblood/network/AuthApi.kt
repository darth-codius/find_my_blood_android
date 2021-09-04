package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.network.hospital.HospitalInfoResponse
import live.adabe.findmyblood.models.network.login.LoginRequest
import live.adabe.findmyblood.models.network.login.LoginResponse
import live.adabe.findmyblood.models.network.signup.SignUpRequest
import live.adabe.findmyblood.models.network.signup.SignUpResponse
import live.adabe.findmyblood.models.network.update.UpdateResponse
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthApi {

    @POST("signup")
    suspend fun signUpHospital(@Body signUpRequest: SignUpRequest): SignUpResponse

    @POST("login")
    suspend fun loginHospital(@Body loginRequest: LoginRequest): LoginResponse

    @POST("edit/{id}")
    suspend fun updateHospital(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body updateRequest: RequestBody
    ): UpdateResponse

    @GET("{id}")
    fun getHospitalInfo(
        @Path("id") id: String,
        @Header("Authorization") token: String,
    ): HospitalInfoResponse
}