package com.thondigital.nc.data.remote

import com.thondigital.nc.data.remote.responses.EventDetailsResponse
import com.thondigital.nc.data.remote.responses.HomeResponse
import com.thondigital.nc.domain.repository.FirestoreRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FireStoreRepositoryImpl : FirestoreRepository {
    private val db = Firebase.firestore
    private val eventsCollection = db.collection("events")

    override suspend fun getHome(): HomeResponse {
        try {
            val events =
                eventsCollection.get()
            return HomeResponse(
                events =
                    events.documents.map {
                        it.data<EventDetailsResponse>().apply { id = it.id }
                    },
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun getEventById(eventId: String): EventDetailsResponse {
        try {
            val event =
                eventsCollection.document(eventId).get()
            return event.data()
        } catch (e: Exception) {
            throw e
        }
    }
}
