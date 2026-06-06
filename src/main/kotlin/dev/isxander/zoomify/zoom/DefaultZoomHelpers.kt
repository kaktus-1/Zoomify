package dev.isxander.zoomify.zoom

import dev.isxander.yacl3.config.v3.value
import dev.isxander.zoomify.Zoomify
import dev.isxander.zoomify.config.ZoomifySettings
import net.minecraft.util.Mth

fun RegularZoomHelper(settings: ZoomifySettings) = ZoomHelper(
    TransitionInterpolator(
        settings.zoomInTransition::value,
        settings.zoomOutTransition::value,
        settings.zoomInTime::value,
        settings.zoomOutTime::value
    ),
    SmoothInterpolator {
        Mth.lerp(
            settings.scrollZoomSmoothness.value / 100.0,
            1.0,
            0.1
        )
    },
    initialZoom = { settings.initialZoom.value.toDouble() },
    zoomPerStep = { settings.zoomPerStep.value.toDouble() },
    maxScrollTiers = { settings.scrollStepCount.value.toInt() },
)

fun SecondaryZoomHelper(settings: ZoomifySettings) = ZoomHelper(
    TimedInterpolator(settings.secondaryZoomInTime::value, settings.secondaryZoomOutTime::value),
    InstantInterpolator,
    initialZoom = { settings.secondaryZoomAmount.value.toDouble() },
    zoomPerStep = { 100.0 },
    maxScrollTiers = { 0 },
)
