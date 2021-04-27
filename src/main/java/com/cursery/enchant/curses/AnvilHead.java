package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class AnvilHead extends Enchantment
{
    /**
     * Enchant id
     */
    private final        String NAME_ID = "curse_anvil";
    private final static int    CHANCE  = 30;

    public AnvilHead(final Rarity rarity, final EquipmentSlotType[] slotTypes)
    {
        super(rarity, EnchantmentType.ARMOR, slotTypes);
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
        return stack.getItem() instanceof ArmorItem && ((ArmorItem) stack.getItem()).getSlot() == EquipmentSlotType.HEAD;
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
