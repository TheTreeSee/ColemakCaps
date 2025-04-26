package com.thetreesee.colemakcaps.fabric;

import com.thetreesee.colemakcaps.ColemakCaps;
import net.fabricmc.api.ClientModInitializer;

public class ColemakCapsFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        System.out.println("Initializing ColemakCaps Fabric client...");
        ColemakCaps.init();
        System.out.println("ColemakCaps Fabric client initialized!");
    }
}