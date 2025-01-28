package com.straccion.socialapp.android.coomon.util

import android.util.Log

//private const val CURRENT_BASE_URL = "http://10.0.2.2:8080/"
//private const val CURRENT_BASE_URL = "http://192.168.1.5:8080/" //celular
private const val CURRENT_BASE_URL = "http://192.168.1.11:8080/" //celular

fun String.toCurrentUrl(): String {
    return try {
        if (this.startsWith("http://")) {
            this // Si ya es una URL completa, la dejamos tal cual
        } else {
            "$CURRENT_BASE_URL$this"
        }
    } catch (error: Throwable) {
        Log.e("URLTransform", "Error transforming URL", error)
        this
    }
}