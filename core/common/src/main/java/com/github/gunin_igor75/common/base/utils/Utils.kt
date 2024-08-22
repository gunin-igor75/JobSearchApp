package com.github.gunin_igor75.common.base.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.github.gunin_igor75.common.R

object Utils {

    fun hasInternetConnection(context: Context?): Boolean {
        try {
            if (context == null) {
                return false
            }
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val activityNetwork = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(activityNetwork)?: return false
            return when {
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error ${e.message}")
            return false
        }
    }

    fun showAlertDialog(context: Context, message: String) {
        try {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.app_name)
            builder.setMessage(message)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            builder.setPositiveButton(BUTTON_POSITIVE){ dialog, _ ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.setCancelable(false)
        } catch (e: Exception) {
            Log.e(TAG, "Error ${e.message}")
            e.stackTrace
        }
    }

    private const val TAG = "Utils"
    private const val BUTTON_POSITIVE = "Ok"
}