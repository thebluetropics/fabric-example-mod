package io.github.thebluetropics.examplemod.client;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleModClient implements ClientModInitializer {
  public static final Logger LOGGER = LoggerFactory.getLogger(ExampleModClient.class);

  @Override
  public void onInitializeClient() {
    ExampleModClient.LOGGER.info("Hello, World! (Client initialize)");
  }
}
