package live.adabe.findmyblood.viewmodels

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val activity: Activity, private val classIndex: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (classIndex) {
            1 -> AuthViewModel(activity) as T
            2 -> MainViewModel(activity) as T
            else -> Any() as T
        }
    }
}