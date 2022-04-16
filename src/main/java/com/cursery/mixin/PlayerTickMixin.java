package com.cursery.mixin;

import com.cursery.event.EventHandler;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerTickMixin
{
    @Inject(method = "tick", at = @At("HEAD"))
    public void onTick(final CallbackInfo ci)
    {
        EventHandler.onPlayerTick((Player) (Object) this);
    }

    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    public void onGetDigSpeed(final BlockState blockState, final CallbackInfoReturnable<Float> cir)
    {
        EventHandler.onDestroySpeed((Player) (Object) this, cir);
    }
}
