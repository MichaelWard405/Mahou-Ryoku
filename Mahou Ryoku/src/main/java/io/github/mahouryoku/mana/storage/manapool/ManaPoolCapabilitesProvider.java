package io.github.mahouryoku.mana.storage.manapool;

import net.minecraft.core.Direction;
import net.minecraft.nbt.FloatTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaPoolCapabilitesProvider implements ICapabilitySerializable<FloatTag> {
    // 1.21.1 capability management
    public static Capability<ManaPoolInterface> MANAPOOL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    private final ManaPool backend = new ManaPool();
    private final LazyOptional<ManaPoolInterface> optionalData = LazyOptional.of(() -> backend);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == MANAPOOL_CAPABILITY ? optionalData.cast() : LazyOptional.empty();
    }

    @Override
    public FloatTag serializeNBT() {
        return backend.serializeNBT();
    }

    @Override
    public void deserializeNBT(FloatTag nbt) {
        backend.deserializeNBT(nbt);
    }
}