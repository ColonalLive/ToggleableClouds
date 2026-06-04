package me.ayydxn.toggleableclouds.config;

import dev.isxander.yacl3.api.NameableEnum;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.CustomDescription;
import dev.isxander.yacl3.config.v2.api.autogen.EnumCycler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import me.ayydxn.toggleableclouds.ToggleableCloudsClientMod;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.CloudStatus;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ToggleableCloudsConfig
{
    public static final ConfigClassHandler<ToggleableCloudsConfig> HANDLER = ConfigClassHandler.createBuilder(ToggleableCloudsConfig.class)
            .id(Identifier.fromNamespaceAndPath(ToggleableCloudsClientMod.MOD_ID, "toggleable_clouds_config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("toggleable_clouds_config.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @AutoGen(category = "Toggleable Clouds")
    @CustomDescription("When toggling clouds back on, this determines the cloud status that will be applied.\n\nFancy is the default Minecraft cloud status, while Fast is the one used when clouds are turned off.")
    @EnumCycler
    public ValidCloudStatuses preferredCloudStatus = ValidCloudStatuses.FANCY;

    public enum ValidCloudStatuses implements NameableEnum
    {
        FANCY(CloudStatus.FANCY),
        FAST(CloudStatus.FAST);

        private final CloudStatus value;

        ValidCloudStatuses(CloudStatus value)
        {
            this.value = value;
        }

        public CloudStatus toMinecraft()
        {
            return this.value;
        }

        @Override
        public Component getDisplayName()
        {
            String lowerCaseAfterFirstLetter = this.name().substring(1).toLowerCase();

            return Component.literal(this.name().charAt(0) + lowerCaseAfterFirstLetter);
        }
    }
}
