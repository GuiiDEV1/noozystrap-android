package com.noozystrap.mobile.data

object PresetRepository {

    val POTATO_MODE = Preset(
        id = "potato",
        title = "Modo Batata Extremo",
        subtitle = "Texturas lisas (skipmips=2), sem sombras, sem relevo. Máximo FPS em celulares fracos.",
        iconName = "potato",
        badge = "ZERO LAG",
        flags = mapOf(
            "FIntDebugTextureManagerSkipMips" to 2,
            "DFFlagTextureQualityOverrideEnabled" to true,
            "DFIntTextureQualityOverride" to 1,
            "FIntRenderShadowIntensity" to 0,
            "FIntFRMMaxGrassDistance" to 0,
            "FIntTerrainArraySliceSize" to 0,
            "FFlagDisablePostFx" to true,
            "FFlagDebugSkyGray" to true,
            "FFlagClouds" to false,
            "FFlagGlobalWindActivated" to false,
            "FFlagFastGPULightCulling3" to true,
            "FIntDebugForceMSAASamples" to 0,
            "DFIntTaskSchedulerTargetFps" to 60
        ),
        recommendedFor = "Celulares de 2GB a 4GB de RAM"
    )

    val COMPETITIVE_120 = Preset(
        id = "competitive",
        title = "Competitivo 120 FPS",
        subtitle = "Taxa de quadros liberada para telas de 90Hz/120Hz, campo de visão limpo e sem sombras.",
        iconName = "trophy",
        badge = "120 FPS",
        flags = mapOf(
            "DFIntTaskSchedulerTargetFps" to 120,
            "FFlagDebugGraphicsDisableShadows" to true,
            "FFlagDisablePostFx" to true,
            "FIntRenderShadowIntensity" to 0,
            "FFlagFastGPULightCulling3" to true,
            "FIntFRMMaxGrassDistance" to 0
        ),
        recommendedFor = "PvP (Arsenal, Blox Fruits, Rivals)"
    )

    val BATTERY_SAVER = Preset(
        id = "battery",
        title = "Bateria & Resfriamento",
        subtitle = "Otimizado para sessões longas sem esquentar a mão e sem drenar a bateria rápido.",
        iconName = "battery",
        badge = "ANTI-HEAT",
        flags = mapOf(
            "DFIntTaskSchedulerTargetFps" to 60,
            "FFlagFRMQualityLevelsOverride" to 1,
            "FIntRenderShadowIntensity" to 0,
            "FFlagDisablePostFx" to true,
            "DFFlagTextureQualityOverrideEnabled" to true,
            "DFIntTextureQualityOverride" to 2
        ),
        recommendedFor = "Farm longo (Pet Sim, Sol's RNG)"
    )

    val VULKAN_BOOSTER = Preset(
        id = "vulkan",
        title = "Vulkan Puro & Estabilidade",
        subtitle = "Força a API gráfica moderna Vulkan para frametime estável em chips Snapdragon e Dimensity.",
        iconName = "bolt",
        badge = "VULKAN",
        flags = mapOf(
            "FFlagGraphicsPreferVulkan" to true,
            "DFIntTaskSchedulerTargetFps" to 90,
            "FFlagFastGPULightCulling3" to true
        ),
        recommendedFor = "Chips Snapdragon e Dimensity"
    )

    val ALL_PRESETS = listOf(POTATO_MODE, COMPETITIVE_120, BATTERY_SAVER, VULKAN_BOOSTER)
}
