package com.example.gmaillayout.recylerview

import java.time.LocalDateTime

data class EmailData(
    val sender: String,
    val subject: String,
    val message: String,
    val dateTime: LocalDateTime,
    val isFavor: Boolean)
