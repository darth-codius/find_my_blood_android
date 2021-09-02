package live.adabe.findmyblood.models.network.update

data class UpdateRequest(
    val phoneNumber: String?,
    val address: String?,
    val motto: String?,
    val state: String?,
    val name: String?
)
