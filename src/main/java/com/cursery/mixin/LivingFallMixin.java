package com.cursery.mixin;

import com.cursery.enchant.Enchants;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class LivingFallMixin
{
    @Shadow
    public abstract ItemStack getItemBySlot(final EquipmentSlot equipmentSlot);

    @ModifyVariable(method = "causeFallDamage", at = @At("HEAD"), index = 1, argsOnly = true)
    public float onFallDist(final float distance)
    {
        final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.steelFeet, getItemBySlot(EquipmentSlot.FEET));
        if (level > 0)
        {
            return distance + 1.7f;
        }
        return distance;
    }

    @ModifyVariable(method = "causeFallDamage", at = @At("HEAD"), index = 2, argsOnly = true)
    public float onFallMult(final float mult)
    {
        final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.steelFeet, getItemBySlot(EquipmentSlot.FEET));
        if (level > 0)
        {
            return mult * level;
        }
        return mult;
    }
}
