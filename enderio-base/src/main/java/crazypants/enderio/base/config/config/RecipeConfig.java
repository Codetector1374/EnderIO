package crazypants.enderio.base.config.config;

import info.loenwind.autoconfig.factory.IValue;
import info.loenwind.autoconfig.factory.IValueFactory;

public final class RecipeConfig {

  public static final IValueFactory F = BaseConfig.F.section("recipe");
  public static final IValueFactory FA = F.section(".alloy");
  public static final IValueFactory FP = F.section(".painter");

  public static final IValue<Integer> energyPerTask = FP.make("energyPerTask", 2000, //
      "The total amount of energy required to paint one block.").setRange(1, 1000000).sync();

  public static final IValue<Float> energyFactorForCobblestone = FP.make("energyFactorForCobblestone", 3f, //
      "Painting cobblestone costs this much in relation to 'energyPerTask'.").setRange(.001, 1000).sync();

  public static final IValue<Float> energyFactorForReinforcedObsidian = FP.make("energyFactorForReinforcedObsidian", 10f, //
      "Painting Reinforced Obsidian costs this much in relation to 'energyPerTask'.").setRange(.001, 1000).sync();

  public static final IValue<Float> energyFactorForWorkbench = FP.make("energyFactorForWorkbench", 50f, //
      "Painting a workbench (crafting table) costs this in relation to than 'energyPerTask'.").setRange(1, 1000).sync();

  public static final IValue<Boolean> allowTileEntitiesAsPaintSource = FP.make("allowTileEntitiesAsPaintSource", true, //
      "When enabled, blocks with tile entities (e.g. machines) can be used as paint targets.").sync();

  public static final IValue<Boolean> createSyntheticRecipes = FA.make("createSyntheticRecipes", true, //
      "Automatically create alloy smelter recipes with double and triple inputs and different slot allocations (1+1+1, 2+1, 1+2, 3 and 2) for single-input recipes.")
      .sync();

  public static final IValue<Boolean> loadCoreRecipes = F.make("loadCoreRecipes", true, //
      "When disabled the XML recipe files that come built-in with Ender IO will not be loaded. Only user supplied files (in the 'recipes/user' folder) will be loaded. "
          + "YOU MUST SUPPLY THE RECIPES ENDER IO NEEDS TO WORK YOURSELF IF YOU DO SO.")
      .sync();

}
