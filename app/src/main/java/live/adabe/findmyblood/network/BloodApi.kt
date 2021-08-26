package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.network.BloodRequest
import live.adabe.findmyblood.models.network.BloodResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BloodApi {

    @GET("blood/all")
    suspend fun getAllBlood(@Header("Authorization") token: String,): BloodResponse

    @POST("blood/add")
    suspend fun addBlood(@Header("Authorization") token: String, @Body bloodRequest: BloodRequest): BloodResponse
}