package xyz.hiveforge.mahouryoku.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

@EventBusSubscriber(modid = "mahouryoku")
public class ToolTipEvents {
    //Set Mod ID
    public String MODID = "mahouryoku";

    //=========================
    //         ToolTip
    //=========================
    @SubscribeEvent
    public static void onItemToolTip(ItemTooltipEvent event){
        //Get the Item to Apply The tool tip for
        ItemStack itemStack = event.getItemStack();

        //=================================
        //  Apply Tooltip to Mahou Items
        //=================================
        Mahou_Items.MahouData data = itemStack.get(Mahou_Items.MAHOU_DATA.get());

        //==============================
        //            Mahou
        //Applies Mahou Data To Tooltip
        //==============================
        if (data != null) {
            event.getToolTip().add(Component.literal("Mahou: " +  data.mahou()).withStyle(ChatFormatting.AQUA));
        }

        //================================
        //           Affinity
        //  Applies Affinity To Tooltip
        //================================
        if (data != null){
            String affinity = switch (data.affinity()){
                case 1 -> "Inferno";
                default -> "Arcane Neutral";
            };
            event.getToolTip().add(Component.literal("Affinity: " + affinity).withStyle(ChatFormatting.GOLD));
        }

        //==================================
        //          Eccentricity
        //  Applies Eccentricity to Tooltip
        //==================================
        if (data != null){
            String eccentricity = switch (data.eccentricity()){
                case 1 -> "volatile";
                default -> "None";
            };
            event.getToolTip().add(Component.literal("Eccentricity: " + eccentricity).withStyle(ChatFormatting.YELLOW));
        }

        //===================================
        //             Tier
        //  Determines And Applies Mahou Tier
        //           As A Prefix
        //===================================
        if (data != null) {
            List<Component> tooltip = event.getToolTip();
            String Prefix;
            ChatFormatting PrefixColour;
            if (data.mahou() >= 1000){
                Prefix = "Crystalline ";
                PrefixColour = ChatFormatting.RED;
            }else if (data.mahou() >= 100){
                Prefix = "Consolidating ";
                PrefixColour = ChatFormatting.BLUE;
            } else{
                Prefix = "Gathering ";
                PrefixColour = ChatFormatting.WHITE;
            }

            tooltip.set(0, Component.literal(Prefix).withStyle(PrefixColour)
                    .append(Component.translatable(itemStack.getItem().getDescriptionId())));
        }

    }
}
