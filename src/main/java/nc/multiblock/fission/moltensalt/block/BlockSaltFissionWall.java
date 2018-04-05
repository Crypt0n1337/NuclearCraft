package nc.multiblock.fission.moltensalt.block;

import nc.multiblock.fission.moltensalt.tile.TileSaltFissionWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSaltFissionWall extends BlockSaltFissionPartBase {

	public BlockSaltFissionWall() {
		super("salt_fission_wall");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileSaltFissionWall();
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (hand != EnumHand.MAIN_HAND) return false;
		if (player.isSneaking()) return false;
		return rightClickOnPart(world, pos, player);
	}
}
