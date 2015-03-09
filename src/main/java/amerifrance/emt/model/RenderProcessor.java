package amerifrance.emt.model;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import amerifrance.emt.ModInformation;
import amerifrance.emt.modules.ic2.tile.TileProcessor;

public class RenderProcessor extends TileEntitySpecialRenderer implements IItemRenderer {

    public ModelProcessor bigModel = new ModelProcessor();
    public ModelCube smallModel = new ModelCube();
    public ResourceLocation bigTexture = new ResourceLocation(ModInformation.TEXLOC + "textures/blocks/ProcessorFormed.png");
    public ResourceLocation smallTexture = new ResourceLocation(ModInformation.TEXLOC + "textures/blocks/Processor.png");

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float scale) {
        TileProcessor processor = (TileProcessor) tileEntity;
        if (processor.hasMaster() && processor.isMaster()) renderBigModel(processor, x, y, z);
        else if (!processor.hasMaster()) renderSmallModel(processor, x, y, z);
    }

    public void renderBigModel(TileProcessor tile, double x, double y, double z) {
        float scale = 0.1F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5F, (float) z + 0.5f);
        GL11.glScalef(scale, scale, scale);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(bigTexture);
        bigModel.renderAll();
        GL11.glPopMatrix();
    }

    public void renderSmallModel(TileProcessor tile, double x, double y, double z) {
        float scale = 0.1F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5f, (float) y - 0.5F, (float) z + 0.5f);
        GL11.glScalef(scale, scale, scale);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(smallTexture);
        smallModel.renderAll();
        GL11.glPopMatrix();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        switch (type) {
            case ENTITY:
                render(0.5F, 15F, -0.5F, 0.1F);
                return;
            case EQUIPPED:
                render(2F, 15F, 5F, 0.1F);
                return;
            case EQUIPPED_FIRST_PERSON:
                render(1F, 19F, 7F, 0.1F);
                return;
            case INVENTORY:
                render(-0.01F, 10F, 0.0F, 0.1F);
                return;
            default:
                return;
        }
    }

    private void render(float x, float y, float z, float size) {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(smallTexture);
        GL11.glPushMatrix(); // start
        GL11.glScalef(size, size, size);
        GL11.glTranslatef(x, y, z); // size
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        smallModel.renderAll();
        GL11.glPopMatrix(); // end
    }
}
