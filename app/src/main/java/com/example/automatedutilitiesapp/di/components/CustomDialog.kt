package com.example.automatedutilitiesapp.di.components

import android.app.AlertDialog
import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.example.automatedutilitiesapp.R
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
            .setTheme(R.style.customDialogView)
            .build()
            .apply {
                show()
            }
    }

}