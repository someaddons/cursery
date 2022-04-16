package com.cursery.mixin;

import com.cursery.event.EventHandler;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentMenuMixin extends AbstractContainerMenu
{
    @Shadow
    @Final
    private Container enchantSlots;

    @Shadow
    @Final
    private ContainerLevelAccess access;

    protected EnchantmentMenuMixin(@Nullable final MenuType<?> menuType, final int i)
    {
        super(menuType, i);
    }

    @Inject(method = "slotsChanged", at = @At("HEAD"))
    public void onEnchant(final Container container, final CallbackInfo ci)
    {
        if (container == enchantSlots)
        {
            ItemStack itemStack = container.getItem(0);
            if (!itemStack.isEmpty() && itemStack.isEnchantable())
            {
                EventHandler.onSuccessEnchant(itemStack, access);
            }
        }
    }
}
