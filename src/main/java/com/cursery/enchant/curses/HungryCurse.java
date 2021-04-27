package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class HungryCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final        String NAME_ID = "curse_hungry";
    private final static int    CHANCE  = 15;

    public HungryCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
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
    public void doPostHurt(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (Cursery.rand.nextInt(CHANCE) == 0)
        {
            user.addEffect(new EffectInstance(Effects.HUNGER, 6 * 20 * enchantLevel));
        }
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (Cursery.rand.nextInt(CHANCE) == 0)
        {
            user.addEffect(new EffectInstance(Effects.HUNGER, 6 * 20 * enchantLevel));
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof ArmorItem || stack.getItem() instanceof SwordItem;
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
        return 3;
    }
}
