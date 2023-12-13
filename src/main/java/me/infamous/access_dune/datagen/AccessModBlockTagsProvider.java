package me.infamous.access_dune.datagen;

import me.infamous.access_dune.AccessMod;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

public class AccessModBlockTagsProvider extends BlockTagsProvider {
    public AccessModBlockTagsProvider(DataGenerator pGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, AccessMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(AccessModTags.DUNES_SPAWN_ON).addTag(BlockTags.SAND);
        this.tag(AccessModTags.DUNE_WRATH_SINK_IN).addTag(BlockTags.SAND);
    }
}
