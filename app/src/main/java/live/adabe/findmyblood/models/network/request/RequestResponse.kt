package live.adabe.findmyblood.models.network.request

data class RequestResponse(
    val message: String,
    val data: RequestData,
    val status: String
)