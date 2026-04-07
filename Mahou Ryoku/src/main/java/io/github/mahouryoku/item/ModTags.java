package io.github.mahouryoku.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModTags {
    public static final TagKey<Item> HAS_QUALITY = ItemTags.create(ResourceLocation.fromNamespaceAndPath("mahouryoku", "has_quality"));
    public static final TagKey<Item> HAS_PURITY = ItemTags.create(ResourceLocation.fromNamespaceAndPath("mahouryoku", "has_purity"));
    public static final TagKey<Item> MAHOU_TYPING = ItemTags.create(ResourceLocation.fromNamespaceAndPath("mahouryoku", "mahou_typing"));
}