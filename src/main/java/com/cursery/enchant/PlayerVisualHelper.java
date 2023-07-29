package com.cursery.enchant;

import com.cursery.Cursery;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
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
        playerEntity.sendSystemMessage(Component.translatable(
          "enchant_curse_applied_" + (Cursery.rand.nextInt(4) + 1) + ".desc").setStyle(Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE)));
        playerEntity.playNotifySound(SoundEvents.ENDER_DRAGON_GROWL, SoundSource.MASTER, 0.2f, 1.0f);
    }

    public static void enchantSuccess(final ServerPlayer playerEntity, final ItemStack stack)
    {
        if (Cursery.config.getCommonConfig().visualSuccess)
        {
            playerEntity.playNotifySound(SoundEvents.PLAYER_LEVELUP, SoundSource.MASTER, 0.2f, 1.0f);
            addParticlesAround(ParticleTypes.HAPPY_VILLAGER, playerEntity);
        }
    }

    public static void addParticlesAround(ParticleOptions particleData, final ServerPlayer playerEntity)
    {
        for (int i = 0; i < 50; ++i)
        {
            double d0 = playerEntity.getRandom().nextGaussian() * 0.02D;
            double d1 = playerEntity.getRandom().nextGaussian() * 0.02D;
            double d2 = playerEntity.getRandom().nextGaussian() * 0.02D;
            ((ServerLevel) playerEntity.level()).sendParticles(particleData,
              playerEntity.getRandomX(1.0D),
              playerEntity.getRandomY() + 1.0D,
              playerEntity.getRandomZ(1.0D),
              3,
              d0,
              d1,
              d2,
              0.15D);
        }
    }
}
