package io.github.thebluetropics.examplemod.block;

import io.github.thebluetropics.examplemod.ExampleMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
  public static final Block CUSTOM_BLOCK = register("custom_block", new CustomBlock(AbstractBlock.Settings.create()));

  public static <T extends Block> T register(String id, T block) {
    return Registry.register(Registries.BLOCK, new Identifier(ExampleMod.ID, id), block);
  }

  public static void initialize() { /* ... */ }
}
