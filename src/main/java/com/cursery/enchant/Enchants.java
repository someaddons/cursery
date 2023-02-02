package com.cursery.enchant;

import com.cursery.enchant.curses.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
          BuiltInRegistries.ENCHANTMENT,
          BlindCurse.NAME_ID,
          new BlindCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        heavyCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            HeavyCurse.NAME_ID,
            new HeavyCurse(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS));

        explosiveToolCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            ExplosiveToolCurse.NAME_ID,
            new ExplosiveToolCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS));

        stubbyCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            StubbyCurse.NAME_ID,
            new StubbyCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));

        recoilCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            RecoilCurse.NAME_ID,
            new RecoilCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS));

        looseCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            LooseCurse.NAME_ID,
            new LooseCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));

        hurtfulCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            HurtfulCurse.NAME_ID,
            new HurtfulCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        weaknessCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            WeaknessCurse.NAME_ID,
            new WeaknessCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        anvilHead =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            AnvilHead.NAME_ID,
            new AnvilHead(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS));

        hungryCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            HungryCurse.NAME_ID,
            new HungryCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        undeadCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            UndeadCurse.NAME_ID,
            new UndeadCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        poisonCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            PoisonCurse.NAME_ID,
            new PoisonCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS));

        switchyWeaponCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            SwitchyWeaponCurse.NAME_ID,
            new SwitchyWeaponCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS));

        hungryHealthCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            HungryHealthCurse.NAME_ID,
            new HungryHealthCurse(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS));

        illusionCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            IllusionCurse.NAME_ID,
            new IllusionCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        steelFeet =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            SteelFeet.NAME_ID,
            new SteelFeet(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS));

        ladderingCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            LadderingCurse.NAME_ID,
            new LadderingCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS));

        slownessCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            SlownessCurse.NAME_ID,
            new SlownessCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS));

        electrifiedToolCurse =
          Registry.register(
            BuiltInRegistries.ENCHANTMENT,
            ElectrifiedToolCurse.NAME_ID,
            new ElectrifiedToolCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS));
    }
}
