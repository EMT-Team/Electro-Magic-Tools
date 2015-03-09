package amerifrance.emt.gui;

import net.minecraft.client.gui.GuiScreen;
import amerifrance.emt.ConfigHandler;
import amerifrance.emt.ModInformation;
import amerifrance.emt.util.TextHelper;
import tterrag.core.api.common.config.IConfigHandler;
import tterrag.core.client.config.BaseConfigGui;

public class ConfigGui extends BaseConfigGui {
    public ConfigGui(GuiScreen parentScreen) {
        super(parentScreen);
    }

    @Override
    protected IConfigHandler getConfigHandler() {
        return ConfigHandler.INSTANCE;
    }

    @Override
    protected String getTitle() {
        return TextHelper.localize("EMT.config.title");
    }

    @Override
    protected String getLangPrefix() {
        return ModInformation.ID + ".config.";
    }
}