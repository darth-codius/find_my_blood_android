package live.adabe.findmyblood.models

import androidx.room.Entity

data class Blood(
    val bloodGroup: String,
    val units: Int,
)
