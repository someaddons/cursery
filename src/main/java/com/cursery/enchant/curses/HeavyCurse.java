package com.cursery.enchant.curses;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

/**
 * Items becomes heavy, causes slowness
 */
public class HeavyCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final       String NAME_ID = "curse_heavy_enchant";
    public static final int    CHANCE  = 1000;

    public HeavyCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
    {
        super(rarity, EnchantmentType.ARMOR_CHEST, slotTypes);
        setRegistryName(NAME_ID);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof ArmorItem;
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

    @Override
    public boolean isCurse()
    {
        return true;
    }

    @Override
    public boolean isTreasureOnly()
    {
        return true;
    }
}
