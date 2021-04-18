package com.hostilevillages.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;

@Mixin(value = ITag.class, priority = 900)
/**
 * Do a reverse lookup for missing tags, there is some sp bugs where reloading the world leaves some old tag references which then fail when saving structures. So we update those
 */
public interface ITagMixin
{
    @Overwrite
    public static <T> Codec<ITag<T>> codec(Supplier<ITagCollection<T>> iTagCollection)
    {
        return ResourceLocation.CODEC.flatXmap((p_232949_1_) -> {
            return Optional.ofNullable(iTagCollection.get().getTag(p_232949_1_)).map(DataResult::success).orElseGet(() -> {
                return DataResult.error("Unknown tag: " + p_232949_1_);
            });
        }, (tag) -> {

            ResourceLocation id = iTagCollection.get().getId(tag);
            if (id == null)
            {
                // Fallback id lookup
                Collection<ResourceLocation> tags = iTagCollection.get().getMatchingTags(tag.getValues().get(0));

                for (final ResourceLocation currentID : tags)
                {
                    final ITag compare = iTagCollection.get().getTag(currentID);
                    if (compare != null)
                    {
                        if (compare.getValues().equals(tag.getValues()))
                        {
                            id = currentID;
                            break;
                        }
                    }
                }
            }

            return Optional.ofNullable(id).map(DataResult::success).orElseGet(() -> {
                return DataResult.error("Unknown tag: " + tag);
            });
        });
    }
}
