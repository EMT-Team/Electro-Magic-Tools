package amerifrance.emt.gui;

import net.minecraft.client.gui.GuiScreen;
import tterrag.core.common.config.BaseConfigFactory;

public class ConfigGuiFactory extends BaseConfigFactory {
    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConfigGui.class;
    }
}
