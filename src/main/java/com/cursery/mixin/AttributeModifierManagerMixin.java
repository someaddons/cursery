package com.cursery.mixin;

import net.minecraft.entity.ai.attributes.AttributeModifierManager;
import net.minecraft.nbt.CompoundNBT;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AttributeModifierManager.class)
public class AttributeModifierManagerMixin
{
    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundNBT;getString(Ljava/lang/String;)Ljava/lang/String;"))
    private String on(final CompoundNBT compoundNBT, final String key)
    {
        return compoundNBT.getString(key).toLowerCase();
    }
}
