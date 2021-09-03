package live.adabe.findmyblood.models.network.search


data class SearchResponse(
    var data: List<DataSearch>,
    var status: String
)