package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Hand;

/**
 * Chance for bows to knockback after firing
 */
public class LooseCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final       String NAME_ID = "curse_loose";
    public final static int    CHANCE  = 30;

    public LooseCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
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
    public void doPostAttack(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (user instanceof PlayerEntity && Cursery.rand.nextInt(CHANCE) == 0)
        {
            ((PlayerEntity) user).drop(user.getMainHandItem(), true);
            user.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof BowItem || stack.getItem() instanceof ToolItem || stack.getItem() instanceof SwordItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return false;
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
