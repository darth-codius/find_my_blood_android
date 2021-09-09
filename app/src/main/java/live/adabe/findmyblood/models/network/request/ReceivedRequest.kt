package live.adabe.findmyblood.models.network.request


import com.google.gson.annotations.SerializedName

data class ReceivedRequest(
    var blood: String,
    @SerializedName("_id")
    var id: String,
    var requestedUnits: Int,
    var requestingHospital: String,
    var status: String,
    var timeStamp: Long,
    @SerializedName("__v")
    var v: Int
)