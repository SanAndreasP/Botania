package vazkii.botania.client.core.handler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockRailPowered;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockWall;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.registry.GameData;
import vazkii.botania.api.render.SpecialFlowerModel;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.api.state.enums.AltGrassVariant;
import vazkii.botania.api.state.enums.BiomeBrickVariant;
import vazkii.botania.api.state.enums.BiomeStoneVariant;
import vazkii.botania.api.state.enums.CrateVariant;
import vazkii.botania.api.state.enums.DrumVariant;
import vazkii.botania.api.state.enums.EndBrickVariant;
import vazkii.botania.api.state.enums.FutureStoneVariant;
import vazkii.botania.api.state.enums.LivingRockVariant;
import vazkii.botania.api.state.enums.LivingWoodVariant;
import vazkii.botania.api.state.enums.PylonVariant;
import vazkii.botania.api.state.enums.StorageVariant;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;
import vazkii.botania.common.block.subtile.SubTileDecor;
import vazkii.botania.common.block.subtile.SubTileManastar;
import vazkii.botania.common.block.subtile.SubTilePureDaisy;
import vazkii.botania.common.block.subtile.functional.SubTileAgricarnation;
import vazkii.botania.common.block.subtile.functional.SubTileBellethorn;
import vazkii.botania.common.block.subtile.functional.SubTileBubbell;
import vazkii.botania.common.block.subtile.functional.SubTileClayconia;
import vazkii.botania.common.block.subtile.functional.SubTileDaffomill;
import vazkii.botania.common.block.subtile.functional.SubTileDreadthorn;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import vazkii.botania.common.block.subtile.functional.SubTileFallenKanade;
import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
import vazkii.botania.common.block.subtile.functional.SubTileHopperhock;
import vazkii.botania.common.block.subtile.functional.SubTileHyacidus;
import vazkii.botania.common.block.subtile.functional.SubTileJadedAmaranthus;
import vazkii.botania.common.block.subtile.functional.SubTileJiyuulia;
import vazkii.botania.common.block.subtile.functional.SubTileLoonuim;
import vazkii.botania.common.block.subtile.functional.SubTileMarimorphosis;
import vazkii.botania.common.block.subtile.functional.SubTileMedumone;
import vazkii.botania.common.block.subtile.functional.SubTileOrechid;
import vazkii.botania.common.block.subtile.functional.SubTileOrechidIgnem;
import vazkii.botania.common.block.subtile.functional.SubTilePollidisiac;
import vazkii.botania.common.block.subtile.functional.SubTileRannuncarpus;
import vazkii.botania.common.block.subtile.functional.SubTileSolegnolia;
import vazkii.botania.common.block.subtile.functional.SubTileSpectranthemum;
import vazkii.botania.common.block.subtile.functional.SubTileTangleberrie;
import vazkii.botania.common.block.subtile.functional.SubTileTigerseye;
import vazkii.botania.common.block.subtile.functional.SubTileVinculotus;
import vazkii.botania.common.block.subtile.generating.SubTileArcaneRose;
import vazkii.botania.common.block.subtile.generating.SubTileDandelifeon;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;
import vazkii.botania.common.block.subtile.generating.SubTileEndoflame;
import vazkii.botania.common.block.subtile.generating.SubTileEntropinnyum;
import vazkii.botania.common.block.subtile.generating.SubTileGourmaryllis;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;
import vazkii.botania.common.block.subtile.generating.SubTileKekimurus;
import vazkii.botania.common.block.subtile.generating.SubTileMunchdew;
import vazkii.botania.common.block.subtile.generating.SubTileNarslimmus;
import vazkii.botania.common.block.subtile.generating.SubTileNightshade;
import vazkii.botania.common.block.subtile.generating.SubTileRafflowsia;
import vazkii.botania.common.block.subtile.generating.SubTileSpectrolus;
import vazkii.botania.common.block.subtile.generating.SubTileThermalily;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibItemNames;
import vazkii.botania.common.lib.LibMisc;

import static vazkii.botania.common.item.ModItems.*;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public final class ModelHandler {

    // Very hardocode-y right now. Will be refactored after things work.
    // In addition, many of the jsons currently use the vanilla format (simple and verified bug-free).
    // Once things settle down, we'll move to forge jsons, which will drastically cut down on the number of json files
    public static void registerModels() {
        ModelLoaderRegistry.registerLoader(SpecialFlowerModel.Loader.INSTANCE);
        OBJLoader.instance.addDomain(LibMisc.MOD_ID.toLowerCase(Locale.ROOT));

        /** Subtile block models **/
        registerSubtiles();

        /** Custom statemappers **/
        registerStateMappers();

        /** ItemBlocks **/
        registerStandardBlocks();
        registerMushrooms();
        registerFlowers();
        registerPavement();
        registerStairs();
        registerSlabs();
        registerWalls();
        registerPanes();
        registerUnstableBeaconPetal();

        /** Normal Items **/
        registerStandardItems();
        registerManaResources();
        registerDyesPetals();
        registerRunes();
        registerBows();

        registerItemModelMetas(cosmetic, LibItemNames.COSMETIC, 32);
        registerItemModelMetas(craftPattern, LibItemNames.CRAFT_PATTERN, 9);
        /** Special Item Meshers **/
    }

    private static void registerSubtiles() {
        SpecialFlowerModel.register(SubTileManastar.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_MANASTAR));
        SpecialFlowerModel.register(SubTilePureDaisy.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_PUREDAISY));

        SpecialFlowerModel.register(SubTileDaybloom.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DAYBLOOM));
        SpecialFlowerModel.register(SubTileDecor.Daybloom.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DAYBLOOM + "Decor"));
        SpecialFlowerModel.register(SubTileDaybloom.Prime.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DAYBLOOM_PRIME));
        SpecialFlowerModel.register(SubTileEndoflame.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_ENDOFLAME));
        SpecialFlowerModel.register(SubTileHydroangeas.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HYDROANGEAS));
        SpecialFlowerModel.register(SubTileDecor.Hydroangeas.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HYDROANGEAS + "Decor"));
        SpecialFlowerModel.register(SubTileThermalily.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_THERMALILY));
        SpecialFlowerModel.register(SubTileNightshade.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_NIGHTSHADE));
        SpecialFlowerModel.register(SubTileDecor.Nightshade.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_NIGHTSHADE + "Decor"));
        SpecialFlowerModel.register(SubTileNightshade.Prime.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_NIGHTSHADE_PRIME));
        SpecialFlowerModel.register(SubTileArcaneRose.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_ARCANE_ROSE));
        SpecialFlowerModel.register(SubTileMunchdew.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_MUNCHDEW));
        SpecialFlowerModel.register(SubTileEntropinnyum.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_ENTROPINNYUM));
        SpecialFlowerModel.register(SubTileKekimurus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_KEKIMURUS));
        SpecialFlowerModel.register(SubTileGourmaryllis.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_GOURMARYLLIS));
        SpecialFlowerModel.register(SubTileNarslimmus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_NARSLIMMUS));
        SpecialFlowerModel.register(SubTileSpectrolus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_SPECTROLUS));
        SpecialFlowerModel.register(SubTileDandelifeon.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DANDELIFEON));
        SpecialFlowerModel.register(SubTileRafflowsia.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_RAFFLOWSIA));

        SpecialFlowerModel.register(SubTileBellethorn.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_BELLETHORN));
        SpecialFlowerModel.register(SubTileBellethorn.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_BELLETHORN + "Chibi"));
        SpecialFlowerModel.register(SubTileDreadthorn.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DREADTHORN));
        SpecialFlowerModel.register(SubTileHeiseiDream.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HEISEI_DREAM));
        SpecialFlowerModel.register(SubTileTigerseye.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_TIGERSEYE));
        SpecialFlowerModel.register(SubTileJadedAmaranthus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_JADED_AMARANTHUS));
        SpecialFlowerModel.register(SubTileOrechid.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_ORECHID));
        SpecialFlowerModel.register(SubTileOrechidIgnem.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_ORECHID_IGNEM));
        SpecialFlowerModel.register(SubTileFallenKanade.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_FALLEN_KANADE));
        SpecialFlowerModel.register(SubTileExoflame.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_EXOFLAME));
        SpecialFlowerModel.register(SubTileAgricarnation.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_AGRICARNATION));
        SpecialFlowerModel.register(SubTileAgricarnation.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_AGRICARNATION + "Chibi"));
        SpecialFlowerModel.register(SubTileHopperhock.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HOPPERHOCK));
        SpecialFlowerModel.register(SubTileHopperhock.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HOPPERHOCK + "Chibi"));
        SpecialFlowerModel.register(SubTileTangleberrie.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_TANGLEBERRIE));
        SpecialFlowerModel.register(SubTileJiyuulia.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_JIYUULIA));
        SpecialFlowerModel.register(SubTileRannuncarpus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_RANNUNCARPUS));
        SpecialFlowerModel.register(SubTileRannuncarpus.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_RANNUNCARPUS + "Chibi"));
        SpecialFlowerModel.register(SubTileHyacidus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_HYACIDUS));
        SpecialFlowerModel.register(SubTilePollidisiac.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_POLLIDISIAC));
        SpecialFlowerModel.register(SubTileClayconia.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_CLAYCONIA));
        SpecialFlowerModel.register(SubTileLoonuim.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_LOONIUM));
        SpecialFlowerModel.register(SubTileDaffomill.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_DAFFOMILL));
        SpecialFlowerModel.register(SubTileVinculotus.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_VINCULOTUS));
        SpecialFlowerModel.register(SubTileSpectranthemum.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_SPECTRANTHEMUM));
        SpecialFlowerModel.register(SubTileMedumone.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_MEDUMONE));
        SpecialFlowerModel.register(SubTileMarimorphosis.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_MARIMORPHOSIS));
        SpecialFlowerModel.register(SubTileMarimorphosis.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_MARIMORPHOSIS + "Chibi"));
        SpecialFlowerModel.register(SubTileBubbell.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_BUBBELL));
        SpecialFlowerModel.register(SubTileBubbell.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_BUBBELL + "Chibi"));
        SpecialFlowerModel.register(SubTileSolegnolia.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_SOLEGNOLIA));
        SpecialFlowerModel.register(SubTileSolegnolia.Mini.class, new ModelResourceLocation("botania:" + LibBlockNames.SUBTILE_SOLEGNOLIA + "Chibi"));
    }

    private static void registerStandardBlocks() {
        registerItemModel(ModBlocks.manaGlass);
        registerItemModel(ModBlocks.elfGlass);
        registerItemModel(ModBlocks.runeAltar);
        registerItemModel(ModBlocks.pistonRelay);
        registerItemModel(ModBlocks.distributor);
        registerItemModel(ModBlocks.starfield);
        registerItemModel(ModBlocks.specialFlower);
        registerItemModel(ModBlocks.dirtPath);
        registerItemModel(ModBlocks.felPumpkin);
        registerItemModel(ModBlocks.ghostRail);
        registerItemModel(ModBlocks.shimmerrock);
        registerItemModel(ModBlocks.shimmerwoodPlanks);
        registerItemModel(ModBlocks.manaVoid);
        registerItemModel(ModBlocks.alchemyCatalyst);
        registerItemModel(ModBlocks.conjurationCatalyst);
        registerItemModel(ModBlocks.reedBlock);
        registerItemModel(ModBlocks.bifrost);
        registerItemModel(ModBlocks.bifrostPerm);
        registerItemModel(ModBlocks.enderEye);
        registerItemModel(ModBlocks.tinyPlanet);
        registerItemModel(ModBlocks.thatch);
        registerItemModel(ModBlocks.blazeBlock);
        registerItemModel(ModBlocks.forestEye);

        // Blocks which share models with their item, and have only one variant to switch over
        registerVariantsDefaulted(ModBlocks.altGrass, AltGrassVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.storage, StorageVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.pylon, PylonVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.forestDrum, DrumVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.livingrock, LivingRockVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.livingwood, LivingWoodVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.dreamwood, LivingWoodVariant.class, "variant");
        registerVariantsDefaulted(ModFluffBlocks.stone, FutureStoneVariant.class, "variant");
        registerVariantsDefaulted(ModFluffBlocks.biomeStoneA, BiomeStoneVariant.class, "variant");
        registerVariantsDefaulted(ModFluffBlocks.biomeStoneB, BiomeBrickVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.endStoneBrick, EndBrickVariant.class, "variant");
        registerVariantsDefaulted(ModBlocks.openCrate, CrateVariant.class, "variant");
    }

    private static void registerStandardItems() {
        registerItemModel(pestleAndMortar);
        registerItemModel(blackLotus);
        registerItemModel(blackLotus, 1);
        registerItemModel(lexicon);
        registerItemModel(manaCookie);

        registerItemModel(manasteelHelm);
        registerItemModel(manasteelChest);
        registerItemModel(manasteelLegs);
        registerItemModel(manasteelBoots);

        registerItemModel(manasteelPick);
        registerItemModel(manasteelShovel);
        registerItemModel(manasteelAxe);
        registerItemModel(manasteelShears);
        registerItemModel(manasteelSword);

        registerItemModel(elementiumHelm);
        registerItemModel(elementiumChest);
        registerItemModel(elementiumLegs);
        registerItemModel(elementiumBoots);

        registerItemModel(elementiumPick);
        registerItemModel(elementiumShovel);
        registerItemModel(elementiumAxe);
        registerItemModel(elementiumShears);
        registerItemModel(elementiumSword);

        registerItemModel(manaweaveHelm);
        registerItemModel(manaweaveChest);
        registerItemModel(manaweaveLegs);
        registerItemModel(manaweaveBoots);

        registerItemModel(terrasteelHelm);
        registerItemModel(terrasteelHelmRevealing);
        registerItemModel(terrasteelChest);
        registerItemModel(terrasteelLegs);
        registerItemModel(terrasteelBoots);

        registerItemModel(starSword);
        registerItemModel(thunderSword);
        registerItemModel(glassPick);
        registerItemModel(twigWand);

        registerItemModel(flowerBag);
        registerItemModel(fertilizer);
        registerItemModel(obedienceStick);
        
        registerItemModel(dirtRod);
        registerItemModel(cobbleRod);
        registerItemModel(fireRod);
        registerItemModel(rainbowRod);
        registerItemModel(skyDirtRod);
        registerItemModel(tornadoRod);
        registerItemModel(terraformRod);

        registerItemModel(tornadoRod);
        ModelLoader.registerItemVariants(tornadoRod, new ModelResourceLocation("botania:tornadoRod_flying", "inventory"));
    }

    private static void registerManaResources() {
        Item item = manaResource;
        for (int i = 0; i < LibItemNames.MANA_RESOURCE_NAMES.length; i++) {
            String name = "botania:" + LibItemNames.MANA_RESOURCE_NAMES[i];
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory"));
            ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(name, "inventory"));
        }
    }

    private static void registerDyesPetals() {
        Item item = dye;
        String name = GameData.getItemRegistry().getNameForObject(item).toString();

        Item item2 = petal;
        String name2 = GameData.getItemRegistry().getNameForObject(item2).toString();

        for (EnumDyeColor color : EnumDyeColor.values()) {
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory"));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(name, "inventory"));

            ModelLoader.registerItemVariants(item2, new ModelResourceLocation(name2, "inventory"));
            ModelLoader.setCustomModelResourceLocation(item2, color.getMetadata(), new ModelResourceLocation(name2, "inventory"));
        }
    }

    private static void registerRunes() {
        List<String> variantNames = ImmutableList.of("water", "fire", "earth", "air", "spring", "summer", "autumn", "winter", "mana", "lust", "gluttony", "greed", "sloth", "wrath", "envy", "pride");
        for (int i = 0; i < variantNames.size(); i++) {
            ModelLoader.registerItemVariants(rune, new ModelResourceLocation("botania:rune_" + variantNames.get(i), "inventory"));
            ModelLoader.setCustomModelResourceLocation(rune, i, new ModelResourceLocation("botania:rune_" + variantNames.get(i), "inventory"));
        }
    }

    private static void registerBows() {
        ModelLoader.registerItemVariants(livingwoodBow, new ModelResourceLocation("botania:livingwoodBow", "inventory"));
        ModelLoader.registerItemVariants(livingwoodBow, new ModelResourceLocation("botania:livingwoodBow_pulling_1", "inventory"));
        ModelLoader.registerItemVariants(livingwoodBow, new ModelResourceLocation("botania:livingwoodBow_pulling_2", "inventory"));
        ModelLoader.registerItemVariants(livingwoodBow, new ModelResourceLocation("botania:livingwoodBow_pulling_3", "inventory"));
        registerItemModel(livingwoodBow);

        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow", "inventory"));
        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow_pulling_1", "inventory"));
        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow_pulling_2", "inventory"));
        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow_pulling_3", "inventory"));
        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow_pulling_4", "inventory"));
        ModelLoader.registerItemVariants(crystalBow, new ModelResourceLocation("botania:crystalBow_pulling_5", "inventory"));
        registerItemModel(crystalBow);
    }

    private static void registerStateMappers() {
        // Ignore vanilla facing, variant in double flower
        ModelLoader.setCustomStateMapper(ModBlocks.doubleFlower1, (new StateMap.Builder()).ignore(BlockDoublePlant.VARIANT, BlockDoublePlant.field_181084_N).build());
        ModelLoader.setCustomStateMapper(ModBlocks.doubleFlower2, (new StateMap.Builder()).ignore(BlockDoublePlant.VARIANT, BlockDoublePlant.field_181084_N).build());

        // Ignore redstone power state in some blocks
        ModelLoader.setCustomStateMapper(ModBlocks.starfield, (new StateMap.Builder()).ignore(BotaniaStateProps.POWERED).build());

        // Ignore color in unstable cube, mana beacon, and petals (handled by color multiplier)
        ModelLoader.setCustomStateMapper(ModBlocks.unstableBlock, (new StateMap.Builder()).ignore(BotaniaStateProps.COLOR).build());
        ModelLoader.setCustomStateMapper(ModBlocks.manaBeacon, (new StateMap.Builder()).ignore(BotaniaStateProps.COLOR).build());
        ModelLoader.setCustomStateMapper(ModBlocks.petalBlock, (new StateMap.Builder()).ignore(BotaniaStateProps.COLOR).build());

        // Ignore vanilla variant in flowers
        ModelLoader.setCustomStateMapper(ModBlocks.flower, (new StateMap.Builder()).ignore(((BlockFlower) ModBlocks.flower).getTypeProperty()).build());
        ModelLoader.setCustomStateMapper(ModBlocks.shinyFlower, (new StateMap.Builder()).ignore(((BlockFlower) ModBlocks.shinyFlower).getTypeProperty()).build());
        ModelLoader.setCustomStateMapper(ModBlocks.buriedPetals, (new StateMap.Builder()).ignore(((BlockFlower) ModBlocks.buriedPetals).getTypeProperty()).build());

        // Ignore in special flower to suppress errors (handled in custom statemapper in BlockSpecialFlower
        ModelLoader.setCustomStateMapper(ModBlocks.specialFlower, (new StateMap.Builder()).ignore(BotaniaStateProps.COLOR, ((BlockFlower) ModBlocks.specialFlower).getTypeProperty()).build());

        // Ignore vanilla variant in walls
        ModelLoader.setCustomStateMapper(ModFluffBlocks.biomeStoneWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.dreamwoodWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.livingrockWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.livingwoodWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.prismarineWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.reedWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());
        ModelLoader.setCustomStateMapper(ModFluffBlocks.stoneWall, (new StateMap.Builder()).ignore(BlockWall.VARIANT).build());

        // Ignore dummy variant in slabs
        for (Block b : ModFluffBlocks.biomeStoneSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY).build());
        }

        for (Block b : ModFluffBlocks.pavementSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY).build());
        }

        for (Block b : ModFluffBlocks.stoneSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY).build());
        }

        List<Block> otherSlabs = ImmutableList.copyOf(new Block[] {
                ModFluffBlocks.livingwoodSlab, ModFluffBlocks.livingwoodPlankSlab, ModFluffBlocks.livingrockSlab, ModFluffBlocks.dreamwoodSlab, ModFluffBlocks.livingrockBrickSlab,
                ModFluffBlocks.dreamwoodPlankSlab, ModFluffBlocks.prismarineSlab, ModFluffBlocks.prismarineBrickSlab, ModFluffBlocks.darkPrismarineSlab,
                ModFluffBlocks.reedSlab, ModFluffBlocks.thatchSlab, ModFluffBlocks.netherBrickSlab, ModFluffBlocks.soulBrickSlab, ModFluffBlocks.snowBrickSlab,
                ModFluffBlocks.tileSlab, ModFluffBlocks.darkQuartzSlab, ModFluffBlocks.manaQuartzSlab, ModFluffBlocks.blazeQuartzSlab,
                ModFluffBlocks.lavenderQuartzSlab, ModFluffBlocks.redQuartzSlab, ModFluffBlocks.elfQuartzSlab, ModFluffBlocks.sunnyQuartzSlab, ModFluffBlocks.dirtPathSlab,
                ModFluffBlocks.shimmerrockSlab, ModFluffBlocks.shimmerwoodPlankSlab, ModFluffBlocks.endStoneSlab, ModFluffBlocks.enderBrickSlab
        });

        for (Block b : otherSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY).build());
        }

        // Ignore both dummy variant and half prop in full slabs
        for (Block b : ModFluffBlocks.biomeStoneFullSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY, BlockSlab.HALF).build());
        }

        for (Block b : ModFluffBlocks.pavementFullSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY, BlockSlab.HALF).build());
        }

        for (Block b : ModFluffBlocks.stoneFullSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY, BlockSlab.HALF).build());
        }
        
        List<Block> otherFullSlabs = ImmutableList.copyOf(new Block[] {
                ModFluffBlocks.livingwoodSlabFull, ModFluffBlocks.livingwoodPlankSlabFull, ModFluffBlocks.livingrockSlabFull, ModFluffBlocks.dreamwoodSlabFull, ModFluffBlocks.livingrockBrickSlabFull,
                ModFluffBlocks.dreamwoodPlankSlabFull, ModFluffBlocks.prismarineSlabFull, ModFluffBlocks.prismarineBrickSlabFull, ModFluffBlocks.darkPrismarineSlabFull,
                ModFluffBlocks.reedSlabFull, ModFluffBlocks.thatchSlabFull, ModFluffBlocks.netherBrickSlabFull, ModFluffBlocks.soulBrickSlabFull, ModFluffBlocks.snowBrickSlabFull,
                ModFluffBlocks.tileSlabFull, ModFluffBlocks.darkQuartzSlabFull, ModFluffBlocks.manaQuartzSlabFull, ModFluffBlocks.blazeQuartzSlabFull,
                ModFluffBlocks.lavenderQuartzSlabFull, ModFluffBlocks.redQuartzSlabFull, ModFluffBlocks.elfQuartzSlabFull, ModFluffBlocks.sunnyQuartzSlabFull, ModFluffBlocks.dirtPathSlabFull,
                ModFluffBlocks.shimmerrockSlabFull, ModFluffBlocks.shimmerwoodPlankSlabFull, ModFluffBlocks.endStoneSlabFull, ModFluffBlocks.enderBrickSlabFull
        });

        for (Block b : otherFullSlabs) {
            ModelLoader.setCustomStateMapper(b, (new StateMap.Builder()).ignore(BlockModSlab.DUMMY, BlockSlab.HALF).build());
        }
    }

    private static void registerMushrooms() {
        Item item = Item.getItemFromBlock(ModBlocks.mushroom);
        for (EnumDyeColor color : EnumDyeColor.values()) {
            String name = GameData.getBlockRegistry().getNameForObject(ModBlocks.mushroom).toString();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory_" + color.getName()));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(name, "inventory_" + color.getName()));
        }
    }

    private static void registerFlowers() {
        Item item = Item.getItemFromBlock(ModBlocks.flower);
        for (EnumDyeColor color : EnumDyeColor.values()) {
            String name = GameData.getBlockRegistry().getNameForObject(ModBlocks.flower).toString();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory_" + color.getName()));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(name, "inventory_" + color.getName()));
        }

        item = Item.getItemFromBlock(ModBlocks.shinyFlower);
        for (EnumDyeColor color : EnumDyeColor.values()) {
            String name = GameData.getBlockRegistry().getNameForObject(ModBlocks.shinyFlower).toString();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory_" + color.getName()));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(name, "inventory_" + color.getName()));
        }

        item = Item.getItemFromBlock(ModBlocks.doubleFlower1);
        for (EnumDyeColor color : BotaniaStateProps.DOUBLEFLOWER_VARIANT_1.getAllowedValues()) {
            String name = GameData.getBlockRegistry().getNameForObject(ModBlocks.doubleFlower1).toString();
            String variant = "inventory_" + color.getName();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata(), new ModelResourceLocation(name, variant));
        }

        item = Item.getItemFromBlock(ModBlocks.doubleFlower2);
        for (EnumDyeColor color : BotaniaStateProps.DOUBLEFLOWER_VARIANT_2.getAllowedValues()) {
            String name = GameData.getBlockRegistry().getNameForObject(ModBlocks.doubleFlower2).toString();
            String variant = "inventory_" + color.getName();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
            ModelLoader.setCustomModelResourceLocation(item, color.getMetadata() - 8, new ModelResourceLocation(name, variant));
        }
    }

    private static void registerPavement() {
        Item item = Item.getItemFromBlock(ModFluffBlocks.pavement);
        String name = GameData.getBlockRegistry().getNameForObject(ModFluffBlocks.pavement).toString();

        for (EnumDyeColor e : BotaniaStateProps.PAVEMENT_COLOR.getAllowedValues()) {
            String variant = "color=" + e.getName();
            switch (e) {
                case BLACK: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(name, variant));
                    break;
                }
                case BLUE: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 2, new ModelResourceLocation(name, variant));
                    break;
                }
                case RED: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation(name, variant));
                    break;
                }
                case YELLOW: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 4, new ModelResourceLocation(name, variant));
                    break;
                }
                case GREEN: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 5, new ModelResourceLocation(name, variant));
                    break;
                }
                case WHITE:
                default: {
                    ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, variant));
                    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(name, variant));
                    break;
                }
            }
        }
    }

    private static void registerStairs() {
        for (Block b : ModFluffBlocks.stoneStairs) {
            registerItemModel(b);
        }

        for (Block b : ModFluffBlocks.pavementStairs) {
            registerItemModel(b);
        }

        for (Block b : ModFluffBlocks.biomeStoneStairs) {
            registerItemModel(b);
        }

        registerItemModel(ModFluffBlocks.blazeQuartzStairs);
        registerItemModel(ModFluffBlocks.darkPrismarineStairs);
        registerItemModel(ModFluffBlocks.darkQuartzStairs);
        registerItemModel(ModFluffBlocks.dreamwoodStairs);
        registerItemModel(ModFluffBlocks.dreamwoodPlankStairs);
        registerItemModel(ModFluffBlocks.elfQuartzStairs);
        registerItemModel(ModFluffBlocks.enderBrickStairs);
        registerItemModel(ModFluffBlocks.endStoneStairs);
        registerItemModel(ModFluffBlocks.lavenderQuartzStairs);
        registerItemModel(ModFluffBlocks.livingrockStairs);
        registerItemModel(ModFluffBlocks.livingrockBrickStairs);
        registerItemModel(ModFluffBlocks.livingwoodStairs);
        registerItemModel(ModFluffBlocks.livingwoodPlankStairs);
        registerItemModel(ModFluffBlocks.manaQuartzStairs);
        registerItemModel(ModFluffBlocks.netherBrickStairs);
        registerItemModel(ModFluffBlocks.prismarineStairs);
        registerItemModel(ModFluffBlocks.prismarineBrickStairs);
        registerItemModel(ModFluffBlocks.redQuartzStairs);
        registerItemModel(ModFluffBlocks.reedStairs);
        registerItemModel(ModFluffBlocks.shimmerrockStairs);
        registerItemModel(ModFluffBlocks.shimmerwoodPlankStairs);
        registerItemModel(ModFluffBlocks.snowBrickStairs);
        registerItemModel(ModFluffBlocks.soulBrickStairs);
        registerItemModel(ModFluffBlocks.sunnyQuartzStairs);
        registerItemModel(ModFluffBlocks.thatchStairs);
        registerItemModel(ModFluffBlocks.tileStairs);
    }

    private static void registerSlabs() {
        for (Block b : ModFluffBlocks.biomeStoneSlabs) {
            registerItemModel(b);
        }

        for (Block b : ModFluffBlocks.pavementSlabs) {
            registerItemModel(b);
        }

        for (Block b : ModFluffBlocks.stoneSlabs) {
            registerItemModel(b);
        }

        registerItemModel(ModFluffBlocks.livingwoodSlab);
        registerItemModel(ModFluffBlocks.livingwoodPlankSlab);
        registerItemModel(ModFluffBlocks.livingrockSlab);
        registerItemModel(ModFluffBlocks.livingrockBrickSlab);

        registerItemModel(ModFluffBlocks.blazeQuartzSlab);
        registerItemModel(ModFluffBlocks.darkQuartzSlab);
        registerItemModel(ModFluffBlocks.elfQuartzSlab);
        registerItemModel(ModFluffBlocks.lavenderQuartzSlab);
        registerItemModel(ModFluffBlocks.manaQuartzSlab);
        registerItemModel(ModFluffBlocks.redQuartzSlab);
        registerItemModel(ModFluffBlocks.sunnyQuartzSlab);

        registerItemModel(ModFluffBlocks.dreamwoodSlab);
        registerItemModel(ModFluffBlocks.dreamwoodPlankSlab);
        registerItemModel(ModFluffBlocks.prismarineSlab);
        registerItemModel(ModFluffBlocks.prismarineBrickSlab);
        registerItemModel(ModFluffBlocks.darkPrismarineSlab);
        registerItemModel(ModFluffBlocks.dirtPathSlab);
        registerItemModel(ModFluffBlocks.shimmerrockSlab);
        registerItemModel(ModFluffBlocks.shimmerwoodPlankSlab);
        registerItemModel(ModFluffBlocks.endStoneSlab);
        registerItemModel(ModFluffBlocks.enderBrickSlab);
        registerItemModel(ModFluffBlocks.netherBrickSlab);
        registerItemModel(ModFluffBlocks.soulBrickSlab);
        registerItemModel(ModFluffBlocks.snowBrickSlab);
        registerItemModel(ModFluffBlocks.tileSlab);
        registerItemModel(ModFluffBlocks.reedSlab);
        registerItemModel(ModFluffBlocks.thatchSlab);

    }

    private static void registerWalls() {
        Item item = Item.getItemFromBlock(ModFluffBlocks.biomeStoneWall);
        for (BiomeStoneVariant variant : BotaniaStateProps.BIOMESTONEWALL_VARIANT.getAllowedValues()) {
            String variantName = "inventory_" + variant.getName();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation("botania:biomeStoneA0Wall", variantName));
            ModelLoader.setCustomModelResourceLocation(item, variant.ordinal() - 8, new ModelResourceLocation("botania:biomeStoneA0Wall", variantName));
        }

        item = Item.getItemFromBlock(ModFluffBlocks.stoneWall);
        for (FutureStoneVariant variant : BotaniaStateProps.FUTURESTONEWALL_VARIANT.getAllowedValues()) {
            String variantName = "inventory_" + variant.getName();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation("botania:stone0Wall", variantName));
            ModelLoader.setCustomModelResourceLocation(item, variant.ordinal(), new ModelResourceLocation("botania:stone0Wall", variantName));
        }

        registerItemModel(ModFluffBlocks.livingrockWall);
        registerItemModel(ModFluffBlocks.livingwoodWall);
        registerItemModel(ModFluffBlocks.dreamwoodWall);
        registerItemModel(ModFluffBlocks.prismarineWall);
        registerItemModel(ModFluffBlocks.reedWall);
    }

    private static void registerPanes() {
        registerItemModel(ModFluffBlocks.alfglassPane);
        registerItemModel(ModFluffBlocks.bifrostPane);
        registerItemModel(ModFluffBlocks.managlassPane);
    }

    private static void registerUnstableBeaconPetal() {
        // The blocks that are dynamically colored, not through their states
        for (EnumDyeColor color : EnumDyeColor.values()) {
            Item unstable = Item.getItemFromBlock(ModBlocks.unstableBlock);
            Item beacon = Item.getItemFromBlock(ModBlocks.manaBeacon);
            Item petal = Item.getItemFromBlock(ModBlocks.petalBlock);
            ModelLoader.setCustomModelResourceLocation(unstable, color.getMetadata(), new ModelResourceLocation("botania:unstableBlock", "inventory"));
            ModelLoader.setCustomModelResourceLocation(beacon, color.getMetadata(), new ModelResourceLocation("botania:manaBeacon", "inventory"));
            ModelLoader.setCustomModelResourceLocation(petal, color.getMetadata(), new ModelResourceLocation("botania:petalBlock", "inventory"));
        }
    }

    private static <T extends Enum<T> & IStringSerializable> void registerVariantsDefaulted(Block b, Class<T> enumclazz, String variantHeader) {
        Item item = Item.getItemFromBlock(b);
        for (T e : enumclazz.getEnumConstants()) {
            String baseName = GameData.getBlockRegistry().getNameForObject(b).toString();
            String variantName = variantHeader + "=" + e.getName();
            ModelLoader.registerItemVariants(item, new ModelResourceLocation(baseName, variantName));
            ModelLoader.setCustomModelResourceLocation(item, e.ordinal(), new ModelResourceLocation(baseName, variantName));
        }
    }

    private static void registerItemModelMetas(Item item, String loc, int range) {
     	for(int i = 0; i < range;i++) {
     		String name = "botania:" + loc + i;
     		ModelLoader.registerItemVariants(item, new ModelResourceLocation(name, "inventory"));
     		ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(name, "inventory"));
     	}
    }

    private static void registerItemModel(Block b) {
        registerItemModel(Item.getItemFromBlock(b));
    }

    private static void registerItemModel(Item i,int meta) {
        ResourceLocation loc = GameData.getItemRegistry().getNameForObject(i);
        ModelLoader.setCustomModelResourceLocation(i, meta, new ModelResourceLocation(loc, "inventory"));
    }

    private static void registerItemModel(Item i) {
        registerItemModel(i, 0);
    }

    private ModelHandler() {}
}
