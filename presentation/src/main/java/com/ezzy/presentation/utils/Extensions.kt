package com.ezzy.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit
import kotlin.math.abs

private val PUNCTUATION = listOf(", ", "; ", ": ", " ")
fun String.smartTruncate(length: Int): String {
    val words = split("")
    var added = 0
    var hasMore = false
    val builder = StringBuilder()

    for (word in words) {
        if (builder.length > length) {
            hasMore = true
            break
        }
        builder.append(word)
        builder.append("")
        added += 1
    }
    PUNCTUATION.map {
        if (builder.endsWith(it)) {
            builder.replace(builder.length - it.length, builder.length, "")
        }
    }
    if (hasMore) builder.append("...")
    return builder.toString()
}


fun slideOutVerticallyEnterAnimation() =
    slideOutVertically(
        animationSpec = tween(700),
        targetOffsetY = { it }
    )

fun slideInVerticallyEnterAnimation() =
    slideInVertically(
        animationSpec = tween(700),
        initialOffsetY = { it }
    )

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

@Composable
inline fun<reified  T: ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)

}


fun String.dateStringToLong(): Long {
    val pattern = "MM/dd/yyyy"
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())

    try {
        val date = sdf.parse(this)
        return date?.time ?: 0L
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return 0L
}

@SuppressLint("SimpleDateFormat")
fun Long.formatTimeToSmallDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return format.format(date)
}


fun Long.getDate(): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this

    // Set time components to zero
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}


fun Pair<Long, Long>.getTotalDays(): Long {
    val millisDifference = first - second
    val daysDifference = TimeUnit.MILLISECONDS.toDays(millisDifference)
    return abs(daysDifference)
}