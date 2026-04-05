package io.github.mahouryoku.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModTags {
    public static final TagKey<Item> HAS_QUALITY = ItemTags.create(ResourceLocation.fromNamespaceAndPath("mahouryoku", "has_quality"));
}