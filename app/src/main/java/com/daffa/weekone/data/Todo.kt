package com.daffa.weekone.data

import java.io.Serializable

data class Todo(
    val userId: String,
    val id: String,
    val title: String,
    val complete: Boolean
): Serializable
