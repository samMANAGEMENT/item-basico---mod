package com.sammicop.decorock;

import com.sammicop.decorock.core.init.Iteminit;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod("decorock")
@Mod.EventBusSubscriber(modid = DecoRockMain.MOD_ID, bus = Bus.MOD)
public class DecoRockMain
{
    public static final String MOD_ID = "decorock";
    private static final Logger LOGGER = LogManager.getLogger();

    public DecoRockMain() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Iteminit.Items.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItem(final RegistryEvent.Register<Item> event) {


    }
}
