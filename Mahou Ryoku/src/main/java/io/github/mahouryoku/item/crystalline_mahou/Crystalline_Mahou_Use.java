package io.github.mahouryoku.item.crystalline_mahou;

import io.github.mahouryoku.mahou.storage.mahoupool.MahouPoolProvider;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import java.util.Properties;

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
}
