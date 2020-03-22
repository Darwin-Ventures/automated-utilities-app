package com.example.automatedutilitiesapp.di.components

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import es.dmoral.toasty.Toasty

class Messaging(val context: Context) {

    /**
     * Shows a message using a snackbar
     */
    fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    /**
     * Shows a toast message
     * @param type -> type of toast (accepted types: normal, error, success, warning)
     */
    fun showToast(type: String, message: String){
        when(type){
            "normal" ->  Toasty.normal(context, message, Toasty.LENGTH_SHORT).show()
            "error" ->  Toasty.error(context, message, Toasty.LENGTH_SHORT).show()
            "success" ->  Toasty.success(context, message, Toasty.LENGTH_SHORT).show()
            "warning" ->  Toasty.warning(context, message, Toasty.LENGTH_SHORT).show()
            else ->  Toasty.info(context, message, Toasty.LENGTH_SHORT).show()
        }
    }

}