package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleModClient implements ClientModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("macro-mod");
    public static boolean isRecording = false;
    private static KeyBinding recordKey;

    @Override
    public void onInitializeClient() {
        recordKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.macro-mod.record",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "category.macro-mod"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (recordKey.wasPressed()) {
                isRecording = !isRecording;
                LOGGER.info("Запись макроса: " + (isRecording ? "ВКЛ" : "ВЫКЛ"));
            }
        });
    }
}
