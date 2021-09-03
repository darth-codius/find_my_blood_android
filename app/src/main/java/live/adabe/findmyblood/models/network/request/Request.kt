package live.adabe.findmyblood.models.network.request

data class Request(
    val __v: Int,
    val _id: String,
    val blood: String,
    val hospital: String,
    val id: String,
    val requestedUnits: Int,
    val requestingHospital: String,
    val status: String,
    val timeStamp: Long
)