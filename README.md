# 📱 NoozyStrap Mobile (Android)

> **Suíte de Otimização e FastFlags para Roblox Mobile no Android.**  
> Inspirado na arquitetura Masterstrap (file-based inject via ClientAppSettings.json), totalmente compatível com **Android 11 a 15 (Xiaomi HyperOS, OneUI, OxygenOS, Stock Android)** sem necessidade de Root!

---

## ⚡ Recursos Principais

- 🥔 **Modo Batata Extremo (skipmips = 2)**:
  - Texturas ultraleves, desativa terreno procedural, grama densa, sombras dinâmicas e pós-processamento pesado.
  - Consumo de RAM reduzido para menos de 450 MB (ideal para celulares de entrada ou longas sessões de grind).
- 🎯 **Competitivo 120 FPS**:
  - Desbloqueio de alta taxa de atualização (DFIntTaskSchedulerTargetFps = 120) perfeito para telas AMOLED de 120Hz/144Hz como no **Poco F7**, Xiaomi, Galaxy e ROG Phone.
  - FOV otimizado e latência de toque reduzida.
- 🔋 **Bateria & Anti-Esquentar**:
  - Trava em 60 FPS com Render Level 1 reduzindo drasticamente o aquecimento e o throttling térmico da bateria.
- 🌋 **Vulkan Booster**:
  - Força a API gráfica moderna Vulkan (FFlagGraphicsPreferVulkan = true) para tirar proveito total dos processadores Qualcomm Snapdragon e MediaTek Dimensity.
- 🚀 **1-Tap Launcher**:
  - Aplica as configurações instantaneamente em /sdcard/Android/data/com.roblox.client/files/ClientSettings/ClientAppSettings.json e abre o Roblox automaticamente com feedback tátil.

---

## 📲 Como Instalar no Celular (Ex: Poco F7 / Xiaomi HyperOS)

1. Baixe o arquivo .apk na aba **Releases** deste repositório privado.
2. Abra o arquivo no Gerenciador de Arquivos do celular e clique em **Instalar**.
   *(Se o Android/HyperOS perguntar sobre fontes desconhecidas, autorize a instalação)*.
3. Ao abrir o NoozyStrap Mobile pela primeira vez:
   - Toque em **Permitir Acesso** para autorizar permissão de armazenamento (Todos os Arquivos ou SAF) para gravar o ClientAppSettings.json.
4. Escolha seu Preset favorito (Ex: **Competitivo 120 FPS** ou **Modo Batata**).
5. Toque no botão verde brilhante **APLICAR & JOGAR ROBLOX**.
6. Pronto! O Roblox iniciará automaticamente com as otimizações ativas.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin** + **Jetpack Compose** (Material 3 Dark OLED Design)
- **FastFlag Exporter Engine** (Validação e serialização de JSON nativo)
- **Android SAF & Intent Resolver**

---

*Desenvolvido com carinho por @GuiiDEV1 & Comunidade NoozyStrap.*
