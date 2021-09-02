package live.adabe.findmyblood.models.network.search


import com.google.gson.annotations.SerializedName

data class HospitalSearch(
    var address: String,
    @SerializedName("_id")
    var id: String,
    var name: String,
    var state: String
)