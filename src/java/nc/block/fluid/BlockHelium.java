package nc.block.fluid;

import nc.NuclearCraft;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockHelium extends BlockFluidClassic {
	@SideOnly(Side.CLIENT)
    public static IIcon stillIcon;
    @SideOnly(Side.CLIENT)
    public static IIcon flowingIcon;
    
	public BlockHelium(Fluid f, Material m) {
		super(f, m);
		setQuantaPerBlock(16);
		setCreativeTab(NuclearCraft.tabNC);
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
            stillIcon = register.registerIcon("nc:fluid/heliumStill");
            flowingIcon = register.registerIcon("nc:fluid/heliumFlowing");
    }
    
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
    
    public boolean displaceIfPossible(World world, int x, int y, int z) {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
    	if ((entity.motionY < -0.2D) || (entity.motionY > 0.2D)) {
    		entity.motionY *= 0.2D;
    	}
    	if ((entity.motionZ < -0.2D) || (entity.motionZ > 0.2D)) {
    		entity.motionZ *= 0.2D;
    	}
    	if ((entity.motionX < -0.2D) || (entity.motionX > 0.2D)) {
    		entity.motionX *= 0.2D;
    	}
    	if (world.isRemote) {
    		return;
    	}
    	if (entity instanceof EntitySnowman) {
    		return;
    	} else {
    		boolean bool = entity.velocityChanged;
    		entity.attackEntityFrom(NuclearCraft.heliumfreeze, 4.0F);
    		entity.velocityChanged = bool;
    	}
    }
}
