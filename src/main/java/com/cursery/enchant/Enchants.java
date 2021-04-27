package com.cursery.enchant;

import com.cursery.Cursery;
import com.cursery.enchant.curses.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Cursery.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Enchants
{
    private static final EquipmentSlotType[] ARMOR_SLOTS   =
      new EquipmentSlotType[] {EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
    private static final EquipmentSlotType[] TOOLS_WEAPONS = new EquipmentSlotType[] {EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND};

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

    @SubscribeEvent
    public static void registerEnchants(final RegistryEvent.Register<Enchantment> event)
    {
        blindEnchant = new BlindCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        event.getRegistry().register(blindEnchant);

        heavyCurse = new HeavyCurse(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS);
        event.getRegistry().register(heavyCurse);

        explosiveToolCurse = new ExplosiveToolCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS);
        event.getRegistry().register(explosiveToolCurse);

        stubbyCurse = new StubbyCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        event.getRegistry().register(stubbyCurse);

        recoilCurse = new RecoilCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS);
        event.getRegistry().register(recoilCurse);

        looseCurse = new LooseCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        event.getRegistry().register(looseCurse);

        hurtfulCurse = new HurtfulCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        event.getRegistry().register(hurtfulCurse);

        weaknessCurse = new WeaknessCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        event.getRegistry().register(weaknessCurse);

        anvilHead = new AnvilHead(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS);
        event.getRegistry().register(anvilHead);

        hungryCurse = new HungryCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        event.getRegistry().register(hungryCurse);

        undeadCurse = new UndeadCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        event.getRegistry().register(undeadCurse);

        poisonCurse = new PoisonCurse(Enchantment.Rarity.COMMON, ARMOR_SLOTS);
        event.getRegistry().register(poisonCurse);

        switchyWeaponCurse = new SwitchyWeaponCurse(Enchantment.Rarity.RARE, TOOLS_WEAPONS);
        event.getRegistry().register(switchyWeaponCurse);

        hungryHealthCurse = new HungryHealthCurse(Enchantment.Rarity.VERY_RARE, ARMOR_SLOTS);
        event.getRegistry().register(hungryHealthCurse);

        illusionCurse = new IllusionCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        event.getRegistry().register(illusionCurse);

        steelFeet = new SteelFeet(Enchantment.Rarity.UNCOMMON, ARMOR_SLOTS);
        event.getRegistry().register(steelFeet);

        ladderingCurse = new LadderingCurse(Enchantment.Rarity.RARE, ARMOR_SLOTS);
        event.getRegistry().register(ladderingCurse);

        slownessCurse = new SlownessCurse(Enchantment.Rarity.COMMON, TOOLS_WEAPONS);
        event.getRegistry().register(slownessCurse);

        electrifiedToolCurse = new ElectrifiedToolCurse(Enchantment.Rarity.UNCOMMON, TOOLS_WEAPONS);
        event.getRegistry().register(electrifiedToolCurse);
    }
}
