package live.adabe.findmyblood.models.network

import live.adabe.findmyblood.models.Blood

data class BloodResponse(
    val status: String,
    val data: List<Blood>
)