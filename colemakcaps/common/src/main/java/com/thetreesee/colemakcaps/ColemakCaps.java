package com.thetreesee.colemakcaps;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class ColemakCaps {
    // private static final Logger LOGGER = LoggerFactory.getLogger("ColemakCaps");
    private static boolean wasCapslockPressed = false;
    private static int holdTime = 0;
    private static final int INITIAL_DELAY = 10;
    private static final int REPEAT_RATE = 1;
    private static KeyMapping capslockKeyBinding;

    public static void init() {
        // LOGGER.info("Initializing ColemakCaps...");

        // Only create the key binding if it hasn't been created yet
        if (capslockKeyBinding == null) {
            capslockKeyBinding = new KeyMapping(
                    "key.colemakcaps.capslock",
                    GLFW.GLFW_KEY_BACKSPACE, // Default to backspace key
                    "key.categories.colemakcaps");
        }

        ClientTickEvent.CLIENT_PRE.register(client -> {
            Minecraft mc = Minecraft.getInstance();
            if (mc.screen != null) { // Only process when in a GUI
                long window = mc.getWindow().getWindow();
                processCapslockInput(mc, window);
            }
        });
    }

    private static void processCapslockInput(Minecraft mc, long window) {
        if (GLFW.glfwGetKey(window, GLFW.GLFW_KEY_CAPS_LOCK) == GLFW.GLFW_PRESS) {
            if (!wasCapslockPressed || (holdTime >= INITIAL_DELAY && holdTime % REPEAT_RATE == 0)) {
                // Check if the key binding is bound to a key
                if (capslockKeyBinding.isUnbound()) {
                    // If unbound, use ESC
                    mc.screen.keyPressed(GLFW.GLFW_KEY_ESCAPE, 0, 0);
                } else {
                    // If bound, simulate the bound key press
                    int keyCode = capslockKeyBinding.getDefaultKey().getValue();
                    mc.screen.keyPressed(keyCode, 0, 0);
                    if (keyCode == GLFW.GLFW_KEY_BACKSPACE) {
                        mc.screen.charTyped('\b', 0);
                    }
                }
            }
            wasCapslockPressed = true;
            holdTime++;
        } else {
            wasCapslockPressed = false;
            holdTime = 0;
        }
    }

    public static KeyMapping getCapslockKeyBinding() {
        return capslockKeyBinding;
    }

    public static void setCapslockKeyBinding(KeyMapping binding) {
        capslockKeyBinding = binding;
    }
}