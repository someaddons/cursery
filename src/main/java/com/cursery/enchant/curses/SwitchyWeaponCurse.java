package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class SwitchyWeaponCurse extends Enchantment
{
    /**
     * Enchant id
     */
    public static final String NAME_ID = "curse_switchy";
    public final static int    CHANCE  = 20;

    public SwitchyWeaponCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
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
        if (Cursery.rand.nextInt(CHANCE) == 0 && attacker != null && enchantLevel > 0 && user instanceof Player)
        {
            final Player playerEntity = ((Player) user);
            final ItemStack mainHand = playerEntity.getMainHandItem();
            playerEntity.setItemInHand(InteractionHand.MAIN_HAND, playerEntity.getOffhandItem());
            playerEntity.setItemInHand(InteractionHand.OFF_HAND, mainHand);
        }
    }

    @Override
    public boolean canEnchant(ItemStack stack)
    {
        return stack.getItem() instanceof SwordItem;
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
