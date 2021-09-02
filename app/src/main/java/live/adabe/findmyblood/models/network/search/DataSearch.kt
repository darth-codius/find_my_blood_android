package live.adabe.findmyblood.models.network.search


import com.google.gson.annotations.SerializedName

data class DataSearch(
    var bloodGroup: String,
    var hospital: HospitalSearch,
    @SerializedName("_id")
    var id: String,
    var units: Int,
    @SerializedName("__v")
    var v: Int
)