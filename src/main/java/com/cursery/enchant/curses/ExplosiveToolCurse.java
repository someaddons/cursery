package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;

public class ExplosiveToolCurse extends Enchantment
{
    /**
     * Enchant id
     */
    public static final ResourceLocation NAME_ID = Cursery.curseryRL("curse_explosive");
    public final static int              CHANCE  = 7;

    public ExplosiveToolCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.WEAPON, slotTypes);
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostAttack(LivingEntity user, Entity attacker, int enchantLevel)
    {
        if (Cursery.rand.nextInt(CHANCE) == 0 && attacker != null && enchantLevel > 0)
        {
            user.level.explode(null, attacker.getX(), attacker.getY(), attacker.getZ(), 3, false, Level.ExplosionInteraction.TNT);
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof DiggerItem || stack.getItem() instanceof SwordItem;
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
