package io.github.thebluetropics.examplemod;

import io.github.thebluetropics.examplemod.block.ModBlocks;
import io.github.thebluetropics.examplemod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
  public static final String ID = "examplemod";
  public static final Logger LOGGER = LoggerFactory.getLogger(ID);

  @Override
  public void onInitialize() {
    ModBlocks.initialize();
    ModItems.initialize();

    ExampleMod.LOGGER.info("Hello, World! (Common initialize)");
  }
}
