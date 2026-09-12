package com.noozystrap.mobile.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noozystrap.mobile.data.Preset
import com.noozystrap.mobile.data.PresetRepository
import com.noozystrap.mobile.service.HardwareReport
import com.noozystrap.mobile.ui.components.HeaderCard
import com.noozystrap.mobile.ui.components.PresetCard
import com.noozystrap.mobile.ui.components.SettingToggle
import com.noozystrap.mobile.ui.theme.*

@Composable
fun HomeScreen(
    hardware: HardwareReport,
    isRobloxInstalled: Boolean,
    onLaunchRoblox: (Preset, Map<String, Any>) -> Unit
) {
    var selectedPreset by remember {
        mutableStateOf(
            PresetRepository.ALL_PRESETS.firstOrNull { it.id == hardware.recommendedPresetId }
                ?: PresetRepository.POTATO_MODE
        )
    }

    // Switches adicionais
    var forceVulkan by remember { mutableStateOf(selectedPreset.flags["FFlagGraphicsPreferVulkan"] == true) }
    var disableShadows by remember { mutableStateOf(selectedPreset.flags["FFlagDebugGraphicsDisableShadows"] == true) }
    var unlock120Fps by remember { mutableStateOf(selectedPreset.flags["DFIntTaskSchedulerTargetFps"] == 120) }

    val activeFlags = remember(selectedPreset, forceVulkan, disableShadows, unlock120Fps) {
        val map = selectedPreset.flags.toMutableMap()
        if (forceVulkan) map["FFlagGraphicsPreferVulkan"] = true
        if (disableShadows) {
            map["FFlagDebugGraphicsDisableShadows"] = true
            map["FIntRenderShadowIntensity"] = 0
        }
        if (unlock120Fps) map["DFIntTaskSchedulerTargetFps"] = 120
        map
    }

    Scaffold(
        containerColor = BgDark,
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, BgDark.copy(alpha = 0.95f), BgDark)
                        )
                    )
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onLaunchRoblox(selectedPreset, activeFlags) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(14.dp)),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentPurple)
                ) {
                    Text(
                        text = "🚀 APLICAR & INICIAR ROBLOX",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                HeaderCard(hardware = hardware, isRobloxInstalled = isRobloxInstalled)
            }

            item {
                Text(
                    text = "PRESETS DE DESEMPENHO",
                    color = AccentMint,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 6.dp, bottom = 2.dp)
                )
            }

            items(PresetRepository.ALL_PRESETS) { preset ->
                PresetCard(
                    preset = preset,
                    isSelected = selectedPreset.id == preset.id,
                    onClick = {
                        selectedPreset = preset
                        forceVulkan = preset.flags["FFlagGraphicsPreferVulkan"] == true
                        disableShadows = preset.flags["FFlagDebugGraphicsDisableShadows"] == true
                        unlock120Fps = preset.flags["DFIntTaskSchedulerTargetFps"] == 120
                    }
                )
            }

            item {
                Text(
                    text = "AJUSTES ADICIONAIS",
                    color = AccentMint,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(top = 10.dp, bottom = 2.dp)
                )
            }

            item {
                SettingToggle(
                    title = "Forçar Motor Vulkan",
                    subtitle = "Gera menos calor e mantém FPS estável em chips modernos.",
                    checked = forceVulkan,
                    onCheckedChange = { forceVulkan = it }
                )
            }

            item {
                SettingToggle(
                    title = "Remover Sombras Dinâmicas",
                    subtitle = "Alivia a carga de GPU e melhora a visibilidade em combates.",
                    checked = disableShadows,
                    onCheckedChange = { disableShadows = it }
                )
            }

            item {
                SettingToggle(
                    title = "Desbloquear 120 FPS",
                    subtitle = "Aumenta o limite de 60 para telas de 90Hz ou 120Hz.",
                    checked = unlock120Fps,
                    onCheckedChange = { unlock120Fps = it }
                )
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}
