package com.notesfinalproject.models

data class UserModel(
    val id: String,
    val name: String,
    val email: String
) {
    constructor() : this("", "", "")
}