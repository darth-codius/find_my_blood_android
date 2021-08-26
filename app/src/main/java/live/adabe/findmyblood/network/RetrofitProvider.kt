package live.adabe.findmyblood.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {
    private val instance =
        Retrofit.Builder().baseUrl("https://find-my-blood.herokuapp.com/hospital/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    val authApi = instance.create(AuthApi::class.java)
    val bloodApi = instance.create(BloodApi::class.java)
}