package live.adabe.findmyblood.models.network

data class LoginResponse(
    val data: Data,
    val status: String,
    val token: String
)