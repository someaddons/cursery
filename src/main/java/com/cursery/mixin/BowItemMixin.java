package com.cursery.mixin;

import com.cursery.Cursery;
import com.cursery.enchant.Enchants;
import com.cursery.enchant.curses.StubbyCurse;
import com.cursery.event.EventHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class BowItemMixin
{
    @Shadow
    public abstract int getUseDuration(final ItemStack itemStack);

    @Redirect(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/BowItem;getUseDuration(Lnet/minecraft/world/item/ItemStack;)I"))
    public int onReleaseUseDuration(final BowItem instance, final ItemStack itemStack)
    {
        if (Cursery.rand.nextInt(StubbyCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.stubbyCurse, itemStack) > 0)
        {
            return getUseDuration(itemStack) * 2;
        }

        return getUseDuration(itemStack);
    }

    @Inject(method = "releaseUsing", at = @At("RETURN"))
    public void onFireArrow(final ItemStack itemStack, final Level level, final LivingEntity livingEntity, final int i, final CallbackInfo ci)
    {
        if (livingEntity instanceof ServerPlayer)
        {
            EventHandler.onArrowFire((ServerPlayer) livingEntity);
        }
    }
}
