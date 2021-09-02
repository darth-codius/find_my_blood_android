package live.adabe.findmyblood.models.network.signup

import live.adabe.findmyblood.models.network.Data

data class SignUpResponse(
    val data: Data,
    val message: String,
    val status: String
)