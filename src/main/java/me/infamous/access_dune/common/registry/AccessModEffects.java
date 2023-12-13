package me.infamous.access_dune.common.registry;

import me.infamous.access_dune.AccessMod;
import me.infamous.access_dune.common.effect.DuneWrathEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class AccessModEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, AccessMod.MODID);

    public static final RegistryObject<Effect> DUNE_WRATH = EFFECTS.register("dune_wrath",
            () -> new DuneWrathEffect(EffectType.NEUTRAL, 745784));

    public static void register(IEventBus modEventBus){
        EFFECTS.register(modEventBus);
    }
}
