package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import com.cursery.enchant.PlayerVisualHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractRepairContainer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractRepairContainer.class)
public class AbstractRepairContainerMixin
{
    @Shadow
    @Final
    public IInventory inputSlots;
    AbstractRepairContainer self = (AbstractRepairContainer) (Object) this;

    @Inject(method = "quickMoveStack", at = @At(value = "INVOKE", target = "net/minecraft/inventory/container/Slot.hasItem ()Z", shift = At.Shift.AFTER)
      , allow = 1)
    public void test(final PlayerEntity player, final int slotNum, final CallbackInfoReturnable<ItemStack> cir)
    {
        if (player != null && !player.level.isClientSide() && slotNum == 2)
        {
            ItemStack result = self.slots.get(slotNum).getItem();
            ItemStack input = self.inputSlots.getItem(0);

            if (CurseEnchantmentHelper.delayItem == result.getItem())
            {
                if (CurseEnchantmentHelper.checkForRandomCurse(result,
                  EnchantmentHelper.getEnchantments(input),
                  EnchantmentHelper.getEnchantments(result)))
                {
                    player.containerMenu.broadcastChanges();
                    ((ServerPlayerEntity) player).refreshContainer(player.containerMenu);
                    PlayerVisualHelper.randomNotificationOnCurseApply((ServerPlayerEntity) player, result);
                }
            }
        }
    }
}
