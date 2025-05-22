package com.thetreesee.colemakcaps;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import java.util.List;
import java.util.ArrayList;

public class KeyBindings {
    private static final List<KeyMapping> KEY_BINDINGS = new ArrayList<>();

    public static void register() {
        KEY_BINDINGS.add(ColemakCaps.getCapslockKeyBinding());
    }

    public static List<KeyMapping> getKeyBindings() {
        return KEY_BINDINGS;
    }
}