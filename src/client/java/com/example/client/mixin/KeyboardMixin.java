package com.example.client.mixin;

import com.example.ExampleModClient;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (ExampleModClient.isRecording) {
            if (action == GLFW.GLFW_PRESS) {
                ExampleModClient.LOGGER.info("Нажата клавиша с кодом: " + key);
            }
        }
    }
}
