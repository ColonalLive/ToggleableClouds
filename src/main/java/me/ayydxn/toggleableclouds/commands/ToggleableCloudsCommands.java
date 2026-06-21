package me.ayydxn.toggleableclouds.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import me.ayydxn.toggleableclouds.config.ToggleableCloudsConfig;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.Minecraft;
import net.minecraft.commands.CommandBuildContext;
import org.jetbrains.annotations.NotNull;

public class ToggleableCloudsCommands implements ClientCommandRegistrationCallback
{
    @Override
    public void register(CommandDispatcher<FabricClientCommandSource> commandDispatcher, @NotNull CommandBuildContext commandBuildContext)
    {
        commandDispatcher.register(ClientCommands.literal("toggleable-clouds").then(ClientCommands.literal("config").executes(context ->
        {
            Minecraft client = context.getSource().getClient();
            client.execute(() -> client.setScreenAndShow(ToggleableCloudsConfig.HANDLER.generateGui().generateScreen(null)));

            return Command.SINGLE_SUCCESS;
        })));
    }
}
