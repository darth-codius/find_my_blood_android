package live.adabe.findmyblood.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class Preferences(private val activity: Activity) {
    private val sharedPref: SharedPreferences =
        activity.getSharedPreferences("find_my_blood", Context.MODE_PRIVATE)

    fun saveId(id: String) {
        with(sharedPref.edit()) {
            putString("id", id)
            apply()
        }
    }

    fun saveToken(token: String) {
        with(sharedPref.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun getId() = sharedPref.getString("id", null)
    fun getToken() = sharedPref.getString("token", null)
}