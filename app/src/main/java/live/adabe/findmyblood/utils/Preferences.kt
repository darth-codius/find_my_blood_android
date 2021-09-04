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

    fun saveImage(url: String) {
        with(sharedPref.edit()) {
            putString("url", url)
            apply()
        }
    }

    fun saveToken(token: String) {
        with(sharedPref.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun setIsLoggedIn(isLoggedIn: Boolean) {
        with(sharedPref.edit()) {
            putBoolean("logged_in", isLoggedIn)
            apply()
        }
    }

    fun setHospitalName(name: String) {
        with(sharedPref.edit()) {
            putString("name", name)
            apply()
        }
    }

    fun getIsLoggedIn() = sharedPref.getBoolean("logged_in", false)

    fun getId() = sharedPref.getString("id", null)
    fun getToken() = sharedPref.getString("token", null)
    fun getHospitalName() = sharedPref.getString("name", null)
    fun getImage() = sharedPref.getString("url", null)

    fun clear() {
        with(sharedPref.edit()) {
            remove("id")
            remove("token")
            remove("name")
            setIsLoggedIn(false)
            apply()
        }
    }
}