package com.cursery.enchant;

import com.cursery.Cursery;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;

/**
 * Helps visualizing curse application to the player
 */
public class PlayerVisualHelper
{
    public static void randomNotificationOnCurseApply(final ServerPlayer playerEntity, final ItemStack cursed)
    {
        playerEntity.sendMessage(new TranslatableComponent(
          "enchant_curse_applied_" + (Cursery.rand.nextInt(4) + 1) + ".desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)), playerEntity.getUUID());
        playerEntity.playNotifySound(SoundEvents.ENDER_DRAGON_GROWL, SoundSource.MASTER, 0.2f, 1.0f);
    }
}
