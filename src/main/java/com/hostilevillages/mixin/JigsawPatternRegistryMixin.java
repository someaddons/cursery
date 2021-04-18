package com.hostilevillages.mixin;

import com.hostilevillages.HostileVillages;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPatternRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JigsawPatternRegistry.class)
/**
 * Redirect the registry to add replacements
 */
public class JigsawPatternRegistryMixin
{
    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void onregister(final JigsawPattern pattern, final CallbackInfoReturnable<JigsawPattern> cir)
    {
        final JigsawPattern rpattern = HostileVillages.getReplacement(pattern);

        if (rpattern != null)
        {
            WorldGenRegistries.register(WorldGenRegistries.TEMPLATE_POOL, rpattern.getName(), rpattern);
            cir.setReturnValue(rpattern);
        }
    }
}
