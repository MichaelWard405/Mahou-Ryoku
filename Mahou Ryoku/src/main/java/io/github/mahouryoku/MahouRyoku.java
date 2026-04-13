package io.github.mahouryoku;

import com.mojang.logging.LogUtils;
import io.github.mahouryoku.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(MahouRyoku.MOD_ID)
public class MahouRyoku
{
    public static final String MOD_ID = "mahouryoku";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MahouRyoku(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        ModItems.register(modEventBus);
        modEventBus.register(modEventBus);
        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CRYSTALLINE_MAHOU);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            event.enqueueWork(() -> {
                ItemProperties.register(ModItems.CRYSTALLINE_MAHOU.get(),
                        ResourceLocation.fromNamespaceAndPath("mahouryoku", "texture_state"),
                        (pStack, pLevel, pEntity, pSeed) -> {
                            if (pStack.hasTag() && pStack.getTag().contains("Mahou_Typing")) {
                                return (float) pStack.getTag().getInt("Mahou_Typing");
                            }
                            return 0;
                        });
            });
        }
    }
}
