package io.github.mahouryoku.mana;

import net.minecraft.core.Direction;
import net.minecraft.nbt.FloatTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaCapabilitesProvider implements ICapabilitySerializable<FloatTag> {
    // 1.21.1 capability management
    public static Capability<ManaInterface> MANA_CAPABILITY = CapabilityManager.get(new CapabilityToken<>(){});

    private final Mana backend = new Mana();
    private final LazyOptional<ManaInterface> optionalData = LazyOptional.of(() -> backend);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == MANA_CAPABILITY ? optionalData.cast() : LazyOptional.empty();
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