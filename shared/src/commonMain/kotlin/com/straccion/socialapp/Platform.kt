package com.straccion.socialapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform