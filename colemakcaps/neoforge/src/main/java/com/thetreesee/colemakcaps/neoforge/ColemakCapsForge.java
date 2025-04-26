package com.thetreesee.colemakcaps.neoforge;

import com.thetreesee.colemakcaps.ColemakCaps;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;

@Mod("colemakcaps")
public class ColemakCapsForge {
    public ColemakCapsForge(IEventBus modEventBus) {
        System.out.println("ColemakCapsForge constructor called!");
        ColemakCaps.init();
        System.out.println("ColemakCaps initialization completed!");
    }
}