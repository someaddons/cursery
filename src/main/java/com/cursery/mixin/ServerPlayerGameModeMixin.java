package com.cursery.mixin;

import com.cursery.event.EventHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixin
{
    @Shadow
    @Final
    protected ServerPlayer player;

    @Inject(method = "destroyBlock", at = @At("HEAD"))
    public void onBlockBreak(final BlockPos blockPos, final CallbackInfoReturnable<Boolean> cir)
    {
        EventHandler.onBlockBreak(player, blockPos);
    }
}
