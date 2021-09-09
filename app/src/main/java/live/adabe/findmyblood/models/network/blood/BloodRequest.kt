package live.adabe.findmyblood.models.network.blood

data class BloodRequest(
    val id: String? = null,
    val bloodGroup: String,
    val units: Int,
)
