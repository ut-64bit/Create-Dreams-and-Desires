package uwu.lopyluna.create_flavored.item;

import com.simibubi.create.content.processing.sequenced.SequencedAssemblyItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import uwu.lopyluna.create_flavored.item.ItemProperties.ForestRavagerItem;

import static com.simibubi.create.AllTags.AllItemTags.CREATE_INGOTS;
import static com.simibubi.create.AllTags.forgeItemTag;
import static uwu.lopyluna.create_flavored.Flavoredcreate.REGISTRATE;

public class Pipebomb {

    static {
        REGISTRATE.creativeModeTab(() -> PipebombTab.BASE_CREATIVE_TAB);
    }

    //YIPPEE ITEMS PIPEBOMB YUMMY
    public static final ItemEntry<Item>
            mithril_ingot = taggedIngredient("mithril_ingot", forgeItemTag("ingots/mithril"), CREATE_INGOTS.tag),
            bronze_ingot = taggedIngredient("bronze_ingot", forgeItemTag("ingots/bronze"), forgeItemTag("ingots/strong_bronze"), CREATE_INGOTS.tag),
            steel_ingot = taggedIngredient("steel_ingot", forgeItemTag("ingots/steel"), CREATE_INGOTS.tag),
            tin_ingot = taggedIngredient("tin_ingot", forgeItemTag("ingots/tin"), CREATE_INGOTS.tag),
            industrial_iron_ingot = taggedIngredient("industrial_iron_ingot", forgeItemTag("ingots/industrial_iron"), CREATE_INGOTS.tag),
            mithril_nugget = taggedIngredient("mithril_nugget", forgeItemTag("nuggets/mithril")),
            bronze_nugget = taggedIngredient("bronze_nugget", forgeItemTag("nuggets/bronze"), forgeItemTag("ingots/strong_bronze")),
            steel_nugget = taggedIngredient("steel_nugget", forgeItemTag("nuggets/steel")),
            tin_nugget = taggedIngredient("tin_nugget", forgeItemTag("nuggets/tin")),
            industrial_iron_nugget = taggedIngredient("industrial_iron_nugget", forgeItemTag("nuggets/industrial_iron")),
            mithril_sheet = taggedIngredient("mithril_sheet", forgeItemTag("plates/mithril")),
            bronze_sheet = taggedIngredient("bronze_sheet", forgeItemTag("plates/bronze"), forgeItemTag("ingots/strong_bronze")),
            steel_sheet = taggedIngredient("steel_sheet", forgeItemTag("plates/steel")),
            industrial_iron_sheet = taggedIngredient("industrial_iron_sheet", forgeItemTag("plates/industrial_iron")),
            tin_raw = ingredient("raw_tin"),
            vanilla_orchid = ingredient("vanilla_orchid");

    public static final ItemEntry<ForestRavagerItem> forest_ravager =
            REGISTRATE.item("forest_ravager", ForestRavagerItem::new)
                    .properties(p -> p.stacksTo(1)
                            .rarity(Rarity.UNCOMMON))
                    .model(AssetLookup.itemModelWithPartials())
                    .register();


    private static ItemEntry<Item> ingredient(String name) {
        return REGISTRATE.item(name, Item::new)
                .register();
    }
    private static ItemEntry<SequencedAssemblyItem> sequencedIngredient(String name) {
        return REGISTRATE.item(name, SequencedAssemblyItem::new)
                .register();
    }

    @SafeVarargs
    private static ItemEntry<Item> taggedIngredient(String name, TagKey<Item>... tags) {
        return REGISTRATE.item(name, Item::new)
                .tag(tags)
                .register();
    }

    public static void register() {}
}