package live.adabe.findmyblood.models.network

data class SignupResponse(
    val `data`: Data,
    val message: String,
    val status: String
)