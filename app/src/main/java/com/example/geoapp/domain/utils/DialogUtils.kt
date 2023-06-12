package com.example.geoapp.domain.utils

import android.content.Context
import androidx.annotation.StringRes
import com.afollestad.materialdialogs.MaterialDialog

class DialogUtils {

    fun showDialog(
        @StringRes titleResId: Int,
        @StringRes messageResId: Int,
        context: Context
    ) {
        MaterialDialog(context).show {
            title(titleResId)
            message(messageResId)
        }
    }
}