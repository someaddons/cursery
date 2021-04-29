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

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin
{
    private static Map<Enchantment, Integer> previous;

    @Inject(method = "setEnchantments", at = @At("HEAD"))
    private static void beforeEnchants(final Map<Enchantment, Integer> enchants, final ItemStack stack, final CallbackInfo ci)
    {
        previous = EnchantmentHelper.getEnchantments(stack);
    }

    @Inject(method = "setEnchantments", at = @At("RETURN"))
    private static void afterEnchanting(final Map<Enchantment, Integer> enchants, final ItemStack stack, final CallbackInfo ci)
    {
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        CurseEnchantmentHelper.checkForRandomCurse(stack, previous, EnchantmentHelper.getEnchantments(stack));
    }
}
