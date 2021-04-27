package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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

        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        CurseEnchantmentHelper.checkForRandomCurse(self, previous, EnchantmentHelper.getEnchantments(self));
    }
}
