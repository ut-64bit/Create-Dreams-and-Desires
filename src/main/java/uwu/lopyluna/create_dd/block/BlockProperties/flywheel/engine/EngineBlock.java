package uwu.lopyluna.create_dd.block.BlockProperties.flywheel.engine;

import javax.annotation.Nullable;

import com.jozufozu.flywheel.core.PartialModel;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.utility.Iterate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED;

public abstract class EngineBlock extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock, IWrenchable {

    protected EngineBlock(Properties builder) {
        super(builder);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return isValidPosition(state, worldIn, pos, state.getValue(FACING));
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.FAIL;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        FluidState ifluidstate = level.getFluidState(pos);
        Direction facing = context.getClickedFace();
        return defaultBlockState().setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER).setValue(FACING, facing.getAxis().isVertical() ? context.getHorizontalDirection().getOpposite() : facing);
    }
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(FACING, WATERLOGGED));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighbourState, LevelAccessor world,
                                  BlockPos pos, BlockPos neighbourPos) {
        if (state.getValue(WATERLOGGED))
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        return state;
    }



    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        if (worldIn.isClientSide)
            return;

        if (fromPos.equals(getBaseBlockPos(state, pos))) {
            if (!canSurvive(state, worldIn, pos)) {
                worldIn.destroyBlock(pos, true);
                return;
            }
        }
    }

    private boolean isValidPosition(BlockState state, BlockGetter world, BlockPos pos, Direction facing) {
        BlockPos baseBlockPos = getBaseBlockPos(state, pos);
        if (!isValidBaseBlock(world.getBlockState(baseBlockPos), world, pos))
            return false;
        for (Direction otherFacing : Iterate.horizontalDirections) {
            if (otherFacing == facing)
                continue;
            BlockPos otherPos = baseBlockPos.relative(otherFacing);
            BlockState otherState = world.getBlockState(otherPos);
            if (otherState.getBlock() instanceof EngineBlock
                    && getBaseBlockPos(otherState, otherPos).equals(baseBlockPos))
                return false;
        }

        return true;
    }

    public static BlockPos getBaseBlockPos(BlockState state, BlockPos pos) {
        return pos.relative(state.getValue(FACING).getOpposite());
    }

    @Nullable
    @OnlyIn(Dist.CLIENT)
    public abstract PartialModel getFrameModel();

    protected abstract boolean isValidBaseBlock(BlockState baseBlock, BlockGetter world, BlockPos pos);

}