package com.sammicop.decorock.core.init;

import com.sammicop.decorock.DecoRockMain;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryBuilder;

public class Iteminit {
    public static final DeferredRegister<Item> Items = DeferredRegister.create(ForgeRegistries.ITEMS,
            DecoRockMain.MOD_ID);

    public static final RegistryObject<Item> PRIMER_ITEM = Items.register("primer_item",
            () -> new Item(new Item.Properties().tab(ItemGroup.TAB_MISC)));


}
