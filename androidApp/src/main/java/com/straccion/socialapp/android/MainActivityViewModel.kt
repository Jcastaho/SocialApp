package com.straccion.socialapp.android

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import com.straccion.socialapp.android.coomon.datastore.UserSettings
import com.straccion.socialapp.android.coomon.datastore.toAuthResultData
import kotlinx.coroutines.flow.map

class MainActivityViewModel(
    private val dataStore: DataStore<UserSettings>
): ViewModel() {
    val authState = dataStore.data.map {
        it.toAuthResultData().token
    }
}