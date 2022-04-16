package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import com.cursery.enchant.PlayerVisualHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemCombinerMenu.class)
public class AbstractRepairContainerMixin
{
    @Shadow
    @Final
    public Container inputSlots;
    ItemCombinerMenu self = (ItemCombinerMenu) (Object) this;

    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/Slot;hasItem()Z", shift = At.Shift.AFTER), allow = 1)
    public void test(final Player player, final int slotNum, final CallbackInfoReturnable<ItemStack> cir)
    {
        if (player != null && !player.level.isClientSide() && slotNum == 2)
        {
            ItemStack result = self.slots.get(slotNum).getItem();
            ItemStack input = inputSlots.getItem(0);

            if (CurseEnchantmentHelper.delayItem == result.getItem())
            {
                if (CurseEnchantmentHelper.checkForRandomCurse(result,
                  EnchantmentHelper.getEnchantments(input),
                  EnchantmentHelper.getEnchantments(result)))
                {
                    player.containerMenu.broadcastChanges();
                    //((ServerPlayer) player).refreshContainer(player.containerMenu);
                    PlayerVisualHelper.randomNotificationOnCurseApply((ServerPlayer) player, result);
                }
                else
                {
                    PlayerVisualHelper.enchantSuccess((ServerPlayer) player, result);
                }
            }
        }
    }
}
