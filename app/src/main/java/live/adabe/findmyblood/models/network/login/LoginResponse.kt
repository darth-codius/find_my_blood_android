package live.adabe.findmyblood.models.network.login

import live.adabe.findmyblood.models.network.Data

data class LoginResponse(
    val data: Data,
    val status: String,
    val token: String
)