package me.ayydxn.toggleableclouds;

import me.ayydxn.toggleableclouds.commands.ToggleableCloudsCommands;
import me.ayydxn.toggleableclouds.input.KeyInputHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class ToggleableCloudsClientMod implements ClientModInitializer
{
    public static final Logger LOGGER = (Logger) LogManager.getLogger("Toggeable Clouds");
    public static final String MOD_ID = "toggleable-clouds";
    public static boolean areCloudsEnabled = false;

    @Override
    public void onInitializeClient()
    {
        LOGGER.info("Initializing Toggleable Clouds... (Version: {})", FabricLoader.getInstance().getModContainer(MOD_ID)
                .orElseThrow().getMetadata().getVersion().getFriendlyString());

        KeyInputHandler.registerKeyBinds();

        ClientCommandRegistrationCallback.EVENT.register(new ToggleableCloudsCommands());
    }
}
