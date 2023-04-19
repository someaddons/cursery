package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * Upon getting hit consume saturation to restore a little health
 */
public class HungryHealthCurse extends Enchantment
{
    /**
     * Enchant id
     */
    public static final ResourceLocation NAME_ID = Cursery.curseryRL("curse_hungryhealth");
    public final static int              CHANCE  = 5;

    public HungryHealthCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.WEAPON, slotTypes);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (attacker != null && Cursery.rand.nextInt(CHANCE) == 0 && enchantLevel > 0 && user instanceof Player && user.getMaxHealth() - user.getHealth() > 4)
        {
            final Player player = (Player) user;
            if (player.getFoodData().getFoodLevel() > 1)
            {
                final int consume = Math.min(player.getFoodData().getFoodLevel() - 1, 2);
                player.getFoodData().setFoodLevel(player.getFoodData().getFoodLevel() - consume);
                player.setHealth(player.getHealth() + consume * 2);
            }
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getType().getSlot() == EquipmentSlot.CHEST;
    }

    @Override
    public boolean isTreasureOnly()
    {
        return true;
    }

    @Override
    public boolean isCurse()
    {
        return true;
    }

    @Override
    public int getMinLevel()
    {
        return 1;
    }

    @Override
    public int getMaxLevel()
    {
        return 1;
    }
}
