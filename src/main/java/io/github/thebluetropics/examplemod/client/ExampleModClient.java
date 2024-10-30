package io.github.thebluetropics.examplemod.client;

import io.github.thebluetropics.examplemod.ExampleMod;
import net.fabricmc.api.ClientModInitializer;

public class ExampleModClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    ExampleMod.LOGGER.info("Hello, World! (Client Setup)");
  }
}
