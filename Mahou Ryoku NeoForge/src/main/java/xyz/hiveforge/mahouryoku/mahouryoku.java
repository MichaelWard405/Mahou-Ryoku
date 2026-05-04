package xyz.hiveforge.mahouryoku;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import xyz.hiveforge.mahouryoku.Registries.MagicRegistries;
import xyz.hiveforge.mahouryoku.Registries.PlayerValueEvents;

@Mod(mahouryoku.MODID)
public class mahouryoku {
    public static final String MODID = "mahouryoku";
    public static final Logger LOGGER = LogUtils.getLogger();

    public mahouryoku(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        NeoForge.EVENT_BUS.register(new PlayerValueEvents());
        modEventBus.addListener(this::addCreative);
        MagicRegistries.register(modEventBus);


    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
}
