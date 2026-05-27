package xyz.hiveforge.mahouryoku.Registries;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import xyz.hiveforge.mahouryoku.magic_systems.mahou.MahouPool;

import java.util.function.Supplier;


public class MagicRegistries {
    //Registers the Players Codec as a Capability
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "mahouryoku");

    //===============================
    //     Mahou Pool Capability
    //===============================
    public static final Supplier<AttachmentType<MahouPool>> MAHOU_POOL =
            ATTACHMENT_TYPES.register("mahou_pool", () -> AttachmentType.<MahouPool>builder(MahouPool::new)
                    .serialize(MahouPool.MAP_CODEC)
                    //.copyOnDeath()
                    .build());

    public static void register(IEventBus modBus) {
        ATTACHMENT_TYPES.register(modBus);
    }

 }
