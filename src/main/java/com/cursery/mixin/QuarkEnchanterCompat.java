package com.cursery.mixin;

import com.cursery.enchant.CurseEnchantmentHelper;
import com.cursery.enchant.PlayerVisualHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.quark.addons.oddities.inventory.MatrixEnchantingMenu;

@Mixin(MatrixEnchantingMenu.class)
public class QuarkEnchanterCompat
{
    @Inject(method = "finish", at = @At("HEAD"), remap = false, require = 0)
    private void onFinish(final Player player, final ItemStack stack, final CallbackInfo ci)
    {
        // Apply curse chance
        if (CurseEnchantmentHelper.delayItem == stack.getItem())
        {
            if (CurseEnchantmentHelper.checkForRandomCurse(stack,
              CurseEnchantmentHelper.prevEnchants,
              EnchantmentHelper.getEnchantments(stack)))
            {
                if (player != null && !player.level.isClientSide())
                {
                    player.containerMenu.broadcastChanges();
                    //((ServerPlayer) event.getPlayer()).refreshContainer(event.getPlayer().containerMenu);
                    PlayerVisualHelper.randomNotificationOnCurseApply((ServerPlayer) player, stack);
                }
            }
            else
            {
                if (player != null && !player.level.isClientSide())
                {
                    PlayerVisualHelper.enchantSuccess((ServerPlayer) player, stack);
                }
            }
        }
    }
}
