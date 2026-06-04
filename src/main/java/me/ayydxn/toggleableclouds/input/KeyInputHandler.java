package me.ayydxn.toggleableclouds.input;

import com.mojang.blaze3d.platform.InputConstants;
import me.ayydxn.toggleableclouds.ToggleableCloudsClientMod;
import me.ayydxn.toggleableclouds.config.ToggleableCloudsConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler
{
    public static final KeyMapping.Category TOGGLEABLE_CLOUDS_CATEGORY = KeyMapping.Category.register(
            Identifier.fromNamespaceAndPath(ToggleableCloudsClientMod.MOD_ID, "toggleable_clouds")
    );

    public static final String KEY_TOGGLE_CLOUDS = String.format("key.%s.toggle_clouds", ToggleableCloudsClientMod.MOD_ID);

    public static KeyMapping toggleCloudsKey;

    public static void registerKeyBinds()
    {
        toggleCloudsKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                KEY_TOGGLE_CLOUDS,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                TOGGLEABLE_CLOUDS_CATEGORY)
        );

        KeyInputHandler.registerKeyInputs();
    }

    private static void registerKeyInputs()
    {
        ClientTickEvents.END_CLIENT_TICK.register(client ->
        {
            if (client.player == null)
                return;

            if (KeyInputHandler.toggleCloudsKey.consumeClick())
            {
                if (ToggleableCloudsClientMod.areCloudsEnabled)
                {
                    Minecraft.getInstance().options.cloudStatus().set(CloudStatus.OFF);

                    ToggleableCloudsClientMod.areCloudsEnabled = false;

                    client.player.sendSystemMessage(Component.translatable("key.toggleable-clouds.toggle_clouds.disabled")
                            .withStyle(ChatFormatting.RED));
                }
                else
                {
                    Minecraft.getInstance().options.cloudStatus().set(ToggleableCloudsConfig.HANDLER.instance().preferredCloudStatus.toMinecraft());

                    ToggleableCloudsClientMod.areCloudsEnabled = true;

                    client.player.sendSystemMessage(Component.translatable("key.toggleable-clouds.toggle_clouds.enabled")
                            .withStyle(ChatFormatting.GREEN));
                }
            }
        });
    }
}
