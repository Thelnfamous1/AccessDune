package me.infamous.access_dune.common.registry;

import me.infamous.access_dune.AccessMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ovh.corail.flyingthings.item.ItemMagicCarpet;

public class AccessModItems {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AccessMod.MODID);

    public static final RegistryObject<Item> DUNE_SPAWN_EGG = ITEMS.register("dune_spawn_egg",
            () -> new ForgeSpawnEggItem(AccessModEntityTypes.DUNE, 7958625, 15125652,
                    (new Item.Properties()).tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> MAGIC_CARPET = ITEMS.register("magic_carpet", ItemMagicCarpet::new);

    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
