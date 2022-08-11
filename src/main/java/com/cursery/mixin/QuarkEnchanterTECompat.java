package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.quark.addons.oddities.block.be.MatrixEnchantingTableBlockEntity;

@Mixin(MatrixEnchantingTableBlockEntity.class)
public class QuarkEnchanterTECompat
{
    @Redirect(method = "makeOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;copy()Lnet/minecraft/world/item/ItemStack;", remap = true), remap = false, require = 0)
    private ItemStack onOutput(final ItemStack instance)
    {
        final ItemStack copy = instance.copy();
        CurseEnchantmentHelper.delayNext = true;
        CurseEnchantmentHelper.delayItem = copy.getItem();
        CurseEnchantmentHelper.prevEnchants = EnchantmentHelper.getEnchantments(copy);
        return copy;
    }
}
