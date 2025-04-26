package com.thetreesee.colemakcaps;

import dev.architectury.event.events.client.ClientTickEvent;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class ColemakCaps {
    // private static final Logger LOGGER = LoggerFactory.getLogger("ColemakCaps");
    private static boolean wasCapslockPressed = false;
    private static int holdTime = 0;
    private static final int INITIAL_DELAY = 10;
    private static final int REPEAT_RATE = 1;

    public static void init() {
        // LOGGER.info("Initializing ColemakCaps...");

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
                mc.screen.keyPressed(GLFW.GLFW_KEY_BACKSPACE, 0, 0);
                mc.screen.charTyped('\b', 0);
            }
            wasCapslockPressed = true;
            holdTime++;
        } else {
            wasCapslockPressed = false;
            holdTime = 0;
        }
    }
}