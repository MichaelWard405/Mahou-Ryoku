package io.github.mahouryoku.mahou.storage.mahoupool;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MahouPoolProvider implements ICapabilitySerializable<CompoundTag> {
    public static Capability<MahouPoolInterFace> MAHOUPOOL_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    private final MahouPool backend = new MahouPool();
    private final LazyOptional<MahouPoolInterFace> optionalData = LazyOptional.of(() -> backend);


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == MAHOUPOOL_CAPABILITY ? optionalData.cast(): LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {return backend.serializeNBT();}

    @Override
    public void deserializeNBT(CompoundTag nbt) {backend.deserializeNBT(nbt);}
}
