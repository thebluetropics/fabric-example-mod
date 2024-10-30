package io.github.thebluetropics.examplemod.mixin.client;

import io.github.thebluetropics.examplemod.ExampleMod;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class ExampleMixinClient {
  @Inject(at = @At("HEAD"), method = "run")
  private void run(CallbackInfo info) {
    ExampleMod.LOGGER.info("Hello, World! (Client Mixin)");
  }
}
