package me.infamous.access_dune.common.events;

import me.infamous.access_dune.AccessMod;
import me.infamous.access_dune.common.entity.dune.Dune;
import me.infamous.access_dune.common.network.AccessModNetwork;
import me.infamous.access_dune.common.registry.AccessModEntityTypes;
import me.infamous.access_dune.common.registry.AccessModLootFunctions;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = AccessMod.MODID)
public class ModEventHandler {

    @SubscribeEvent
    public static void onAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(AccessModEntityTypes.DUNE.get(), Dune.createAttributes().build());
    }

    @SubscribeEvent
    static void onCommonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> {
            AccessModNetwork.init();
            EntitySpawnPlacementRegistry.register(
                    AccessModEntityTypes.DUNE.get(),
                    EntitySpawnPlacementRegistry.PlacementType.ON_GROUND,
                    Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                    Dune::checkDuneSpawnRules);
            AccessModLootFunctions.register();
        });
    }
}
