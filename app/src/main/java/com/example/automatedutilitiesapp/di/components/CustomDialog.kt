package com.example.automatedutilitiesapp.di.components

import android.app.AlertDialog
import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import dmax.dialog.SpotsDialog

class CustomDialog {

    fun materialDialog(context: Context): MaterialDialog {
        return MaterialDialog(context)
    }

    fun progressDialog(context: Context, message: String): AlertDialog {
        return SpotsDialog.Builder()
            .setContext(context)
            .setMessage(message)
            .setCancelable(false)
            .build()
            .apply {
                show()
            }
    }

}