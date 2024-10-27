package io.github.thebluetropics.examplemod.item;

import io.github.thebluetropics.examplemod.ExampleMod;
import io.github.thebluetropics.examplemod.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
  public static final Item CUSTOM_ITEM = register("custom_item", new CustomItem(new Item.Settings()));
  public static final BlockItem CUSTOM_BLOCK = register("custom_block", new BlockItem(ModBlocks.CUSTOM_BLOCK, new Item.Settings()));

  public static <T extends Item> T register(String id, T item) {
    return Registry.register(Registries.ITEM, new Identifier(ExampleMod.ID, id), item);
  }

  public static void initialize() { /* ... */ }
}
