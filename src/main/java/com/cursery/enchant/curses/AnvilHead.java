package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;

public class AnvilHead extends Enchantment
{
    /**
     * Enchant id
     */
    private final        String NAME_ID = "curse_anvil";
    private final static int    CHANCE  = 30;

    public AnvilHead(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.ARMOR, slotTypes);
        setRegistryName(NAME_ID);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int damage)
    {
        if (Cursery.rand.nextInt(CHANCE) == 0)
        {
            if (user.level.getBlockState(user.blockPosition().above(2)).getMaterial() == Material.AIR
                  && user.level.getBlockState(user.blockPosition().above(3)).getMaterial() == Material.AIR)
            {
                user.level.setBlock(user.blockPosition().above(3), Blocks.DAMAGED_ANVIL.defaultBlockState(), 3);
            }
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlot.HEAD;
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
    public boolean isTreasureOnly()
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
