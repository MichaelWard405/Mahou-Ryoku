package io.github.mahouryoku.item.crystalline_mahou;
import io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;


import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;


public class Crystalline_Mahou_Use extends Item{
    public Crystalline_Mahou_Use(Item.Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            player.getCapability(MahouPoolProvider.MAHOUPOOL_CAPABILITY).ifPresent(mahou -> {
                float CurrentMahouSize = mahou.MahouPoolSize();
                mahou.MaxMahouPoolSize(CurrentMahouSize + 10f);
            });
            if (!player.getAbilities().instabuild) {
                itemStack.shrink(1);
            }
        }
    return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide());
    }

    public void appendHoverText(ItemStack stack, @Nullable Level level, List<net.minecraft.network.chat.Component> tooltip, TooltipFlag flag) {
        int Purity = GetPurity(stack);
        tooltip.add(Component.literal("Purity: " + Purity));
        super.appendHoverText(stack, level, tooltip, flag);
    }


    public static void SetPurity(ItemStack stack, int Purity) {
        CompoundTag NBT = stack.getOrCreateTag();
        NBT.putInt("Purity", Purity);
    }
    public static int GetPurity(ItemStack stack) {
        CompoundTag NBT = stack.getTag();
        if (NBT != null && NBT.contains("Purity")) {
            return NBT.getInt("Purity");
        }
        return 0;
    }



}