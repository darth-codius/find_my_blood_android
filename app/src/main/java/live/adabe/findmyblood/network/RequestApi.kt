package live.adabe.findmyblood.network

import live.adabe.findmyblood.models.network.CreateRequestResponse
import live.adabe.findmyblood.models.network.SearchRequest
import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.models.network.search.SearchResponse
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RequestApi {

    @POST("request/all")
    suspend fun getRecords(
        @Header("Authorization") token: String,
        searchRequest: SearchRequest
    ): SearchResponse

    @POST("request/create/{id}")
    suspend fun createBloodRequest(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        bloodRequest: BloodRequest
    ): CreateRequestResponse
}