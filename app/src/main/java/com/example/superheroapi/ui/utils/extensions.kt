package com.example.superheroapi.ui.utils

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import coil.imageLoader
import coil.request.ImageRequest
import coil.size.Scale
import com.example.superheroapi.R
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun ImageView.loadCoil(url: String?, colorShimmer: String) {
    val drawable = ContextCompat.getDrawable(this.context, R.drawable.avd_dashboard)
    (drawable as Animatable).start()
    val request = ImageRequest.Builder(this.context)
        .data("$url")
        .crossfade(true)
        .error(R.drawable.error)
        .placeholder(shimmerDrawable(colorShimmer))
        .scale(Scale.FILL)
        .target(
            onStart = { placeholder ->
                this.setImageDrawable(placeholder)
            },
            onSuccess = { result ->
                this.setImageDrawable(result)
            },
            onError = { error ->
                this.setImageDrawable(error)
            }
        )
        .build()
    this.context.imageLoader.enqueue(request)
}


fun shimmerDrawable(color: String): ShimmerDrawable {
    val shimmer = Shimmer.ColorHighlightBuilder()
        .setBaseColor(Color.parseColor(color))
        .setBaseAlpha(1F)
        .setHighlightColor(Color.parseColor("#E7E7E7"))
        .setHighlightAlpha(1F)
        .setDropoff(100F)
        .build()
    val shimmerDrawable = ShimmerDrawable()
    shimmerDrawable.setShimmer(shimmer)
    return shimmerDrawable
}


