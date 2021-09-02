package live.adabe.findmyblood.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import live.adabe.findmyblood.models.Blood
import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.utils.Preferences

class BloodRepository(private val preferences: Preferences) {

    suspend fun getAllBlood(): List<Blood>{
        return withContext(Dispatchers.IO){
            try {
                val response = preferences.getToken()?.let {
                    RetrofitProvider.bloodApi.getAllBlood(
                        it
                    )
                }
                response!!.data
            }catch (t: Throwable){
                listOf<Blood>()
            }
        }
    }

    suspend fun addBlood(blood: BloodRequest){
        withContext(Dispatchers.IO){
            try {
                preferences.getToken()?.let {
                    RetrofitProvider.bloodApi.addBlood(it, blood)
                }
            }catch (t: Throwable){

            }
        }
    }
}