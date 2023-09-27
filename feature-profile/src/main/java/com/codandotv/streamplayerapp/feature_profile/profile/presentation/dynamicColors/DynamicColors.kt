package com.codandotv.streamplayerapp.feature_profile.profile.presentation.dynamicColors

import android.graphics.Bitmap
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun getPaletteColor(image: Bitmap): List<Pair<Color, String>> {
    image.let {
        val palette: Palette =
            Palette.from(it).generate()

        return getPaletteColorList(palette)
    }
}

private fun getPaletteColorList(palette: Palette) = run {
    val properties = listOf(
        "lightVibrantSwatch",
        "darkVibrantSwatch",
        "lightMutedSwatch",
        "darkMutedSwatch",
        "mutedSwatch",
        "vibrantSwatch"
    )

    properties.map {
        val swatch = when (it) {
            "lightVibrantSwatch" -> palette.lightVibrantSwatch
            "darkVibrantSwatch" -> palette.darkVibrantSwatch
            "lightMutedSwatch" -> palette.lightMutedSwatch
            "darkMutedSwatch" -> palette.darkMutedSwatch
            "mutedSwatch" -> palette.mutedSwatch
            "vibrantSwatch" -> palette.vibrantSwatch
            else -> null
        }

        Pair(swatch?.let { currentColor -> Color(currentColor.rgb) }
            ?: Color.Black, it)
    }.toList()
}
