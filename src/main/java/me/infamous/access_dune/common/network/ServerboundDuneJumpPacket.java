package me.infamous.access_dune.common.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ServerboundDuneJumpPacket {

    private final boolean jumping;

    public ServerboundDuneJumpPacket(boolean jumping){
        this.jumping = jumping;
    }

    public ServerboundDuneJumpPacket(PacketBuffer buffer){
        this.jumping = buffer.readBoolean();
    }

    public static void encode(ServerboundDuneJumpPacket packet, PacketBuffer buffer){
        buffer.writeBoolean(packet.jumping);
    }

    public static void handle(ServerboundDuneJumpPacket packet, Supplier<NetworkEvent.Context> context){
        context.get().enqueueWork(() -> {
            ServerPlayerEntity player = context.get().getSender();
            if(player != null){
                player.setJumping(packet.jumping);
            }
        });
        context.get().setPacketHandled(true);
    }
}
