package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

/**
 * Upon getting hit consume saturation to restore a little health
 */
public class HungryHealthCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final       String NAME_ID = "curse_hungryhealth";
    public final static int    CHANCE  = 5;

    public HungryHealthCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
    {
        super(rarity, EnchantmentType.WEAPON, slotTypes);
        setRegistryName(NAME_ID);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (attacker != null && Cursery.rand.nextInt(CHANCE) == 0 && enchantLevel > 0 && user instanceof PlayerEntity && user.getMaxHealth() - user.getHealth() > 4)
        {
            final PlayerEntity player = (PlayerEntity) user;
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
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlotType.CHEST;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
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
