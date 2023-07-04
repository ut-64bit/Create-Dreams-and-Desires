package uwu.lopyluna.create_dd.block.temp;

import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.PushReaction;

public class YIPPEEButtonBlock extends ButtonBlock implements IWrenchable {
    public YIPPEEButtonBlock(Properties pProperties, BlockSetType pType, int pTicksToStayPressed, boolean pArrowsCanPress) {
        super(pProperties, pType, pTicksToStayPressed, pArrowsCanPress);
    }

    public static YIPPEEButtonBlock WoodenButton(BlockSetType pSetType, FeatureFlag... pRequiredFeatures) {
        BlockBehaviour.Properties blockbehaviour$properties = BlockBehaviour.Properties.of().noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY);
        if (pRequiredFeatures.length > 0) {
            blockbehaviour$properties = blockbehaviour$properties.requiredFeatures(pRequiredFeatures);
        }

        return new YIPPEEButtonBlock(blockbehaviour$properties, pSetType, 30, true);
    }
}
