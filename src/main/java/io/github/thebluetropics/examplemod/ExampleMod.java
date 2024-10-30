package io.github.thebluetropics.examplemod;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleMod implements ModInitializer {
  public static final String ID = "examplemod";
  public static final Logger LOGGER = LogManager.getLogger(ID);

  @Override
  public void onInitialize() {
    ExampleMod.LOGGER.info("Hello, World! (Common Setup)");
  }
}
