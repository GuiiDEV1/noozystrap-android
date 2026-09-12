package com.noozystrap.mobile.data

enum class FlagType {
    BOOLEAN, INTEGER, STRING
}

enum class FlagCategory {
    GRAPHICS, TEXTURES, PERFORMANCE, ENGINE
}

data class FastFlag(
    val name: String,
    val value: Any,
    val type: FlagType,
    val description: String = "",
    val category: FlagCategory = FlagCategory.PERFORMANCE
)

data class Preset(
    val id: String,
    val title: String,
    val subtitle: String,
    val iconName: String,
    val badge: String,
    val flags: Map<String, Any>,
    val recommendedFor: String
)
