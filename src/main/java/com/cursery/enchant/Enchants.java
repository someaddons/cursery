package com.cursery.enchant;

import com.cursery.enchant.curses.*;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;

public class Enchants
{
    private static final EquipmentSlot[] ARMOR_SLOTS   =
      new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final EquipmentSlot[] TOOLS_WEAPONS = new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public static BlindCurse           blindEnchant;
    public static HeavyCurse           heavyCurse;
    public static ExplosiveToolCurse   explosiveToolCurse;
    public static HurtfulCurse         hurtfulCurse;
    public static HungryHealthCurse    hungryHealthCurse;
    public static WeaknessCurse        weaknessCurse;
    public static StubbyCurse          stubbyCurse;
    public static RecoilCurse          recoilCurse;
    public static LooseCurse           looseCurse;
    public static AnvilHead            anvilHead;
    public static HungryCurse          hungryCurse;
    public static UndeadCurse          undeadCurse;
    public static PoisonCurse          poisonCurse;
    public static SwitchyWeaponCurse   switchyWeaponCurse;
    public static IllusionCurse        illusionCurse;
    public static SteelFeet            steelFeet;
    public static LadderingCurse       ladderingCurse;
    public static SlownessCurse        slownessCurse;
    public static ElectrifiedToolCurse electrifiedToolCurse;

    public static void registerEnchants()
    {
        blindEnchant = Registry.register(
          Registry.ENCHANTMENT,
          BlindCurse.NAME_ID,
          new BlindCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        heavyCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            HeavyCurse.NAME_ID,
            new HeavyCurse(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS));

        explosiveToolCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            ExplosiveToolCurse.NAME_ID,
            new ExplosiveToolCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS));

        stubbyCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            StubbyCurse.NAME_ID,
            new StubbyCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));

        recoilCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            RecoilCurse.NAME_ID,
            new RecoilCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS));

        looseCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            LooseCurse.NAME_ID,
            new LooseCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));

        hurtfulCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            HurtfulCurse.NAME_ID,
            new HurtfulCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        weaknessCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            WeaknessCurse.NAME_ID,
            new WeaknessCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        anvilHead =
          Registry.register(
            Registry.ENCHANTMENT,
            AnvilHead.NAME_ID,
            new AnvilHead(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS));

        hungryCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            HungryCurse.NAME_ID,
            new HungryCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        undeadCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            UndeadCurse.NAME_ID,
            new UndeadCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        poisonCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            PoisonCurse.NAME_ID,
            new PoisonCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        switchyWeaponCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            SwitchyWeaponCurse.NAME_ID,
            new SwitchyWeaponCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS));

        hungryHealthCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            HungryHealthCurse.NAME_ID,
            new HungryHealthCurse(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS));

        illusionCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            IllusionCurse.NAME_ID,
            new IllusionCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        steelFeet =
          Registry.register(
            Registry.ENCHANTMENT,
            SteelFeet.NAME_ID,
            new SteelFeet(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS));

        ladderingCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            LadderingCurse.NAME_ID,
            new LadderingCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        slownessCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            SlownessCurse.NAME_ID,
            new SlownessCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS));

        electrifiedToolCurse =
          Registry.register(
            Registry.ENCHANTMENT,
            ElectrifiedToolCurse.NAME_ID,
            new ElectrifiedToolCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));
    }
}
