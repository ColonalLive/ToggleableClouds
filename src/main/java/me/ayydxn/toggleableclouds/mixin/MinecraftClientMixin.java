package me.ayydxn.toggleableclouds.mixin;

import me.ayydxn.toggleableclouds.ToggleableCloudsClientMod;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin
{
    @Inject(method = "<init>", at = @At("TAIL"))
    public void setAreCloudsEnabled(GameConfig gameConfig, CallbackInfo ci)
    {
        ToggleableCloudsClientMod.areCloudsEnabled = Minecraft.getInstance().options.getCloudStatus() != CloudStatus.OFF;
    }
}
