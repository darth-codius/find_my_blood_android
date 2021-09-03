package live.adabe.findmyblood.models.network.request

data class RequestResponse(
    val recievedRequest: List<Request>,
    val sentRequest: List<Request>,
    val status: String
)