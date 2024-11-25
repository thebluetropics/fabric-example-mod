package io.github.thebluetropics.examplemod.client.mixin;

import io.github.thebluetropics.examplemod.client.ExampleModClient;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class TestClientMixin {
  @Inject(at = @At("HEAD"), method = "run")
  private void run(CallbackInfo info) {
    ExampleModClient.LOGGER.info("Hello, World! (Client mixin)");
  }
}
