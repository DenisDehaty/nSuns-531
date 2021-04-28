package com.ddehaty.nsuns531

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Ohp(@PrimaryKey(autoGenerate = true) val id: Int = 0, val weight: String, val timestamp: Long = Date().time) {
}