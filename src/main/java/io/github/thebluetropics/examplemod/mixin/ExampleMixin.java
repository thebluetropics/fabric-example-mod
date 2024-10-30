package io.github.thebluetropics.examplemod.mixin;

import io.github.thebluetropics.examplemod.ExampleMod;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
  @Inject(at = @At("HEAD"), method = "run")
  private void run(CallbackInfo info) {
    ExampleMod.LOGGER.info("Hello, World! (Server Mixin)");
  }
}
