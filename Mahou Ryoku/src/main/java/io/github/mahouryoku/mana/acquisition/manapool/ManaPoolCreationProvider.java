package io.github.mahouryoku.mana.acquisition.manapool;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaPoolCreationProvider implements ICapabilitySerializable<CompoundTag> {

    public static Capability<ManaPoolCreationInterface> MANAPOOLCREATION_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final ManaPoolCreation backend = new ManaPoolCreation();
    private final LazyOptional<ManaPoolCreationInterface> optionalData = LazyOptional.of(() -> backend);
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == MANAPOOLCREATION_CAPABILITY ? optionalData.cast(): LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() { return backend.serializeNBT();}

    @Override
    public void deserializeNBT(CompoundTag nbt) { backend.deserializeNBT(nbt);}
}
