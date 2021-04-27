package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;

public class SwitchyWeaponCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final       String NAME_ID = "curse_switchy";
    public final static int    CHANCE  = 20;

    public SwitchyWeaponCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
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
        if (Cursery.rand.nextInt(CHANCE) == 0 && attacker != null && enchantLevel > 0 && user instanceof PlayerEntity)
        {
            final PlayerEntity playerEntity = ((PlayerEntity) user);
            final ItemStack mainHand = playerEntity.getMainHandItem();
            playerEntity.setItemInHand(Hand.MAIN_HAND, playerEntity.getOffhandItem());
            playerEntity.setItemInHand(Hand.OFF_HAND, mainHand);
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
