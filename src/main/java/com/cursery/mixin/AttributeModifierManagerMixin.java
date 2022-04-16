package com.cursery.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AttributeMap.class)
public class AttributeModifierManagerMixin
{
    @Redirect(method = "load", at = @At(value = "INVOKE", target = "Lnet/minecraft/nbt/CompoundTag;getString(Ljava/lang/String;)Ljava/lang/String;"))
    private String on(final CompoundTag compoundNBT, final String key)
    {
        return compoundNBT.getString(key).toLowerCase();
    }
}
