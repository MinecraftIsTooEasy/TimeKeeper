package com.github.Debris.timekeeper.mixins;

import com.github.Debris.timekeeper.event.TimeHandler;
import net.minecraft.NBTTagCompound;
import net.minecraft.WorldInfoShared;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldInfoShared.class)
public class WorldInfoSharedMixin {
    @Inject(method = "<init>(Lnet/minecraft/NBTTagCompound;)V", at = @At("RETURN"))
    private void onNBTRead(NBTTagCompound nbt, CallbackInfo ci) {
        TimeHandler.getInstance().readNBT(nbt);
    }

    @Inject(method = "updateTagCompound", at = @At("RETURN"))
    private void onNBTWrite(NBTTagCompound nbt, NBTTagCompound playerNBT, CallbackInfo ci) {
        TimeHandler.getInstance().writeNBT(nbt);
    }
}
