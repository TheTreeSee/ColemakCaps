package com.thetreesee.colemakcaps.fabric;

import com.thetreesee.colemakcaps.ColemakCaps;
import com.thetreesee.colemakcaps.KeyBindings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class ColemakCapsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("ColemakCapsFabric initialization started!");

        // Initialize ColemakCaps first to create the key binding
        ColemakCaps.init();

        // Then register the key binding
        KeyBindings.register();
        for (var keyBinding : KeyBindings.getKeyBindings()) {
            KeyBindingHelper.registerKeyBinding(keyBinding);
        }

        System.out.println("ColemakCapsFabric initialization completed!");
    }
}