package amerifrance.emt.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import amerifrance.emt.ModInformation;
import amerifrance.emt.gui.container.ContainerWandRecharger;
import amerifrance.emt.modules.ic2.tile.TileWandRecharger;
import amerifrance.emt.util.TextHelper;

public class GuiWandRecharger extends GuiContainer {

    public GuiWandRecharger(InventoryPlayer inventory, TileWandRecharger te) {
        super(new ContainerWandRecharger(inventory, te));
        xSize = 176;
        ySize = 165;
    }

    public static final ResourceLocation texture = new ResourceLocation(ModInformation.ID, "textures/guis/wandcharger.png");

    @Override
    public void drawGuiContainerBackgroundLayer(float f, int j, int i) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(TextHelper.localize("gui.EMT.wandRecharge.title"), 6, 6, 4210752);
        this.fontRendererObj.drawString(TextHelper.localize("gui.EMT.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }
}
