# NoozyStrap Mobile (Android)

> Suite de Otimizacao e FastFlags para Roblox Mobile no Android.
> Suporte para Android 11 a 15 (Xiaomi HyperOS, OneUI, OxygenOS, Stock Android).

---

## Importante: Versao Full Alpha 0.1.0

Aviso obrigatorio antes de instalar:
- E necessario **desinstalar o Roblox oficial da Play Store antes** de instalar o NoozyStrap Mobile APK.
- Motivo: Conflito de assinatura de pacote (INSTALL_FAILED_UPDATE_INCOMPATIBLE). Como o NoozyStrap Mobile utiliza assinatura propria para permitir modificacao e conexao direta, o Android impede a sobreposicao direta em cima do app oficial da Play Store.

---

## Recursos Principais

- Modo Batata Extremo (skipmips = 2):
  - Texturas ultraleves, desativa terreno procedural, grama densa, sombras dinamicas e pos-processamento pesado.
  - Consumo de RAM reduzido para menos de 450 MB (ideal para aparelhos de entrada ou longas sessoes de jogo).
- Competitivo 120 FPS:
  - Desbloqueio de alta taxa de atualizacao (DFIntTaskSchedulerTargetFps = 120) para telas de 90Hz, 120Hz e 144Hz.
  - FOV otimizado e latencia de toque reduzida.
- Economia de Bateria:
  - Trava em 60 FPS com Render Level 1 reduzindo aquecimento e throttling termico.
- Vulkan Booster:
  - Forca API grafica Vulkan (FFlagGraphicsPreferVulkan = true) para ganho de estabilidade em chips Qualcomm Snapdragon e MediaTek Dimensity.
- Integracao Direta de FastFlags:
  - Aplicacao direta de ClientAppSettings.json sem necessidade de root.

---

## Como Instalar

1. Desinstale o Roblox oficial do aparelho.
2. Baixe o APK na aba Releases deste repositorio.
3. Abra o arquivo no Gerenciador de Arquivos do celular e confirme a instalacao (permita fontes desconhecidas se solicitado).
4. Abra o NoozyStrap Mobile e conceda as permissoes necessarias para gravacao de dados.
5. Selecione suas configuracoes e inicie o jogo.

---

## Tecnologias

- Kotlin + Jetpack Compose
- FastFlag Exporter Engine
- Android Storage Access Framework (SAF)

---

Desenvolvido por @GuiiDEV1 e Comunidade NoozyStrap.
