package com.noozystrap.mobile.service

import android.content.Context
import android.os.Environment
import com.google.gson.GsonBuilder
import java.io.File

data class ExportResult(
    val success: Boolean,
    val message: String,
    val targetPath: String = "",
    val flagCount: Int = 0
)

object FastFlagExporter {

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun exportFlags(
        context: Context,
        flags: Map<String, Any>,
        customPath: String? = null
    ): ExportResult {
        if (flags.isEmpty()) {
            return ExportResult(false, "Nenhuma FastFlag selecionada.")
        }

        val jsonString = gson.toJson(flags)

        // Lista de locais de destino em ordem de prioridade
        val candidateDirs = mutableListOf<File>()

        if (!customPath.isNullOrBlank()) {
            candidateDirs.add(File(customPath))
        }

        // 1. Caminho padrão do Roblox com suporte a FastFlag (Armazenamento externo)
        val extStorage = Environment.getExternalStorageDirectory()
        candidateDirs.add(
            File(extStorage, "Android/data/com.roblox.client/files/ClientSettings")
        )

        // 2. Pasta pública NoozyStrap no armazenamento
        candidateDirs.add(
            File(extStorage, "NoozyStrap/ClientSettings")
        )

        // 3. Pasta privada do app com permissão compartilhada
        context.getExternalFilesDir(null)?.let {
            candidateDirs.add(File(it, "ClientSettings"))
        }

        var writtenPath: String? = null
        var lastError: String? = null

        for (dir in candidateDirs) {
            try {
                if (!dir.exists()) {
                    dir.mkdirs()
                }

                val jsonFile = File(dir, "ClientAppSettings.json")
                jsonFile.writeText(jsonString, Charsets.UTF_8)

                if (jsonFile.exists() && jsonFile.length() > 0) {
                    writtenPath = jsonFile.absolutePath
                    break
                }
            } catch (e: Exception) {
                lastError = e.message
            }
        }

        return if (writtenPath != null) {
            ExportResult(
                success = true,
                message = "Configurações aplicadas com sucesso (${flags.size} flags).",
                targetPath = writtenPath,
                flagCount = flags.size
            )
        } else {
            ExportResult(
                success = false,
                message = "Erro ao gravar arquivo de configuração: ${lastError ?: "permissão negada"}"
            )
        }
    }
}
