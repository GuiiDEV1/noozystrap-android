package com.noozystrap.mobile.service

import android.app.ActivityManager
import android.content.Context
import android.os.Build

data class HardwareReport(
    val deviceModel: String,
    val processor: String,
    val totalRamGb: String,
    val androidVersion: String,
    val recommendedPresetId: String
)

object HardwareDetector {

    fun detect(context: Context): HardwareReport {
        val model = "${Build.MANUFACTURER.replaceFirstChar { it.uppercase() }} ${Build.MODEL}"
        val soc = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            Build.SOC_MODEL.ifBlank { Build.HARDWARE }
        } else {
            Build.HARDWARE
        }

        val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager?.getMemoryInfo(memInfo)
        val totalRamBytes = memInfo.totalMem
        val totalRamGb = "%.1f GB".format(totalRamBytes / (1024.0 * 1024.0 * 1024.0))

        val recommended = when {
            totalRamBytes < 3.5 * 1024 * 1024 * 1024 -> "potato"
            soc.contains("snapdragon", ignoreCase = true) || soc.contains("sm8", ignoreCase = true) -> "vulkan"
            else -> "competitive"
        }

        return HardwareReport(
            deviceModel = model,
            processor = soc.ifBlank { "Octa-Core ARM" },
            totalRamGb = totalRamGb,
            androidVersion = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})",
            recommendedPresetId = recommended
        )
    }
}
