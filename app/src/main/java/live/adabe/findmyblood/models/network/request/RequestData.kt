package live.adabe.findmyblood.models.network.request

data class RequestData(
    val recievedRequest: List<Request>,
    val sentRequest: List<Request>,
)
