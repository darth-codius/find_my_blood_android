package live.adabe.findmyblood.models

import androidx.room.Entity


data class Blood(
    val bloodType: String,
    val units: Int,
)
