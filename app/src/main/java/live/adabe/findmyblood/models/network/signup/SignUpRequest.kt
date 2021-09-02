package live.adabe.findmyblood.models.network.signup

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
