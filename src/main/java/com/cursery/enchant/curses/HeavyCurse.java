package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

/**
 * Items becomes heavy, causes slowness
 */
public class HeavyCurse extends Enchantment
{
    /**
     * Enchant id
     */
    public static final ResourceLocation NAME_ID = Cursery.curseryRL("curse_heavy_enchant");
    public static final int              CHANCE  = 1000;

    public HeavyCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.ARMOR_CHEST, slotTypes);
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
    public boolean isTreasureOnly()
    {
        return true;
    }

    @Override
    public boolean isCurse()
    {
        return true;
    }
}
