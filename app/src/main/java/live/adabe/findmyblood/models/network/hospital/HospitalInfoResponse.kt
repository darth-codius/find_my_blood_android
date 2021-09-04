package live.adabe.findmyblood.models.network.hospital

import live.adabe.findmyblood.models.network.Hospital

data class HospitalInfoResponse(
    val status: String,
    val data: Hospital
)
