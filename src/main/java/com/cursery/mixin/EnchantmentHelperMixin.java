package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin
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
        if ((Thread.currentThread().getName().contains("Render")))
        {
            return;
        }

        CurseEnchantmentHelper.checkForRandomCurse(stack, previous, EnchantmentHelper.getEnchantments(stack));
    }

    @Redirect(method = "selectEnchantment", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getAvailableEnchantmentResults(ILnet/minecraft/world/item/ItemStack;Z)Ljava/util/List;"))
    private static List onGetTableEnchants(final int i, final ItemStack enchantment, final boolean treasures)
    {
        final List<EnchantmentInstance> enchants = EnchantmentHelper.getAvailableEnchantmentResults(i, enchantment, treasures);
        if (!treasures)
        {
            enchants.removeIf(e -> e.enchantment.isTreasureOnly() || e.enchantment.isCurse());
        }

        return enchants;
    }
}
