package amerifrance.emt.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCube extends ModelBase {

    ModelRenderer Cube;

    public ModelCube() {
        textureWidth = 64;
        textureHeight = 64;
        Cube = new ModelRenderer(this, 0, 0);
        Cube.addBox(0F, 0F, 0F, 16, 16, 16);
        Cube.setRotationPoint(-8F, 8F, -8F);
        Cube.setTextureSize(64, 64);
        Cube.mirror = true;
        setRotation(Cube, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Cube.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderAll() {
        Cube.render(0.625F);
    }
}
