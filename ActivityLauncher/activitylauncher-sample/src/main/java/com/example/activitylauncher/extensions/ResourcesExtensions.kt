package com.example.activitylauncher.extensions

import android.content.res.Resources
import androidx.annotation.IdRes

fun Resources.prettyPrintId(@IdRes id: Int): String {
    return "${getResourceTypeName(id)}:${getResourceEntryName(id)}"
}
