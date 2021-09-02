package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.models.network.blood.BloodResponse
import live.adabe.findmyblood.models.network.SearchRequest
import live.adabe.findmyblood.models.network.search.SearchResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface BloodApi {

    @GET("blood/all")
    suspend fun getAllBlood(@Header("Authorization") token: String): BloodResponse

    @POST("blood/add")
    suspend fun addBlood(
        @Header("Authorization") token: String,
        @Body bloodRequest: BloodRequest
    ): BloodResponse

    @POST("blood/search")
    suspend fun searchBlood(
        @Header("Authorization") token: String,
        searchRequest: SearchRequest
    ): SearchResponse
}