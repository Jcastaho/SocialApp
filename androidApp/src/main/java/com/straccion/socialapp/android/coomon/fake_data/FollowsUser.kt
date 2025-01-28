package com.straccion.socialapp.android.coomon.fake_data

data class FollowsUser(
    val id: Long,
    val name: String,
    val bio: String = "",
    val imageUrl: String,
    val isFollowing: Boolean = false
)

val sampleUsers = listOf(
    FollowsUser(
        id = 1,
        name = "Mr Dip",
        imageUrl = "https://picsum.photos/510"
    ),
    FollowsUser(
        id = 2,
        name = "John Cena",
        imageUrl = "https://picsum.photos/520"
    ),
    FollowsUser(
        id = 3,
        name = "Cristiano",
        imageUrl = "https://picsum.photos/530"
    ),
    FollowsUser(
        id = 4,
        name = "L. James",
        imageUrl = "https://picsum.photos/540"
    )
)