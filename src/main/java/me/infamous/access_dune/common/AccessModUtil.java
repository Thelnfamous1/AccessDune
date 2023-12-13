package me.infamous.access_dune.common;

import me.infamous.access_dune.common.entity.ai.digger.Digger;
import me.infamous.access_dune.common.entity.dune.Dune;
import me.infamous.access_dune.common.registry.AccessModEntityTypes;
import me.infamous.access_dune.common.registry.AccessModPOITypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class AccessModUtil {
    public static final String FROM_DESERT_WELL_TAG = "FromDesertWell";

    public static final float TO_RADIANS = ((float) Math.PI / 180F);

    public static void handleDesertWellFillBottle(ItemStack stack, World world, PlayerEntity player) {
        if(world instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld) world;
            BlockRayTraceResult playerPOVHitResult = AccessModUtil.getPlayerPOVHitResult(serverWorld, player, RayTraceContext.FluidMode.SOURCE_ONLY);
            if(playerPOVHitResult.getType() == RayTraceResult.Type.BLOCK){
                BlockPos blockPos = playerPOVHitResult.getBlockPos();
                if(serverWorld.getPoiManager().existsAtPosition(AccessModPOITypes.DESERT_WELL.get(), blockPos)){
                    stack.getOrCreateTag().putBoolean(FROM_DESERT_WELL_TAG, true);
                }
            }
        }
    }

    public static boolean isFromDesertWell(ItemStack bottleStack){
        return bottleStack.hasTag() && bottleStack.getTag().getBoolean(FROM_DESERT_WELL_TAG);
    }

    public static int secondsToTicks(double seconds) {
        return (int) Math.ceil(seconds * 20);
    }

    public static BlockRayTraceResult getPlayerPOVHitResult(World pLevel, PlayerEntity pPlayer, RayTraceContext.FluidMode pFluidMode) {
        float f = pPlayer.xRot;
        float f1 = pPlayer.yRot;
        Vector3d vector3d = pPlayer.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * TO_RADIANS - (float)Math.PI);
        float f3 = MathHelper.sin(-f1 * TO_RADIANS - (float)Math.PI);
        float f4 = -MathHelper.cos(-f * TO_RADIANS);
        float f5 = MathHelper.sin(-f * TO_RADIANS);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = pPlayer.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue();
        Vector3d vector3d1 = vector3d.add((double)f6 * d0, (double)f5 * d0, (double)f7 * d0);
        return pLevel.clip(new RayTraceContext(vector3d, vector3d1, RayTraceContext.BlockMode.OUTLINE, pFluidMode, pPlayer));
    }

    public static void summonDune(LivingEntity summoner, ServerWorld serverWorld){
        Dune dune = AccessModEntityTypes.DUNE.get().create(serverWorld);
        BlockPos spawnPos = summoner.blockPosition();
        dune.moveTo(spawnPos, 0.0F, 0.0F);
        for(int i = 0; i < 16; ++i) {
            double xRandom = summoner.getX() + (summoner.getRandom().nextDouble() - 0.5D) * 16.0D;
            double yRandom = MathHelper.clamp(summoner.getY() + (double)(summoner.getRandom().nextInt(16) - 8), 0.0D, serverWorld.getHeight() - 1);
            double zRandom = summoner.getZ() + (summoner.getRandom().nextDouble() - 0.5D) * 16.0D;
            if(dune.randomTeleport(xRandom, yRandom, zRandom, false)){
                spawnPos = new BlockPos(xRandom, yRandom, zRandom);
                break;
            }
        }
        dune.setDigState(Digger.DigState.BURIED);
        dune.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(spawnPos), SpawnReason.MOB_SUMMONED, null, null);
        serverWorld.addFreshEntityWithPassengers(dune);
    }

}
