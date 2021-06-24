package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.ArrayList;
import java.util.List;

public class IllusionCurse extends Enchantment
{
    /**
     * Enchant id
     */
    private final       String NAME_ID = "curse_illusion";
    public final static int    CHANCE  = 10000;

    private static final List<SoundEvent> events = new ArrayList<>();

    public IllusionCurse(final Rarity rarity, final EquipmentSlotType[] slotTypes)
    {
        super(rarity, EnchantmentType.ARMOR, slotTypes);
        setRegistryName(NAME_ID);

        events.add(SoundEvents.TNT_PRIMED);
        events.add(SoundEvents.CREEPER_PRIMED);
        events.add(SoundEvents.HUSK_AMBIENT);
        events.add(SoundEvents.ZOMBIE_AMBIENT);
        events.add(SoundEvents.ZOMBIFIED_PIGLIN_AMBIENT);
        events.add(SoundEvents.ENDERMAN_AMBIENT);
        events.add(SoundEvents.SKELETON_AMBIENT);
        events.add(SoundEvents.WITCH_AMBIENT);
        events.add(SoundEvents.PORTAL_AMBIENT);
        events.add(SoundEvents.GHAST_SCREAM);
        events.add(SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON);
        events.add(SoundEvents.PHANTOM_AMBIENT);
        events.add(SoundEvents.LAVA_AMBIENT);
        events.add(SoundEvents.SHIELD_BREAK);
    }

    public static SoundEvent getRandomSound()
    {
        return events.get(Cursery.rand.nextInt(events.size()));
    }

    @Override
    protected boolean checkCompatibility(Enchantment enchant)
    {
        return this != enchant;
    }

    @Override
    public void doPostHurt(LivingEntity user, Entity attacker, int damage)
    {
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
