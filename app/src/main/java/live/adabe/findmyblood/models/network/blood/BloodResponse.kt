package live.adabe.findmyblood.models.network.blood

import live.adabe.findmyblood.models.Blood

data class BloodResponse(
    val status: String,
    val data: List<Blood>
)