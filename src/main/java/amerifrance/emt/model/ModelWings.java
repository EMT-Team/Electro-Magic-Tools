package amerifrance.emt.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelWings extends ModelBiped {

    ModelRenderer rightWing;
    ModelRenderer leftWing;
    ModelRenderer center;

    public ModelWings() {
        textureWidth = 64;
        textureHeight = 64;

        rightWing = new ModelRenderer(this, 24, 0);
        rightWing.addBox(-11F, 0F, 0F, 11, 13, 1);
        rightWing.setRotationPoint(-2F, 0F, 3F);
        rightWing.setTextureSize(64, 32);
        rightWing.mirror = true;
        setRotation(rightWing, 0F, 0.1570796F, 0F);

        leftWing = new ModelRenderer(this, 0, 0);
        leftWing.addBox(0F, 0F, 0F, 11, 13, 1);
        leftWing.setRotationPoint(2F, 0F, 3F);
        leftWing.setTextureSize(64, 32);
        leftWing.mirror = true;
        setRotation(leftWing, 0F, -0.1570796F, 0F);

        center = new ModelRenderer(this, 0, 14);
        center.addBox(0F, 0F, 0F, 8, 14, 1);
        center.setRotationPoint(-4F, 0F, 3F);
        center.setTextureSize(64, 32);
        center.mirror = true;
        setRotation(center, 0F, 0F, 0F);

        this.bipedBody.addChild(rightWing);
        this.bipedBody.addChild(leftWing);
        this.bipedBody.addChild(center);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        bipedHead.showModel = false;
        bipedHeadwear.showModel = false;
        bipedLeftLeg.showModel = false;
        bipedRightLeg.showModel = false;
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        rightWing.render(f5);
        leftWing.render(f5);
        center.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
