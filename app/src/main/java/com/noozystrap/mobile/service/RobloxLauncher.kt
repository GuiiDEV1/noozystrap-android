package com.noozystrap.mobile.service

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

object RobloxLauncher {

    const val ROBLOX_PACKAGE = "com.roblox.client"

    fun isRobloxInstalled(context: Context): Boolean {
        return try {
            context.packageManager.getPackageInfo(ROBLOX_PACKAGE, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun launchRoblox(context: Context): Boolean {
        return try {
            val pm = context.packageManager
            val intent = pm.getLaunchIntentForPackage(ROBLOX_PACKAGE)
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                true
            } else {
                // Fallback: tentar abrir via protocolo robloxmobile://
                val protocolIntent = Intent(Intent.ACTION_VIEW, Uri.parse("robloxmobile://"))
                protocolIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(protocolIntent)
                true
            }
        } catch (e: Exception) {
            false
        }
    }
}
