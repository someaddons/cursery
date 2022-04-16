package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import static com.cursery.Cursery.curseryRL;

public class BlindCurse extends Enchantment
{
    /**
     * Enchant id
     */
    public static final  ResourceLocation NAME_ID = curseryRL("curse_blindness");
    private final static int              CHANCE  = 7;

    public BlindCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.ARMOR, slotTypes);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int level)
    {
        if (Cursery.rand.nextInt(CHANCE) == 0)
        {
            user.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 6 * 20 * level));
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof ArmorItem;
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
        return 3;
    }
}
