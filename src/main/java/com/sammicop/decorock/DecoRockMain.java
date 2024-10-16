package com.sammicop.decorock;

import com.sammicop.decorock.core.init.Iteminit;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("decorock")
public class DecoRockMain {
    public static final String MOD_ID = "decorock";
    private static final Logger LOGGER = LogManager.getLogger();

    public DecoRockMain() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        Iteminit.Items.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        LOGGER.info("DecoRock mod initialized.");
    }
}