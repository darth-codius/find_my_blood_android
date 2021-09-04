package live.adabe.findmyblood.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import live.adabe.findmyblood.models.Blood
import live.adabe.findmyblood.models.network.SearchRequest
import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.models.network.request.RecordsRequest
import live.adabe.findmyblood.models.network.request.Request
import live.adabe.findmyblood.models.network.search.DataSearch
import live.adabe.findmyblood.utils.Preferences

class BloodRepository(private val preferences: Preferences) {

    suspend fun getAllBlood(): List<Blood> {
        return withContext(Dispatchers.IO) {
            try {
                val response = preferences.getToken()?.let {
                    RetrofitProvider.bloodApi.getAllBlood(
                        it
                    )
                }
                response!!.data
            } catch (t: Throwable) {
                emptyList()
            }
        }
    }

    suspend fun addBlood(blood: BloodRequest) {
        withContext(Dispatchers.IO) {
            try {
                preferences.getToken()?.let {
                    RetrofitProvider.bloodApi.addBlood(it, blood)
                }
            } catch (t: Throwable) {

            }
        }
    }

    suspend fun searchBlood(searchRequest: SearchRequest): List<DataSearch> {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitProvider.bloodApi.searchBlood(preferences.getToken()!!, searchRequest).data
            } catch (t: Throwable) {
                emptyList()
            }
        }
    }

    suspend fun makeBloodRequest(bloodRequest: BloodRequest, id: String): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitProvider.requestApi.createBloodRequest(
                    preferences.getToken()!!,
                    id,
                    bloodRequest
                )
                (response.status == "success")
            } catch (t: Throwable) {
                false
            }
        }
    }

    suspend fun getIncomingRequests(): List<Request> {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitProvider.requestApi.getRecords(
                    preferences.getToken()!!,
                    RecordsRequest(preferences.getHospitalName()!!)
                ).recievedRequest
            } catch (t: Throwable) {
                emptyList()
            }
        }
    }

    suspend fun getSentRequests(): List<Request> {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitProvider.requestApi.getRecords(
                    preferences.getToken()!!,
                    RecordsRequest(preferences.getHospitalName()!!)
                ).sentRequest
            } catch (t: Throwable) {
                emptyList()
            }
        }
    }
}