package live.adabe.findmyblood.models.network.request

import live.adabe.findmyblood.models.network.Hospital

data class Request(
    val __v: Int,
    val _id: String,
    val blood: String,
    val hospital: Hospital,
    val id: String,
    val requestedUnits: Int,
    val requestingHospital: String,
    val status: String,
    val timeStamp: Long
)