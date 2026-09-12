package com.noozystrap.mobile

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.noozystrap.mobile.data.Preset
import com.noozystrap.mobile.service.FastFlagExporter
import com.noozystrap.mobile.service.HardwareDetector
import com.noozystrap.mobile.service.RobloxLauncher
import com.noozystrap.mobile.ui.screens.HomeScreen
import com.noozystrap.mobile.ui.theme.BgDark
import com.noozystrap.mobile.ui.theme.NoozyStrapTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkStoragePermission()

        val hardware = HardwareDetector.detect(this)
        val isRobloxInstalled = RobloxLauncher.isRobloxInstalled(this)

        setContent {
            NoozyStrapTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BgDark
                ) {
                    HomeScreen(
                        hardware = hardware,
                        isRobloxInstalled = isRobloxInstalled,
                        onLaunchRoblox = { preset, activeFlags ->
                            handleApplyAndLaunch(preset, activeFlags)
                        }
                    )
                }
            }
        }
    }

    private fun handleApplyAndLaunch(preset: Preset, activeFlags: Map<String, Any>) {
        val result = FastFlagExporter.exportFlags(this, activeFlags)

        if (result.success) {
            Toast.makeText(this, "${preset.title} aplicado!", Toast.LENGTH_SHORT).show()
            val launched = RobloxLauncher.launchRoblox(this)
            if (!launched) {
                Toast.makeText(
                    this,
                    "Roblox não instalado ou bloqueado. Instale o APK compatível!",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(this, "Erro: ${result.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                try {
                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                    intent.data = Uri.parse("package:$packageName")
                    startActivity(intent)
                } catch (e: Exception) {
                    val fallback = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
                    startActivity(fallback)
                }
            }
        }
    }
}
