package io.github.thebluetropics.examplemod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
  public static final String ID = "example_mod";
  public static final Logger LOGGER = LoggerFactory.getLogger(ExampleMod.class);

  @Override
  public void onInitialize() {
    ExampleMod.LOGGER.info("Hello, World! (Common initialize)");
  }
}
