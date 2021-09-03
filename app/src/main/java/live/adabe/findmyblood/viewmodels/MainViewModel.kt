package live.adabe.findmyblood.viewmodels

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import live.adabe.findmyblood.models.Blood
import live.adabe.findmyblood.models.network.blood.BloodRequest
import live.adabe.findmyblood.models.network.request.Request
import live.adabe.findmyblood.network.BloodRepository
import live.adabe.findmyblood.utils.Preferences

class MainViewModel(activity: Activity) : ViewModel() {
    private val preferences = Preferences(activity)
    private val repository = BloodRepository(preferences)

    val bloodLiveData = liveData<List<Blood>> {
        val data = repository.getAllBlood()
        emit(data)
    }

    val incomingRequestLiveData  = liveData {
        emit(repository.getIncomingRequests())
    }

    val sentRequestsLiveData = liveData {
        emit(repository.getSentRequests())
    }

    fun addBlood(blood: BloodRequest){
        viewModelScope.launch {
            repository.addBlood(blood)
        }
    }



}