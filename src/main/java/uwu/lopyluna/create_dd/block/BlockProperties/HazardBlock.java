package uwu.lopyluna.create_dd.block.BlockProperties;

import com.simibubi.create.content.decoration.encasing.CasingBlock;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.NotNull;

public class HazardBlock extends HorizontalDirectionalBlock implements IWrenchable {

    private boolean visible;

    public static HazardBlock deprecated(BlockBehaviour.Properties p_i48440_1_) {
        return new HazardBlock(p_i48440_1_, false);
    }

    public HazardBlock(BlockBehaviour.Properties p_i48440_1_) {
        this(p_i48440_1_, true);
    }

    public HazardBlock(BlockBehaviour.Properties p_i48440_1_, boolean visible) {
        super(p_i48440_1_);
        this.visible = visible;
    }

    @Override
    public void fillItemCategory(CreativeModeTab pCategory, NonNullList<ItemStack> pItems) {
        if (visible)
            super.fillItemCategory(pCategory, pItems);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        return InteractionResult.FAIL;
    }

    @NotNull
    public  PushReaction getPistonPushReaction(BlockState pState) {
        return PushReaction.PUSH_ONLY;
    }
}
