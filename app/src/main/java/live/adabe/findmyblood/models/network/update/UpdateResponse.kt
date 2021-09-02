package live.adabe.findmyblood.models.network.update

import live.adabe.findmyblood.models.network.Hospital

data class UpdateResponse(
    val data: Hospital,
    val status: String
)