package com.cursery.event;

import com.cursery.Cursery;
import com.cursery.enchant.CurseEnchantmentHelper;
import com.cursery.enchant.Enchants;
import com.cursery.enchant.PlayerVisualHelper;
import com.cursery.enchant.curses.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.enchanting.EnchantmentLevelSetEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.thread.EffectiveSide;

/**
 * Handler to catch server tick events
 */
public class EventHandler
{
    @SubscribeEvent
    public static void onAnvilInput(final AnvilUpdateEvent event)
    {
        if (event.getPlayer() != null && event.getPlayer().level.isClientSide() || event.getPlayer() == null && EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        CurseEnchantmentHelper.delayNext = true;
        CurseEnchantmentHelper.delayItem = event.getLeft().getItem();
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onAnvilOutput(final AnvilRepairEvent event)
    {
        if (event.getPlayer() != null && event.getPlayer().level.isClientSide() || event.getPlayer() == null && EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        if (CurseEnchantmentHelper.delayItem == event.getItemResult().getItem())
        {
            if (CurseEnchantmentHelper.checkForRandomCurse(event.getItemResult(),
              EnchantmentHelper.getEnchantments(event.getItemInput()),
              EnchantmentHelper.getEnchantments(event.getItemResult())))
            {
                if (event.getPlayer() != null && !event.getPlayer().level.isClientSide())
                {
                    event.getPlayer().containerMenu.broadcastChanges();
                    //((ServerPlayer) event.getPlayer()).refreshContainer(event.getPlayer().containerMenu);
                    PlayerVisualHelper.randomNotificationOnCurseApply((ServerPlayer) event.getPlayer(), event.getItemResult());
                }
            }
        }
    }

    @SubscribeEvent
    public static void on(EnchantmentLevelSetEvent event)
    {
        if (event.getWorld().isClientSide)
        {
            return;
        }

        final Player player = event.getWorld().getNearestPlayer(
          TargetingConditions.forNonCombat().selector(entity -> entity instanceof ServerPlayer && ((ServerPlayer) entity).containerMenu != null),
          event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());

        // Save the combination to add some visual output for the player
        CurseEnchantmentHelper.notifyStack = event.getItem();
        CurseEnchantmentHelper.notifyPlayer = (ServerPlayer) player;
    }

    private static BlockPos lastPos = BlockPos.ZERO;

    @SubscribeEvent
    public static void on(TickEvent.PlayerTickEvent event)
    {
        if (event.player.level.isClientSide)
        {
            if (Cursery.rand.nextInt(IllusionCurse.CHANCE) == 0)
            {
                for (final ItemStack armor : event.player.getArmorSlots())
                {
                    if (armor.isEmpty())
                    {
                        continue;
                    }

                    if (EnchantmentHelper.getItemEnchantmentLevel(Enchants.illusionCurse, armor) > 0)
                    {
                        event.player.level.playLocalSound(event.player.getX() - 1,
                          event.player.getY(),
                          event.player.getZ(),
                          IllusionCurse.getRandomSound(),
                          SoundSource.AMBIENT,
                          0.8f,
                          1.0f,
                          false);
                        break;
                    }
                }
            }
            return;
        }

        if (Cursery.rand.nextInt(HeavyCurse.CHANCE) == 0)
        {
            for (final ItemStack armor : event.player.getArmorSlots())
            {
                if (armor.isEmpty())
                {
                    continue;
                }

                final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.heavyCurse, armor);
                if (level > 0)
                {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 7 * 20 * level));
                }
            }
        }

        if (Cursery.rand.nextInt(UndeadCurse.CHANCE) == 0)
        {
            final ItemStack armor = event.player.getItemBySlot(EquipmentSlot.HEAD);
            if (!armor.isEmpty())
            {
                final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.undeadCurse, armor);
                if (level > 0)
                {
                    if (event.player.level.isDay() && event.player.getBrightness() > 0.5f && event.player.level.canSeeSky(event.player.blockPosition()))
                    {
                        event.player.setSecondsOnFire(10 * level);
                    }
                    else
                    {
                        event.player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20 * level));
                    }
                }
            }
        }

        if (Cursery.rand.nextInt(LadderingCurse.CHANCE) == 0)
        {
            final ItemStack armor = event.player.getItemBySlot(EquipmentSlot.FEET);
            if (!armor.isEmpty())
            {
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchants.ladderingCurse, armor) > 0)
                {
                    final BlockState state = event.player.getFeetBlockState();

                    if (state.getBlock() instanceof LadderBlock)
                    {
                        final Direction facing = state.getValue(HorizontalDirectionalBlock.FACING).getOpposite();

                        event.player.hurtMarked = true;
                        event.player
                          .knockback(4f,
                            facing.getStepX(),
                            facing.getStepZ());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void on(BlockEvent.BreakEvent event)
    {
        if (event.getPlayer() == null || event.getPlayer().level.isClientSide)
        {
            return;
        }

        if (Cursery.rand.nextInt(ExplosiveToolCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.explosiveToolCurse, event.getPlayer().getMainHandItem()) > 0)
        {
            event.getPlayer().level.explode(null, event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), 3, false, Explosion.BlockInteraction.DESTROY);
        }

        if (Cursery.rand.nextInt(LooseCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.looseCurse, event.getPlayer().getMainHandItem()) > 0)
        {
            ((Player) event.getPlayer()).drop(event.getPlayer().getMainHandItem(), true);
            event.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }

        final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.electrifiedToolCurse, event.getPlayer().getMainHandItem());
        if (level > 0 && Cursery.rand.nextInt(100) <= level)
        {
            BlockPos blockpos = event.getPlayer().blockPosition();
            if (event.getPlayer().level.canSeeSky(blockpos))
            {
                LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(event.getPlayer().level);
                if (lightningboltentity != null)
                {
                    lightningboltentity.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningboltentity.setCause((ServerPlayer) event.getPlayer());
                    event.getPlayer().level.addFreshEntity(lightningboltentity);
                }
            }
        }
    }

    @SubscribeEvent
    public static void on(ArrowLooseEvent event)
    {
        if (event.getPlayer() == null || event.getPlayer().level.isClientSide)
        {
            return;
        }

        if (Cursery.rand.nextInt(StubbyCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.stubbyCurse, event.getPlayer().getMainHandItem()) > 0)
        {
            event.setCharge(5);
        }

        if (Cursery.rand.nextInt(RecoilCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.recoilCurse, event.getPlayer().getMainHandItem()) > 0)
        {
            event.getPlayer().hurtMarked = true;
            event.getPlayer()
              .knockback(1.0F,
                event.getPlayer().getLookAngle().x,
                event.getPlayer().getLookAngle().z);
        }

        if (Cursery.rand.nextInt(LooseCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.looseCurse, event.getPlayer().getMainHandItem()) > 0)
        {
            ((Player) event.getPlayer()).drop(event.getPlayer().getMainHandItem(), true);
            event.getPlayer().setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    @SubscribeEvent
    public static void on(LivingFallEvent event)
    {
        if (event.getEntity() == null || event.getEntity().level.isClientSide)
        {
            return;
        }

        final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.steelFeet, event.getEntityLiving().getItemBySlot(EquipmentSlot.FEET));
        if (level > 0)
        {
            event.setDistance(event.getDistance() + 1.7f);
            event.setDamageMultiplier(event.getDamageMultiplier() * level);
        }
    }

    @SubscribeEvent
    public static void on(PlayerEvent.BreakSpeed event)
    {
        if (event.getEntity() == null || event.getEntity().level.isClientSide)
        {
            return;
        }

        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.slownessCurse, event.getEntityLiving().getMainHandItem());
        if (level > 0)
        {
            event.setNewSpeed(event.getOriginalSpeed() * (1f - (level * 0.20f)));
        }
    }
}
