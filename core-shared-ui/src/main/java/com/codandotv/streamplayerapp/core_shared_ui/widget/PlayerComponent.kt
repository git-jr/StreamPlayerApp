package com.codandotv.streamplayerapp.core_shared_ui.widget

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.codandotv.streamplayerapp.core.shared.ui.R
import com.codandotv.streamplayerapp.core_shared_ui.theme.ThemePreviews
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class PlayerIconData(
    @DrawableRes val iconRes: Int,
    @StringRes val contentDescriptionRes: Int,
)

@Composable
fun PlayerComponent(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var isPlayerPlaying by remember { mutableStateOf(true) }

    var playerPosition by remember { mutableStateOf(0L) }

    val playerControlData by remember {
        derivedStateOf {
            if (isPlayerPlaying) {
                PlayerIconData(R.drawable.ic_pause, R.string.player_pause)
            } else {
                PlayerIconData(R.drawable.ic_play, R.string.player_continue)
            }
        }
    }

    val exoplayer = remember {
        val mediaItem = MediaItem.Builder()
            .setUri(url)
            .build()

        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()

            playWhenReady = true

            addListener(
                object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        isPlayerPlaying = isPlaying
                    }
                }
            )
        }
    }

    val playerProgress by remember {
        derivedStateOf { (playerPosition / exoplayer.contentDuration.toDouble()).toFloat() }
    }

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            factory = {
                PlayerView(it).apply {
                    player = exoplayer
                    useController = false
                }
            }
        )

        IconButton(
            modifier = Modifier.align(Alignment.Center),
            onClick = {
                if (isPlayerPlaying) {
                    exoplayer.pause()
                } else {
                    exoplayer.play()
                }
            }
        ) {
            Icon(
                painter = painterResource(id = playerControlData.iconRes),
                contentDescription = stringResource(id = playerControlData.contentDescriptionRes)
            )
        }

        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            value = playerProgress,
            onValueChange = {}
        )
    }

    LaunchedEffect(
        key1 = isPlayerPlaying,
        block = {
            coroutineScope.launch {
                while (isPlayerPlaying) {
                    delay(500L)
                    playerPosition += 500L
                }
            }
        }
    )

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            exoplayer.release()
        }
    })
}

@Composable
@ThemePreviews
fun PlayerComponentPreview() {
    PlayerComponent(
        url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        modifier = Modifier.fillMaxWidth()
    )
}