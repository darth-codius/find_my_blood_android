package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.network.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

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
}