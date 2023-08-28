package com.notesfinalproject.usecases

import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class GenerateRandomIdUseCase @Inject constructor(
    private val firestore: FirebaseFirestore,
) : IGenerateRandomIdUseCase {
    override suspend fun generate(path: String): String {
        val document = firestore.collection(path).document()
        return document.id
    }
}