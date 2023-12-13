package me.infamous.access_dune.common.events;

import me.infamous.access_dune.AccessMod;
import me.infamous.access_dune.common.AccessModUtil;
import me.infamous.access_dune.common.network.AccessModNetwork;
import me.infamous.access_dune.common.network.ServerboundDuneJumpPacket;
import me.infamous.access_dune.duck.DuneSinker;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = AccessMod.MODID)
public class ForgeEventHandler {

    @SubscribeEvent
    static void onClientPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.phase == TickEvent.Phase.END && event.side == LogicalSide.CLIENT && DuneSinker.canSink(event.player)){
            if(event.player.isLocalPlayer()){
                ClientPlayerEntity player = (ClientPlayerEntity) event.player;
                AccessModNetwork.SYNC_CHANNEL.sendToServer(new ServerboundDuneJumpPacket(player.input.jumping));
            }
        }
    }

    @SubscribeEvent
    static void onItemUseFinish(LivingEntityUseItemEvent.Finish event){
        if(AccessModUtil.isFromDesertWell(event.getItem())){
            if(!event.getEntityLiving().level.isClientSide){
                AccessModUtil.summonDune(event.getEntityLiving(), (ServerWorld) event.getEntityLiving().level);
            }
        }
    }

}
