package com.straccion.socialapp.android.coomon.util

import com.straccion.socialapp.acount.domain.model.Profile
import com.straccion.socialapp.android.coomon.util.Constants.EVENT_BUS_BUFFER_CAPACITY
import com.straccion.socialapp.common.domain.model.Post
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


object EventBus {
    private val _events = MutableSharedFlow<Event>(extraBufferCapacity = EVENT_BUS_BUFFER_CAPACITY)
    val events = _events.asSharedFlow()

    suspend fun send(event: Event) {
        _events.emit(event)
    }
}
sealed interface Event{
    data class PostUpdated(val post: Post): Event
    data class ProfileUpdated(val profile: Profile): Event
    data class PostCreated(val post: Post): Event
}