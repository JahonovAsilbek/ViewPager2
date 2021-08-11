package uz.revolution.viewpagertask2.models

import java.io.Serializable

data class Photo(
    val color: String,
    val created_at: String,
    val downloads: Int,
    val exif: Exif,
    val height: Int,
    val id: String,
    val likes: Int,
    val links: Links,
    val location: Location,
    val updated_at: String,
    val urls: Urls,
    val user: User,
    val views: Int,
    val width: Int
):Serializable