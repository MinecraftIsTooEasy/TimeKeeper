package com.github.Debris.timekeeper.mixins;

import com.github.Debris.timekeeper.event.TimeHandler;
import net.minecraft.DedicatedServer;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {
    @Inject(method = "playerLoggedIn", at = @At("RETURN"))
    private void onPlayerLogIn(ServerPlayer serverPlayer, CallbackInfo ci) {
        TimeHandler.getInstance().onPlayerLoggedIn(serverPlayer);
    }

    @Inject(method = "playerLoggedOut", at = @At("RETURN"))
    private void onPlayerLogOut(ServerPlayer serverPlayer, CallbackInfo ci) {
        TimeHandler.getInstance().onPlayerLoggedOut(serverPlayer);
    }
}
