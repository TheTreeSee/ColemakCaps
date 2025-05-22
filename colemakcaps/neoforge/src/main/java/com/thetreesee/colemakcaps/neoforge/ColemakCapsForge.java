package com.thetreesee.colemakcaps.neoforge;

import com.thetreesee.colemakcaps.ColemakCaps;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

@Mod("colemakcaps")
public class ColemakCapsForge {
    private static KeyMapping capslockKeyBinding;

    public ColemakCapsForge(IEventBus modEventBus) {
        System.out.println("ColemakCapsForge constructor called!");

        // Create the key binding
        capslockKeyBinding = new KeyMapping(
                "key.colemakcaps.capslock",
                GLFW.GLFW_KEY_BACKSPACE, // Default to backspace key
                "key.categories.colemakcaps");

        // Set the key binding in ColemakCaps
        ColemakCaps.setCapslockKeyBinding(capslockKeyBinding);

        // Register key bindings
        modEventBus.addListener(this::registerKeyBindings);

        // Initialize ColemakCaps
        ColemakCaps.init();

        System.out.println("ColemakCaps initialization completed!");
    }

    private void registerKeyBindings(final RegisterKeyMappingsEvent event) {
        System.out.println("Registering key bindings in RegisterKeyMappingsEvent...");
        event.register(capslockKeyBinding);
    }
}