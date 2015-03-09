package amerifrance.emt.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelProcessor extends ModelBase {

    ModelRenderer cube;

    public ModelProcessor() {
        textureWidth = 256;
        textureHeight = 256;

        cube = new ModelRenderer(this, 0, 0);
        cube.addBox(0F, 0F, 0F, 48, 48, 48);
        cube.setRotationPoint(-24F, -24F, -24F);
        cube.setTextureSize(512, 512);
        cube.mirror = true;
        setRotation(cube, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        cube.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderAll() {
        cube.render(0.625F);
    }
}
