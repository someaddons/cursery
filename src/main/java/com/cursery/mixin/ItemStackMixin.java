package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import com.cursery.event.ClientEventHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

@Mixin(ItemStack.class)
public class ItemStackMixin
{
    final ItemStack self = (ItemStack) (Object) this;

    private Map<Enchantment, Integer> previous;

    @Inject(method = "enchant", at = @At("HEAD"))
    private void beforeEnchant(final Enchantment enchantment, final int level, final CallbackInfo ci)
    {
        previous = EnchantmentHelper.getEnchantments(self);
    }

    @Inject(method = "enchant", at = @At("RETURN"))
    private void afterEnchant(final Enchantment enchantment, final int level, final CallbackInfo ci)
    {
        if (level <= 0 || enchantment.isCurse() || enchantment.isTreasureOnly())
        {
            return;
        }

        if (Thread.currentThread().getName().contains("Render"))
        {
            return;
        }

        CurseEnchantmentHelper.checkForRandomCurse(self, previous, EnchantmentHelper.getEnchantments(self));
    }

    @Inject(method = "getTooltipLines", at = @At("RETURN"))
    public void addToolTip(final Player player, final TooltipFlag tooltipFlag, final CallbackInfoReturnable<List<Component>> cir)
    {
        ClientEventHandler.on(self, cir.getReturnValue());
    }
}
