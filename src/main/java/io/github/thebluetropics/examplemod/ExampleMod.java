package io.github.thebluetropics.examplemod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
  public static final String ID = "examplemod";
  public static final Logger LOGGER = LoggerFactory.getLogger(ID);

  @Override
  public void onInitialize() {
    ExampleMod.LOGGER.info("Hello, World! (Common initialize)");
  }
}
