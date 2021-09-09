package live.adabe.findmyblood.models.network.request

data class RequestData(
    val recievedRequest: List<ReceivedRequest>,
    val sentRequest: List<Request>,
)
