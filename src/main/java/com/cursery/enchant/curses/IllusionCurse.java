package com.cursery.enchant.curses;

import com.cursery.Cursery;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

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

    public IllusionCurse(final Rarity rarity, final EquipmentSlot[] slotTypes)
    {
        super(rarity, EnchantmentCategory.ARMOR, slotTypes);
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
