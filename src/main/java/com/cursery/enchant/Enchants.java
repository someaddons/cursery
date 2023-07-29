package com.cursery.enchant;

import com.cursery.Cursery;
import com.cursery.enchant.curses.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Cursery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Enchants
{
    public static final  DeferredRegister<Enchantment> ENCHANTMENTS  = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Cursery.MODID);
    private static final EquipmentSlot[]               ARMOR_SLOTS   =
      new EquipmentSlot[] {EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private static final EquipmentSlot[]               TOOLS_WEAPONS = new EquipmentSlot[] {EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND};

    public static BlindCurse         blindEnchant;
    public static DullWeaponCurse    dullCurse;
    public static HeavyCurse         heavyCurse;
    public static ExplosiveToolCurse explosiveToolCurse;
    public static HurtfulCurse       hurtfulCurse;
    public static HungryHealthCurse  hungryHealthCurse;
    public static WeaknessCurse      weaknessCurse;
    public static StubbyCurse        stubbyCurse;
    public static RecoilCurse        recoilCurse;
    public static LooseCurse         looseCurse;
    public static AnvilHead          anvilHead;
    public static HungryCurse        hungryCurse;
    public static UndeadCurse        undeadCurse;
    public static PoisonCurse          poisonCurse;
    public static SwitchyWeaponCurse   switchyWeaponCurse;
    public static IllusionCurse        illusionCurse;
    public static SteelFeet            steelFeet;
    public static LadderingCurse       ladderingCurse;
    public static SlownessCurse        slownessCurse;
    public static ElectrifiedToolCurse electrifiedToolCurse;

    public static void init()
    {
        ENCHANTMENTS.register(FMLJavaModLoadingContext.get().getModEventBus());
        blindEnchant = new BlindCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(BlindCurse.NAME_ID, () -> blindEnchant);

        heavyCurse = new HeavyCurse(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(HeavyCurse.NAME_ID, () -> heavyCurse);

        explosiveToolCurse = new ExplosiveToolCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS);
        ENCHANTMENTS.register(ExplosiveToolCurse.NAME_ID, () -> explosiveToolCurse);

        stubbyCurse = new StubbyCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(StubbyCurse.NAME_ID, () -> stubbyCurse);

        recoilCurse = new RecoilCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(RecoilCurse.NAME_ID, () -> recoilCurse);

        looseCurse = new LooseCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(LooseCurse.NAME_ID, () -> looseCurse);

        hurtfulCurse = new HurtfulCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(HurtfulCurse.NAME_ID, () -> hurtfulCurse);

        weaknessCurse = new WeaknessCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(WeaknessCurse.NAME_ID, () -> weaknessCurse);

        anvilHead = new AnvilHead(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS);
        ENCHANTMENTS.register(AnvilHead.NAME_ID, () -> anvilHead);

        hungryCurse = new HungryCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(HungryCurse.NAME_ID, () -> hungryCurse);

        undeadCurse = new UndeadCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        ENCHANTMENTS.register(UndeadCurse.NAME_ID, () -> undeadCurse);

        poisonCurse = new PoisonCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(PoisonCurse.NAME_ID, () -> poisonCurse);

        switchyWeaponCurse = new SwitchyWeaponCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS);
        ENCHANTMENTS.register(SwitchyWeaponCurse.NAME_ID, () -> switchyWeaponCurse);

        hungryHealthCurse = new HungryHealthCurse(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS);
        ENCHANTMENTS.register(HungryHealthCurse.NAME_ID, () -> hungryHealthCurse);

        illusionCurse = new IllusionCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        ENCHANTMENTS.register(IllusionCurse.NAME_ID, () -> illusionCurse);

        steelFeet = new SteelFeet(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS);
        ENCHANTMENTS.register(SteelFeet.NAME_ID, () -> steelFeet);

        ladderingCurse = new LadderingCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        ENCHANTMENTS.register(LadderingCurse.NAME_ID, () -> ladderingCurse);

        slownessCurse = new SlownessCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(SlownessCurse.NAME_ID, () -> slownessCurse);

        electrifiedToolCurse = new ElectrifiedToolCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(ElectrifiedToolCurse.NAME_ID, () -> electrifiedToolCurse);

        dullCurse = new DullWeaponCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS);
        ENCHANTMENTS.register(DullWeaponCurse.NAME_ID, () -> dullCurse);
    }
}
