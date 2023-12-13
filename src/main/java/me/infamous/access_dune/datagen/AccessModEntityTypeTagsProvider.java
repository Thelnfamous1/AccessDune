package me.infamous.access_dune.datagen;

import me.infamous.access_dune.AccessMod;
import me.infamous.access_dune.common.registry.AccessModEntityTypes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class AccessModEntityTypeTagsProvider extends EntityTypeTagsProvider {
    public AccessModEntityTypeTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, AccessMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(EntityTypeTags.IMPACT_PROJECTILES).add(AccessModEntityTypes.WRATHFUL_DUST.get());
    }
}
