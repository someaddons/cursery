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
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Handler to catch server tick events
 */
public class EventHandler
{
    public static void onAnvilInput(final ItemStack stack1, final ItemStack stack2, final Player player)
    {
        if (player == null || player.level.isClientSide())
        {
            return;
        }

        CurseEnchantmentHelper.delayNext = true;
        CurseEnchantmentHelper.delayItem = stack1.getItem();
    }

    public static void onAnvilOutput(final Player player, final ItemStack result, final ItemStack input)
    {
        if (player == null || player.level.isClientSide())
        {
            return;
        }

        if (CurseEnchantmentHelper.delayItem == result.getItem())
        {
            if (CurseEnchantmentHelper.checkForRandomCurse(result,
              EnchantmentHelper.getEnchantments(input),
              EnchantmentHelper.getEnchantments(result)))
            {
                if (!player.level.isClientSide())
                {
                    player.containerMenu.broadcastChanges();
                    //((ServerPlayer) event.getPlayer()).refreshContainer(event.getPlayer().containerMenu);
                    PlayerVisualHelper.randomNotificationOnCurseApply((ServerPlayer) player, result);
                }
            }
            else
            {
                PlayerVisualHelper.enchantSuccess((ServerPlayer) player, result);
            }
        }
    }

    public static void onSuccessEnchant(final ItemStack itemStack, final ContainerLevelAccess access)
    {
        access.execute((level, pos) ->
        {
            if (level.isClientSide)
            {
                return;
            }

            final Player player = level.getNearestPlayer(
              TargetingConditions.forNonCombat().selector(entity -> entity instanceof ServerPlayer && ((ServerPlayer) entity).containerMenu != null),
              pos.getX(), pos.getY(), pos.getZ());

            // Save the combination to add some visual output for the player
            CurseEnchantmentHelper.notifyStack = itemStack;
            CurseEnchantmentHelper.notifyPlayer = (ServerPlayer) player;
        });
    }

    public static void onPlayerTick(Player player)
    {
        if (player.level.isClientSide)
        {
            if (Cursery.rand.nextInt(IllusionCurse.CHANCE) == 0)
            {
                for (final ItemStack armor : player.getArmorSlots())
                {
                    if (armor.isEmpty())
                    {
                        continue;
                    }

                    if (EnchantmentHelper.getItemEnchantmentLevel(Enchants.illusionCurse, armor) > 0)
                    {
                        player.level.playLocalSound(player.getX() - 1,
                          player.getY(),
                          player.getZ(),
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
            for (final ItemStack armor : player.getArmorSlots())
            {
                if (armor.isEmpty())
                {
                    continue;
                }

                final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.heavyCurse, armor);
                if (level > 0)
                {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 7 * 20 * level));
                }
            }
        }

        if (Cursery.rand.nextInt(UndeadCurse.CHANCE) == 0)
        {
            final ItemStack armor = player.getItemBySlot(EquipmentSlot.HEAD);
            if (!armor.isEmpty())
            {
                final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.undeadCurse, armor);
                if (level > 0)
                {
                    if (player.level.isDay() && player.getLightLevelDependentMagicValue() > 9 && player.level.canSeeSky(player.blockPosition()))
                    {
                        player.setSecondsOnFire(10 * level);
                    }
                    else
                    {
                        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 20 * 20 * level));
                    }
                }
            }
        }

        if (Cursery.rand.nextInt(LadderingCurse.CHANCE) == 0)
        {
            final ItemStack armor = player.getItemBySlot(EquipmentSlot.FEET);
            if (!armor.isEmpty())
            {
                if (EnchantmentHelper.getItemEnchantmentLevel(Enchants.ladderingCurse, armor) > 0)
                {
                    final BlockState state = player.getFeetBlockState();

                    if (state.getBlock() instanceof LadderBlock)
                    {
                        final Direction facing = state.getValue(HorizontalDirectionalBlock.FACING).getOpposite();

                        player.hurtMarked = true;
                        player
                          .knockback(4f,
                            facing.getStepX(),
                            facing.getStepZ());
                    }
                }
            }
        }
    }

    public static void onBlockBreak(ServerPlayer player, final BlockPos blockPos)
    {
        if (Cursery.rand.nextInt(ExplosiveToolCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.explosiveToolCurse, player.getMainHandItem()) > 0)
        {
            player.level.explode(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(), 3, false, Level.ExplosionInteraction.TNT);
        }

        if (Cursery.rand.nextInt(LooseCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.looseCurse, player.getMainHandItem()) > 0)
        {
            player.drop(player.getMainHandItem(), true);
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }

        final int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.electrifiedToolCurse, player.getMainHandItem());
        if (level > 0 && Cursery.rand.nextInt(100) <= level)
        {
            BlockPos blockpos = player.blockPosition();
            if (player.level.canSeeSky(blockpos))
            {
                LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(player.level);
                if (lightningboltentity != null)
                {
                    lightningboltentity.moveTo(Vec3.atBottomCenterOf(blockpos));
                    lightningboltentity.setCause((ServerPlayer) player);
                    player.level.addFreshEntity(lightningboltentity);
                }
            }
        }
    }

    public static void onArrowFire(final ServerPlayer player)
    {
        if (Cursery.rand.nextInt(RecoilCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.recoilCurse, player.getMainHandItem()) > 0)
        {
            player.hurtMarked = true;
            player
              .knockback(1.0F,
                player.getLookAngle().x,
                player.getLookAngle().z);
        }

        if (Cursery.rand.nextInt(LooseCurse.CHANCE) == 0 && EnchantmentHelper.getItemEnchantmentLevel(Enchants.looseCurse, player.getMainHandItem()) > 0)
        {
            ((Player) player).drop(player.getMainHandItem(), true);
            player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    public static void onDestroySpeed(final Player player, final CallbackInfoReturnable<Float> speed)
    {
        if (player == null)
        {
            return;
        }

        int level = EnchantmentHelper.getItemEnchantmentLevel(Enchants.slownessCurse, player.getMainHandItem());
        if (level > 0)
        {
            speed.setReturnValue(speed.getReturnValue() * (1f - (level * 0.20f)));
        }
    }
}
