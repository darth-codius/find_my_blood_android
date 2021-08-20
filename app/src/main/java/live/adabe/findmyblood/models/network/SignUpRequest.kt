package live.adabe.findmyblood.models.network

data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val confirmPassword: String
)
//ame, email, password and confirmPassword
