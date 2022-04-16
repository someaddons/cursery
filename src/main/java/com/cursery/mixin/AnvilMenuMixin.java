package com.cursery.mixin;

import com.cursery.event.EventHandler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilMenu.class)
public abstract class AnvilMenuMixin extends ItemCombinerMenu
{
    public AnvilMenuMixin(
      @Nullable final MenuType<?> menuType,
      final int i,
      final Inventory inventory,
      final ContainerLevelAccess containerLevelAccess)
    {
        super(menuType, i, inventory, containerLevelAccess);
    }

    @Inject(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/EnchantedBookItem;getEnchantments(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/nbt/ListTag;", shift = At.Shift.BEFORE))
    public void onCreateResult(final CallbackInfo ci)
    {
        EventHandler.onAnvilInput(inputSlots.getItem(0), inputSlots.getItem(1), player);
    }

    @Inject(method = "onTake", at = @At(value = "HEAD"))
    public void onTake(final Player player, final ItemStack itemStack, final CallbackInfo ci)
    {
        EventHandler.onAnvilOutput(player, itemStack, inputSlots.getItem(0));
    }
}
