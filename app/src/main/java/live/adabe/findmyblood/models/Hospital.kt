package live.adabe.findmyblood.models

data class Hospital(
    val name: String,
    val email: String,
    val password: String,
    val phone_number: String,
    val address: String,
    val state: String,
    val motto: String,
    val imageUrl: String
)
