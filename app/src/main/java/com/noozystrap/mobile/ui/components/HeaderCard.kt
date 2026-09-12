package com.noozystrap.mobile.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.noozystrap.mobile.service.HardwareReport
import com.noozystrap.mobile.ui.theme.*

@Composable
fun HeaderCard(
    hardware: HardwareReport,
    isRobloxInstalled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                Brush.horizontalGradient(
                    colors = listOf(SurfaceRaised, SurfaceDark)
                )
            )
            .border(1.dp, BorderDark, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "NOOZYSTRAP MOBILE",
                        color = AccentPurple,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = hardware.deviceModel,
                        color = TextPrimary,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Badge de Status do Roblox
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isRobloxInstalled) AccentMint.copy(alpha = 0.15f)
                            else AccentAmber.copy(alpha = 0.15f)
                        )
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = if (isRobloxInstalled) "● ROBLOX DETECTADO" else "○ ROBLOX NÃO ENCONTRADO",
                        color = if (isRobloxInstalled) AccentMint else AccentAmber,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoPill(label = "CHIP", value = hardware.processor.take(16))
                InfoPill(label = "RAM", value = hardware.totalRamGb)
                InfoPill(label = "SISTEMA", value = hardware.androidVersion.take(12))
            }
        }
    }
}

@Composable
private fun InfoPill(label: String, value: String) {
    Column {
        Text(text = label, color = TextMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Text(text = value, color = TextSecondary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
    }
}
