package io.github.mahouryoku.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "mahouryoku")
public class ToolTipEvent {
    @SubscribeEvent
    public static void onToolTip(ItemTooltipEvent event){
        ItemStack stack = event.getItemStack();
        if (stack.is(ModTags.MAHOU_TYPING)){
            int mahou_typing = 0;
            if (stack.hasTag() && stack.getTag().contains("Mahou_Typing")){
                mahou_typing = stack.getTag().getInt("Mahou_Typing");
            }
            String mahoutypingtext = switch (mahou_typing){
                case 1 -> "Inferno";
                case 2 -> "Celestial";
                default -> "Arcane Netural";
            };
            event.getToolTip().add(Component.literal(mahoutypingtext));
        }
        if (stack.is(ModTags.HAS_PURITY)){
            int purity = 0;
            if (stack.hasTag() && stack.getTag().contains("Purity")){
                purity = stack.getTag().getInt("Purity");
            }
            String purityText = switch (purity){
                case 1 -> "poor";
                case 2 -> "Native";
                case 3 -> "Rich";
                default -> "Unuseable";
            };
            event.getToolTip().add(Component.literal("Purity " + purityText));
        }
        if (stack.is(ModTags.HAS_QUALITY)){
            int quality = 0;
            if (stack.hasTag() && stack.getTag().contains("Quality")){
                quality = stack.getTag().getInt("Quality");
            }
            String qualityText = switch (quality){
                case 1 -> "Poor";
                case 2 -> "Native";
                case 3 -> "Rich";
                default -> "Unuseable";
            };
            event.getToolTip().add(Component.literal("Quality " + qualityText));
        }
    }
}
