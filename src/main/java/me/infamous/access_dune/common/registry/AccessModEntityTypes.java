package me.infamous.access_dune.common.registry;

import me.infamous.access_dune.AccessMod;
import me.infamous.access_dune.common.entity.dune.Dune;
import me.infamous.access_dune.common.entity.dune.WrathfulDust;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ovh.corail.flyingthings.entity.EntityMagicCarpet;

import java.util.stream.Collectors;

public class AccessModEntityTypes {

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, AccessMod.MODID);

    public static final RegistryObject<EntityType<Dune>> DUNE = register("dune",
            EntityType.Builder.of(Dune::new, EntityClassification.MONSTER)
                    .sized(0.75F, 2.25F)
                    .clientTrackingRange(8));

    public static final RegistryObject<EntityType<WrathfulDust>> WRATHFUL_DUST = register("wrathful_dust",
            EntityType.Builder.<WrathfulDust>of(WrathfulDust::new, EntityClassification.MISC)
                    .sized(1.0F, 1.0F)
                    .clientTrackingRange(4)
                    .updateInterval(10));

    public static final RegistryObject<EntityType<EntityMagicCarpet>> MAGIC_CARPET = register("magic_carpet",
            EntityType.Builder.<EntityMagicCarpet>of(EntityMagicCarpet::new, EntityClassification.MISC)
                    .setTrackingRange(80)
                    .setUpdateInterval(1)
                    .setShouldReceiveVelocityUpdates(true)
                    .sized(1.5f, 0.3f)
                    .setCustomClientFactory(EntityMagicCarpet::new)
                    .noSummon());


    private static <T extends Entity> RegistryObject<EntityType<T>> register(String pKey, EntityType.Builder<T> pBuilder) {
        return ENTITY_TYPES.register(pKey, () -> pBuilder.build(AccessMod.MODID + ":" + pKey));
    }

    public static void register(IEventBus modEventBus){
        ENTITY_TYPES.register(modEventBus);
    }

    public static Iterable<EntityType<?>> getKnownEntities() {
        return ENTITY_TYPES.getEntries().stream().map(RegistryObject::get).collect(Collectors.toSet());
    }
}
