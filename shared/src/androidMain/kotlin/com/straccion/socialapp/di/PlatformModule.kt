//package com.straccion.socialapp.di
//
//import androidx.datastore.core.DataStoreFactory
//import com.straccion.socialapp.common.data.UserSettingsSerializer
//import com.straccion.socialapp.common.data.local.PREFERENCES_FILE_NAME
//import com.straccion.socialapp.common.data.local.UserPreferences
//import org.koin.dsl.module

//actual val platformModule = module {
//    single<UserPreferences> { AndroidUserPreferences(get()) }
//
//    single {
//        DataStoreFactory.create(
//            serializer = UserSettingsSerializer,
//            produceFile = {
//                androidContext().dataStoreFile(
//                    fileName = PREFERENCES_FILE_NAME
//                )
//            }
//        )
//    }
//}