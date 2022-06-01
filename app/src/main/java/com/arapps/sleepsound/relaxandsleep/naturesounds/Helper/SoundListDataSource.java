package com.arapps.sleepsound.relaxandsleep.naturesounds.Helper;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.imageutils.JfifUtil;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixCategoryModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixCoverModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SettingsModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SoundListDataSource {
    private static final SparseArray<MixCoverModel> mixCoverItemSparseArray = new SparseArray();
    private static SparseArray<MixModel> mixItemSparseArray = new SparseArray();
    public static int numerTab = 7;
    private static SparseArray<SoundModel> soundItemSparseArray = new SparseArray();
    private static List<SoundModel> soundModels = new ArrayList();

    public static void createData(Context context) {
        soundItemSparseArray = new SparseArray();
        soundModels = new ArrayList();
        mixItemSparseArray = new SparseArray();
        createSoundData(context);
        createMixCoverItemData(context);
        createMixItemData(context);
    }

    private static void createMixItemData(Context context) {
        MixModel mixModel = new MixModel(201, 3, context.getResources().getString(R.string.mix_sound_name_spring_rain), createMixCoverItem(201), createListSoundItem(new int[]{102, 104}, new int[]{50, 50}));
        MixModel mixModel2 = new MixModel(202, 4, context.getResources().getString(R.string.mix_sound_name_peaceful_night), createMixCoverItem(202), createListSoundItem(new int[]{111}, new int[]{50}));
        MixModel mixModel3 = new MixModel(203, 5, context.getResources().getString(R.string.mix_sound_name_beach), createMixCoverItem(203), createListSoundItem(new int[]{112}, new int[]{50}));
        MixModel mixModel4 = mixModel2;
        mixModel2 = new MixModel(JfifUtil.MARKER_SOI, 3, context.getResources().getString(R.string.sound_name_light_rain), createMixCoverItem(JfifUtil.MARKER_SOI), createListSoundItem(new int[]{109}, new int[]{50}));
        MixModel mixModel5 = new MixModel(204, 5, context.getResources().getString(R.string.mix_sound_name_forest_adventure), createMixCoverItem(204), createListSoundItem(new int[]{116, 115}, new int[]{50, 50}));
        mixModel3 = new MixModel(JfifUtil.MARKER_RST7, 2, context.getResources().getString(R.string.mix_sound_name_fire), createMixCoverItem(JfifUtil.MARKER_RST7), createListSoundItem(new int[]{118}, new int[]{50}));
        mixModel5 = new MixModel(205, 2, context.getResources().getString(R.string.mix_sound_name_train_journey), createMixCoverItem(205), createListSoundItem(new int[]{119, 107}, new int[]{50, 5}));
        MixModel mixModel6 = new MixModel(210, 5, context.getResources().getString(R.string.mix_sound_name_babbling_brook), createMixCoverItem(210), createListSoundItem(new int[]{115, 125}, new int[]{50, 50}));
        mixModel5 = new MixModel(209, 3, context.getResources().getString(R.string.mix_sound_name_rain_in_forest), createMixCoverItem(209), createListSoundItem(new int[]{101, 104}, new int[]{50, 50}));
        mixModel6 = new MixModel(207, 2, context.getResources().getString(R.string.mix_sound_name_countryside), createMixCoverItem(207), createListSoundItem(new int[]{111, 118}, new int[]{50, 50}));
        MixModel mixModel7 = new MixModel(JfifUtil.MARKER_RST0, 3, context.getResources().getString(R.string.mix_sound_name_rain_on_roof), createMixCoverItem(JfifUtil.MARKER_RST0), createListSoundItem(new int[]{107, 118}, new int[]{50, 50}));
        MixModel mixModel8 = new MixModel(206, 2, context.getResources().getString(R.string.mix_sound_name_cold_winter), createMixCoverItem(206), createListSoundItem(new int[]{108, 113, 118}, new int[]{50, 50, 50}));
        MixModel mixModel9 = new MixModel(213, 2, context.getResources().getString(R.string.mix_sound_name_city_life), createMixCoverItem(213), createListSoundItem(new int[]{121, 106}, new int[]{50, 10}));
        MixModel mixModel10 = new MixModel(211, 5, context.getResources().getString(R.string.mix_sound_name_autumn_jungle), createMixCoverItem(211), createListSoundItem(new int[]{105, 125}, new int[]{50, 50}));
        MixModel mixModel11 = mixModel10;
        MixModel mixModel12 = new MixModel(212, 2, context.getResources().getString(R.string.mix_sound_name_flying_sky), createMixCoverItem(212), createListSoundItem(new int[]{120}, new int[]{50}));
        mixModel10 = new MixModel(214, 2, context.getResources().getString(R.string.mix_sound_name_cave), createMixCoverItem(214), createListSoundItem(new int[]{117, 118}, new int[]{50, 50}));
        mixItemSparseArray.put(201, mixModel12);
        mixItemSparseArray.put(202, mixModel2);
        mixItemSparseArray.put(203, mixModel3);
        mixItemSparseArray.put(JfifUtil.MARKER_SOI, mixModel4);
        mixItemSparseArray.put(204, mixModel5);
        mixItemSparseArray.put(JfifUtil.MARKER_RST7, mixModel3);
        mixItemSparseArray.put(205, mixModel5);
        mixItemSparseArray.put(210, mixModel6);
        mixItemSparseArray.put(209, mixModel5);
        mixItemSparseArray.put(207, mixModel6);
        mixItemSparseArray.put(JfifUtil.MARKER_RST0, mixModel7);
        mixItemSparseArray.put(206, mixModel8);
        mixItemSparseArray.put(213, mixModel9);
        mixItemSparseArray.put(211, mixModel11);
        mixItemSparseArray.put(212, mixModel12);
        mixItemSparseArray.put(214, mixModel10);
        MixModel mixModel13 = new MixModel(220, 4, context.getResources().getString(R.string.mix_sound_name_nature_melody), createMixCoverItem(220), createListSoundItem(new int[]{141, 116}, new int[]{50, 50}));
        MixModel mixModel14 = new MixModel(222, 4, context.getResources().getString(R.string.mix_sound_name_relax_time), createMixCoverItem(222), createListSoundItem(new int[]{138, 109}, new int[]{50, 50}));
        MixModel mixModel15 = new MixModel(228, 5, context.getResources().getString(R.string.mix_sound_name_meditate_in_forest), createMixCoverItem(228), createListSoundItem(new int[]{136, 115}, new int[]{50, 50}));
        mixModel2 = new MixModel(JfifUtil.MARKER_EOI, 2, context.getResources().getString(R.string.mix_sound_name_evening_beach), createMixCoverItem(JfifUtil.MARKER_EOI), createListSoundItem(new int[]{135, 148}, new int[]{50, 50}));
        MixModel mixModel16 = new MixModel(223, 4, context.getResources().getString(R.string.mix_sound_name_yoga_music), createMixCoverItem(223), createListSoundItem(new int[]{147}, new int[]{50}));
        mixModel2 = new MixModel(235, 3, context.getResources().getString(R.string.sound_name_rain_on_leaves), createMixCoverItem(235), createListSoundItem(new int[]{101}, new int[]{50}));
        mixModel16 = new MixModel(224, 4, context.getResources().getString(R.string.mix_sound_name_memories), createMixCoverItem(224), createListSoundItem(new int[]{143}, new int[]{50, 50}));
        mixModel2 = new MixModel(JfifUtil.MARKER_APP1, 4, context.getResources().getString(R.string.mix_sound_name_deep_relaxation), createMixCoverItem(JfifUtil.MARKER_APP1), createListSoundItem(new int[]{144}, new int[]{50}));
        mixModel16 = new MixModel(219, 2, context.getResources().getString(R.string.mix_sound_name_lullaby_music_box), createMixCoverItem(219), createListSoundItem(new int[]{142}, new int[]{50}));
        mixModel2 = new MixModel(227, 5, context.getResources().getString(R.string.mix_sound_name_zen), createMixCoverItem(227), createListSoundItem(new int[]{148}, new int[]{50}));
        mixModel3 = new MixModel(226, 5, context.getResources().getString(R.string.mix_sound_name_meditation), createMixCoverItem(226), createListSoundItem(new int[]{140}, new int[]{50}));
        MixModel mixModel17 = new MixModel(229, 6, context.getResources().getString(R.string.mix_sound_name_universe), createMixCoverItem(229), createListSoundItem(new int[]{146}, new int[]{50}));
        mixModel3 = new MixModel(231, 6, context.getResources().getString(R.string.mix_sound_name_deep_thinking), createMixCoverItem(231), createListSoundItem(new int[]{133}, new int[]{50}));
        mixModel3 = mixModel3;
        mixModel17 = new MixModel(234, 6, context.getResources().getString(R.string.mix_sound_name_library), createMixCoverItem(234), createListSoundItem(new int[]{130}, new int[]{50}));
        mixModel4 = mixModel17;
        MixModel mixModel18 = new MixModel(232, 6, context.getResources().getString(R.string.mix_sound_name_focus), createMixCoverItem(232), createListSoundItem(new int[]{137}, new int[]{50}));
        mixModel9 = new MixModel(233, 6, context.getResources().getString(R.string.mix_sound_name_rainy_weekend), createMixCoverItem(233), createListSoundItem(new int[]{139, 109}, new int[]{50, 16}));
        MixModel mixModel19 = new MixModel(230, 6, context.getResources().getString(R.string.mix_sound_name_cafe), createMixCoverItem(230), createListSoundItem(new int[]{131, 132}, new int[]{24, 86}));
        mixModel9 = new MixModel(JfifUtil.MARKER_SOS, 2, context.getResources().getString(R.string.mix_sound_name_dryer), createMixCoverItem(JfifUtil.MARKER_SOS), createListSoundItem(new int[]{134}, new int[]{50}));
        mixItemSparseArray.put(mixModel13.getMixSoundId(), mixModel13);
        mixItemSparseArray.put(mixModel14.getMixSoundId(), mixModel14);
        mixItemSparseArray.put(mixModel15.getMixSoundId(), mixModel15);
        mixItemSparseArray.put(mixModel2.getMixSoundId(), mixModel2);
        mixItemSparseArray.put(mixModel16.getMixSoundId(), mixModel16);
        mixItemSparseArray.put(mixModel2.getMixSoundId(), mixModel2);
        mixItemSparseArray.put(mixModel16.getMixSoundId(), mixModel16);
        mixItemSparseArray.put(mixModel2.getMixSoundId(), mixModel2);
        mixItemSparseArray.put(mixModel16.getMixSoundId(), mixModel16);
        mixItemSparseArray.put(mixModel2.getMixSoundId(), mixModel2);
        mixItemSparseArray.put(mixModel3.getMixSoundId(), mixModel3);
        mixItemSparseArray.put(mixModel17.getMixSoundId(), mixModel17);
        mixItemSparseArray.put(mixModel3.getMixSoundId(), mixModel3);
        mixItemSparseArray.put(mixModel4.getMixSoundId(), mixModel4);
        mixItemSparseArray.put(mixModel18.getMixSoundId(), mixModel18);
        mixItemSparseArray.put(mixModel9.getMixSoundId(), mixModel9);
        mixItemSparseArray.put(mixModel19.getMixSoundId(), mixModel19);
        mixItemSparseArray.put(mixModel9.getMixSoundId(), mixModel9);
        List<MixModel> customMixList = SaveDataHelper.getCustomMixList(context);
        if (customMixList != null && customMixList.size() > 0) {
            for (MixModel mixModel20 : customMixList) {
                mixItemSparseArray.put(mixModel20.getMixSoundId(), mixModel20);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(mixModel20.getMixSoundId());
                stringBuilder.append("");
                Log.e("CUSTOM_MIX", stringBuilder.toString());
            }
        }
    }

    private static List<SoundModel> createListSoundItem(int[] iArr, int[] iArr2) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            SoundModel soundModel = new SoundModel(soundItemSparseArray.get(iArr[i]));
            soundModel.setVolume(iArr2[i]);
            arrayList.add(soundModel);
        }
        return arrayList;
    }

    private static MixCoverModel createMixCoverItem(int i) {
        return mixCoverItemSparseArray.get(i);
    }

    private static void createMixCoverItemData(Context context) {
        mixCoverItemSparseArray.put(0, new MixCoverModel(0, R.drawable.custom_cover_1, R.drawable.big_custom_cover_1));
        mixCoverItemSparseArray.put(1, new MixCoverModel(1, R.drawable.custom_cover_2, R.drawable.big_custom_cover_2));
        mixCoverItemSparseArray.put(2, new MixCoverModel(2, R.drawable.custom_cover_3, R.drawable.big_custom_cover_3));
        mixCoverItemSparseArray.put(3, new MixCoverModel(3, R.drawable.custom_cover_4, R.drawable.big_custom_cover_4));
        mixCoverItemSparseArray.put(4, new MixCoverModel(4, R.drawable.custom_cover_5, R.drawable.big_custom_cover_5));
        mixCoverItemSparseArray.put(5, new MixCoverModel(5, R.drawable.custom_cover_6, R.drawable.big_custom_cover_6));
        mixCoverItemSparseArray.put(6, new MixCoverModel(6, R.drawable.custom_cover_7, R.drawable.big_custom_cover_7));
        mixCoverItemSparseArray.put(7, new MixCoverModel(7, R.drawable.custom_cover_8, R.drawable.big_custom_cover_8));
        mixCoverItemSparseArray.put(201, new MixCoverModel(201, R.drawable.mix_spring_rain, R.drawable.big_mix_spring_rain));
        mixCoverItemSparseArray.put(202, new MixCoverModel(202, R.drawable.mix_peaceful_night, R.drawable.big_mix_peaceful_night));
        mixCoverItemSparseArray.put(203, new MixCoverModel(203, R.drawable.mix_beach, R.drawable.big_mix_beach));
        mixCoverItemSparseArray.put(204, new MixCoverModel(204, R.drawable.mix_forest_adventure, R.drawable.big_mix_forest_adventure));
        mixCoverItemSparseArray.put(205, new MixCoverModel(205, R.drawable.mix_train_journey, R.drawable.big_mix_train_journey));
        mixCoverItemSparseArray.put(206, new MixCoverModel(206, R.drawable.mix_cold_winter, R.drawable.big_mix_cold_winter));
        mixCoverItemSparseArray.put(207, new MixCoverModel(207, R.drawable.mix_countryside, R.drawable.big_mix_countryside));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_RST0, new MixCoverModel(JfifUtil.MARKER_RST0, R.drawable.mix_rain_on_roof, R.drawable.big_mix_rain_on_roof));
        mixCoverItemSparseArray.put(209, new MixCoverModel(209, R.drawable.mix_rain_in_forest, R.drawable.big_mix_rain_in_forest));
        mixCoverItemSparseArray.put(210, new MixCoverModel(210, R.drawable.mix_babbling_brook, R.drawable.big_mix_babbling_brook));
        mixCoverItemSparseArray.put(211, new MixCoverModel(211, R.drawable.mix_autumn_jungle, R.drawable.big_mix_autumn_jungle));
        mixCoverItemSparseArray.put(212, new MixCoverModel(212, R.drawable.mix_flying_sky, R.drawable.big_mix_flying_sky));
        mixCoverItemSparseArray.put(213, new MixCoverModel(213, R.drawable.mix_city_life, R.drawable.big_mix_city_life));
        mixCoverItemSparseArray.put(214, new MixCoverModel(214, R.drawable.mix_cave, R.drawable.big_mix_cave));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_RST7, new MixCoverModel(JfifUtil.MARKER_RST7, R.drawable.mix_fire, R.drawable.big_mix_fire));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_SOI, new MixCoverModel(JfifUtil.MARKER_SOI, R.drawable.mix_light_rain, R.drawable.big_mix_light_rain));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_EOI, new MixCoverModel(JfifUtil.MARKER_EOI, R.drawable.mix_evening_beach, R.drawable.big_mix_evening_beach));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_SOS, new MixCoverModel(JfifUtil.MARKER_SOS, R.drawable.mix_dryer, R.drawable.big_mix_dryer));
        mixCoverItemSparseArray.put(219, new MixCoverModel(219, R.drawable.mix_lullaby_music_box, R.drawable.big_mix_lullaby_music_box));
        mixCoverItemSparseArray.put(220, new MixCoverModel(220, R.drawable.mix_nature_melody, R.drawable.big_mix_nature_melody));
        mixCoverItemSparseArray.put(222, new MixCoverModel(222, R.drawable.mix_relax_time, R.drawable.big_mix_relax_time));
        mixCoverItemSparseArray.put(223, new MixCoverModel(223, R.drawable.mix_yoga_music, R.drawable.big_mix_yoga_music));
        mixCoverItemSparseArray.put(224, new MixCoverModel(224, R.drawable.mix_memories, R.drawable.big_mix_memories));
        mixCoverItemSparseArray.put(JfifUtil.MARKER_APP1, new MixCoverModel(JfifUtil.MARKER_APP1, R.drawable.mix_deep_relaxation, R.drawable.big_mix_deep_relaxation));
        mixCoverItemSparseArray.put(226, new MixCoverModel(226, R.drawable.mix_meditation, R.drawable.big_mix_meditation));
        mixCoverItemSparseArray.put(227, new MixCoverModel(227, R.drawable.mix_zen, R.drawable.big_mix_zen));
        mixCoverItemSparseArray.put(228, new MixCoverModel(228, R.drawable.mix_meditation_in_forest, R.drawable.big_mix_meditation_in_forest));
        mixCoverItemSparseArray.put(229, new MixCoverModel(229, R.drawable.mix_universe, R.drawable.big_mix_universe));
        mixCoverItemSparseArray.put(230, new MixCoverModel(230, R.drawable.mix_cafe, R.drawable.big_mix_cafe));
        mixCoverItemSparseArray.put(231, new MixCoverModel(231, R.drawable.mix_deep_thinking, R.drawable.big_mix_deep_thinking));
        mixCoverItemSparseArray.put(232, new MixCoverModel(232, R.drawable.mix_focus, R.drawable.big_mix_focus));
        mixCoverItemSparseArray.put(233, new MixCoverModel(233, R.drawable.mix_rainy_weekend, R.drawable.big_mix_rainy_weekend));
        mixCoverItemSparseArray.put(234, new MixCoverModel(234, R.drawable.mix_library, R.drawable.big_mix_library));
        mixCoverItemSparseArray.put(235, new MixCoverModel(235, R.drawable.mix_rain_on_leaves, R.drawable.big_mix_rain_on_leaves));
    }

    private static void createSoundData(Context context) {
        SoundModel soundModel = new SoundModel(101, context.getResources().getString(R.string.sound_name_rain_on_leaves), "rain_on_leaves", R.drawable.vector_sound_ic_rain_on_leaves, 50);
        SoundModel soundModel2 = new SoundModel(102, context.getResources().getString(R.string.sound_name_rain), "rain", R.drawable.vector_sound_ic_rain, 50);
        SoundModel soundModel3 = new SoundModel(103, context.getResources().getString(R.string.sound_name_storm), "storm", R.drawable.vector_sound_ic_storm, 50);
        soundModel2 = new SoundModel(104, context.getResources().getString(R.string.sound_name_thunder), "rain_thunders", R.drawable.vector_sound_ic_thunder, 50);
        soundModel3 = new SoundModel(105, context.getResources().getString(R.string.sound_name_wind_leaves), "forrest_wind", R.drawable.vector_sound_ic_wind_leaves, 50);
        soundModel2 = new SoundModel(106, context.getResources().getString(R.string.sound_name_rain_on_window), "rain_on_window", R.drawable.vector_sound_ic_rain_on_window, 50);
        soundModel3 = new SoundModel(107, context.getResources().getString(R.string.sound_name_rain_on_roof), "rain_on_roof", R.drawable.vector_sound_ic_rain_on_roof, 50);
        soundModel2 = new SoundModel(108, context.getResources().getString(R.string.sound_name_wind), "wind", R.drawable.vector_sound_ic_wind, 75);
        SoundModel soundModel4 = new SoundModel(109, context.getResources().getString(R.string.sound_name_light_rain), "rain_light", R.drawable.vector_sound_ic_light_rain, 50);
        SoundModel soundModel5 = new SoundModel(110, context.getResources().getString(R.string.sound_name_rainstorm), "rain_thunders", R.drawable.vector_sound_ic_rainstorm, 50);
        soundModel4 = new SoundModel(111, context.getResources().getString(R.string.sound_name_night), "night", R.drawable.vector_sound_ic_night, 50);
        soundModel5 = new SoundModel(112, context.getResources().getString(R.string.sound_name_waves), "meditation_stones", R.drawable.vector_sound_ic_waves, 50);
        soundModel4 = new SoundModel(113, context.getResources().getString(R.string.sound_name_snow), "snow", R.drawable.vector_sound_ic_snow, 50);
        soundModel5 = new SoundModel(114, context.getResources().getString(R.string.sound_name_heavy_rain), "heavy_rain", R.drawable.vector_sound_ic_heavy_rain, 50);
        SoundModel soundModel6 = soundModel4;
        soundModel4 = new SoundModel(115, context.getResources().getString(R.string.sound_name_river), "river", R.drawable.vector_sound_ic_river, 50);
        SoundModel soundModel7 = new SoundModel(116, context.getResources().getString(R.string.sound_name_forest), "forest_forest", R.drawable.vector_sound_ic_forest, 50);
        SoundModel soundModel8 = soundModel4;
        soundModel4 = new SoundModel(117, context.getResources().getString(R.string.sound_name_cave), "cave", R.drawable.vector_sound_ic_cave, 50);
        SoundModel soundModel9 = new SoundModel(118, context.getResources().getString(R.string.sound_name_fire), "forest_fire", R.drawable.vector_sound_ic_fire, 50);
        SoundModel soundModel10 = soundModel4;
        soundModel4 = new SoundModel(119, context.getResources().getString(R.string.sound_name_train), "city_subway", R.drawable.vector_sound_ic_train, 75);
        SoundModel soundModel11 = new SoundModel(120, context.getResources().getString(R.string.sound_name_flight), "city_airplane", R.drawable.vector_sound_ic_flight, 50);
        SoundModel soundModel12 = soundModel4;
        soundModel4 = new SoundModel(121, context.getResources().getString(R.string.sound_name_driving), "city_traffic", R.drawable.vector_sound_ic_driving, 75);
        SoundModel soundModel13 = new SoundModel(122, context.getResources().getString(R.string.sound_name_rain_on_tent), "rain_on_tent", R.drawable.vector_sound_ic_rain_on_tent, 50);
        SoundModel soundModel14 = soundModel4;
        soundModel4 = new SoundModel(123, context.getResources().getString(R.string.sound_name_urban_rain), "urban_rain", R.drawable.vector_sound_ic_urban_rain, 50);
        SoundModel soundModel15 = new SoundModel(124, context.getResources().getString(R.string.sound_name_white_noise), "meditation_white_noise", R.drawable.vector_sound_ic_white_noise, 50);
        SoundModel soundModel16 = soundModel4;
        soundModel4 = new SoundModel(125, context.getResources().getString(R.string.sound_name_bird), "forest_birds", R.drawable.vector_sound_ic_bird, 50);
        SoundModel soundModel17 = new SoundModel(126, context.getResources().getString(R.string.sound_name_frog), "forest_frogs", R.drawable.vector_sound_ic_frog, 50);
        SoundModel soundModel18 = soundModel4;
        soundModel4 = new SoundModel(127, context.getResources().getString(R.string.sound_name_owl), "forest_owls", R.drawable.vector_sound_ic_owl, 50);
        SoundModel soundModel19 = new SoundModel(128, context.getResources().getString(R.string.sound_name_cricket), "forest_crickets", R.drawable.vector_sound_ic_cricket, 50);
        SoundModel soundModel20 = soundModel4;
        soundModel4 = new SoundModel(129, context.getResources().getString(R.string.sound_name_parrot), "parrot", R.drawable.vector_sound_ic_parrot, 50);
        soundModels.add(soundModel);
        soundModels.add(soundModel2);
        soundModels.add(soundModel3);
        soundModels.add(soundModel2);
        soundModels.add(soundModel3);
        soundModels.add(soundModel2);
        soundModels.add(soundModel3);
        soundModels.add(soundModel2);
        soundModels.add(soundModel4);
        soundModels.add(soundModel5);
        soundModels.add(soundModel4);
        soundModels.add(soundModel5);
        soundModels.add(soundModel4);
        soundModels.add(soundModel5);
        soundModels.add(soundModel6);
        soundModels.add(soundModel7);
        soundModels.add(soundModel8);
        soundModels.add(soundModel9);
        soundModels.add(soundModel10);
        soundModels.add(soundModel11);
        soundModels.add(soundModel12);
        soundModels.add(soundModel13);
        soundModels.add(soundModel14);
        soundModels.add(soundModel15);
        soundModels.add(soundModel16);
        soundModels.add(soundModel17);
        soundModels.add(soundModel18);
        soundModels.add(soundModel19);
        soundModels.add(soundModel20);
        SoundModel soundModel21 = new SoundModel(131, context.getResources().getString(R.string.sound_name_cafe), "cafe", R.drawable.vector_sound_ic_cafe, 50);
        SoundModel soundModel22 = new SoundModel(136, context.getResources().getString(R.string.sound_name_flute), "flute", R.drawable.vector_sound_ic_flute, 50);
        soundModel21 = new SoundModel(138, context.getResources().getString(R.string.sound_name_harp), "harp", R.drawable.vector_sound_ic_harp, 50);
        SoundModel soundModel23 = new SoundModel(139, context.getResources().getString(R.string.sound_name_jazz), "jazz", R.drawable.vector_sound_ic_jazz, 50);
        SoundModel soundModel24 = new SoundModel(141, context.getResources().getString(R.string.sound_name_melody), "melody", R.drawable.vector_sound_ic_melody, 50);
        soundModel23 = new SoundModel(142, context.getResources().getString(R.string.sound_name_music_box), "music_box", R.drawable.vector_sound_ic_music_box, 50);
        soundModel24 = new SoundModel(143, context.getResources().getString(R.string.sound_name_piano), "meditation_piano", R.drawable.vector_sound_ic_piano, 50);
        soundModel23 = new SoundModel(144, context.getResources().getString(R.string.sound_name_piano_2), "meditation_piano_2", R.drawable.vector_sound_ic_piano, 50);
        soundModel24 = new SoundModel(147, context.getResources().getString(R.string.sound_name_yoga_music), "yoga_music", R.drawable.vector_sound_ic_yoga_music, 50);
        soundModel23 = new SoundModel(135, context.getResources().getString(R.string.sound_name_evening_beach), "evening_beach", R.drawable.vector_sound_ic_evening_beach, 50);
        SoundModel soundModel25 = new SoundModel(145, context.getResources().getString(R.string.sound_name_tide), "tide", R.drawable.vector_sound_ic_tide, 50);
        soundModel9 = new SoundModel(148, context.getResources().getString(R.string.sound_name_zen), "zen", R.drawable.vector_sound_ic_zen, 50);
        soundModel25 = new SoundModel(146, context.getResources().getString(R.string.sound_name_universe), "universe", R.drawable.vector_sound_ic_universe, 50);
        soundModel9 = new SoundModel(140, context.getResources().getString(R.string.sound_name_meditation), "meditation", R.drawable.vector_sound_ic_meditation, 50);
        soundModel25 = new SoundModel(137, context.getResources().getString(R.string.sound_name_focus), "focus", R.drawable.vector_sound_ic_focus, 50);
        SoundModel soundModel26 = new SoundModel(133, context.getResources().getString(R.string.sound_name_dream), "dream", R.drawable.vector_sound_ic_dream, 50);
        SoundModel soundModel27 = new SoundModel(130, context.getResources().getString(R.string.sound_name_astral), "astral", R.drawable.vector_sound_ic_astral, 50);
        SoundModel soundModel28 = new SoundModel(134, context.getResources().getString(R.string.sound_name_dryer), "city_washing_machine", R.drawable.vector_sound_ic_dryer, 50);
        soundModel5 = new SoundModel(132, context.getResources().getString(R.string.sound_name_crowd), "crowd", R.drawable.vector_sound_ic_crowd, 50);
        soundModel7 = new SoundModel(149, context.getResources().getString(R.string.sound_name_thunderstorm), "rain_thunders", R.drawable.vector_sound_ic_thunderstorm, 50);
        SoundModel soundModel29 = new SoundModel(150, context.getResources().getString(R.string.sound_name_rain_on_car_window), "rain_on_car_windows", R.drawable.vector_sound_ic_rain_on_car_window, 50);
        soundModel11 = new SoundModel(151, context.getResources().getString(R.string.sound_name_rain_in_city), "rain_in_city", R.drawable.vector_sound_ic_rain_in_city, 50);
        soundModel13 = new SoundModel(152, context.getResources().getString(R.string.sound_name_rain_on_car_roof), "rain_on_car_roof", R.drawable.vector_sound_ic_rain_on_car_roof, 50);
        soundModel15 = new SoundModel(153, context.getResources().getString(R.string.sound_name_pink_noise), "meditation_pink_noise", R.drawable.vector_sound_ic_pink_noise, 50);
        soundModel6 = new SoundModel(154, context.getResources().getString(R.string.sound_name_brown_noise), "meditation_brown_noise", R.drawable.vector_sound_ic_brown_noise, 50);
        soundModel17 = new SoundModel(155, context.getResources().getString(R.string.sound_name_wolf), "wolf", R.drawable.vector_sound_ic_wolf, 50);
        soundModel19 = new SoundModel(156, context.getResources().getString(R.string.sound_name_cat_purring), "cat_purring", R.drawable.vector_sound_ic_cat_purring, 50);
        soundModel14 = new SoundModel(157, context.getResources().getString(R.string.sound_name_whales), "whales", R.drawable.vector_sound_ic_whales, 50);
        SoundModel soundModel30 = new SoundModel(158, context.getResources().getString(R.string.sound_name_deep_sea), "deep_sea", R.drawable.vector_sound_ic_deep_sea, 50);
        SoundModel soundModel31 = new SoundModel(159, context.getResources().getString(R.string.sound_name_womb), "womb", R.drawable.vector_sound_ic_womb, 50);
        SoundModel soundModel32 = new SoundModel(160, context.getResources().getString(R.string.sound_name_brahms_lullaby), "brahms_lullaby", R.drawable.vector_sound_ic_brahms_lullaby, 50);
        soundModel16 = new SoundModel(161, "Test Sound", "brahms_lullaby", R.drawable.vector_sound_ic_brahms_lullaby, 50);
        soundModels.add(soundModel21);
        soundModels.add(soundModel22);
        soundModels.add(soundModel21);
        soundModels.add(soundModel23);
        soundModels.add(soundModel24);
        soundModels.add(soundModel23);
        soundModels.add(soundModel24);
        soundModels.add(soundModel23);
        soundModels.add(soundModel24);
        soundModels.add(soundModel23);
        soundModels.add(soundModel25);
        soundModels.add(soundModel9);
        soundModels.add(soundModel25);
        soundModels.add(soundModel9);
        soundModels.add(soundModel25);
        soundModels.add(soundModel26);
        soundModels.add(soundModel27);
        soundModels.add(soundModel28);
        soundModels.add(soundModel5);
        soundModels.add(soundModel7);
        soundModels.add(soundModel29);
        soundModels.add(soundModel11);
        soundModels.add(soundModel13);
        soundModels.add(soundModel15);
        soundModels.add(soundModel6);
        soundModels.add(soundModel17);
        soundModels.add(soundModel19);
        soundModels.add(soundModel14);
        soundModels.add(soundModel30);
        soundModels.add(soundModel31);
        soundModels.add(soundModel32);
        soundModels.add(soundModel16);
        for (SoundModel soundModel33 : soundModels) {
            soundItemSparseArray.put(soundModel33.getSoundId(), soundModel33);
        }
    }

    public static List<MixModel> getListMixItem(Context context) {
        ArrayList arrayList = new ArrayList();
        List asList = ArrayUtils.asList(mixItemSparseArray);
        Collections.sort(asList, new Comparator<MixModel>() {
            public int compare(MixModel mixModel, MixModel mixModel2) {
                return mixModel.getMixSoundId() - mixModel2.getMixSoundId();
            }
        });
        return asList;
    }

    public static List<SoundModel> getListSoundItem(Context context) {
        return ArrayUtils.asList(soundItemSparseArray);
    }

    public static List<MixCoverModel> getListMixCoverItem(Context context) {
        ArrayList arrayList = new ArrayList();
        return ArrayUtils.asList(mixCoverItemSparseArray);
    }

    public static List<SettingsModel> getListSettingItem(Context context) {
        return new ArrayList();
    }

    public static List<MixCategoryModel> getListMixCategoryItem(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MixCategoryModel(0, "All"));
        List customMixList = SaveDataHelper.getCustomMixList(context);
        String str = "Work";
        String str2 = "Mediation";
        String str3 = "Relax";
        String str4 = "Rain";
        String str5 = "Sleep";
        if (customMixList == null || customMixList.size() <= 0) {
            arrayList.add(new MixCategoryModel(1, str5));
            arrayList.add(new MixCategoryModel(2, str4));
            arrayList.add(new MixCategoryModel(3, str3));
            arrayList.add(new MixCategoryModel(4, str2));
            arrayList.add(new MixCategoryModel(5, str));
        } else {
            arrayList.add(new MixCategoryModel(1, "Custom"));
            arrayList.add(new MixCategoryModel(2, str5));
            arrayList.add(new MixCategoryModel(3, str4));
            arrayList.add(new MixCategoryModel(4, str3));
            arrayList.add(new MixCategoryModel(5, str2));
            arrayList.add(new MixCategoryModel(6, str));
        }
        ((MixCategoryModel) arrayList.get(0)).setChecked(true);
        return arrayList;
    }

    public static List<MixCoverModel> getCustomCoverList(Context context) {
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(new MixCoverModel(0, R.drawable.custom_cover_1, R.drawable.big_custom_cover_1));
        arrayList.add(new MixCoverModel(1, R.drawable.custom_cover_2, R.drawable.big_custom_cover_2));
        arrayList.add(new MixCoverModel(2, R.drawable.custom_cover_3, R.drawable.big_custom_cover_3));
        arrayList.add(new MixCoverModel(3, R.drawable.custom_cover_4, R.drawable.big_custom_cover_4));
        arrayList.add(new MixCoverModel(4, R.drawable.custom_cover_5, R.drawable.big_custom_cover_5));
        arrayList.add(new MixCoverModel(5, R.drawable.custom_cover_6, R.drawable.big_custom_cover_6));
        arrayList.add(new MixCoverModel(6, R.drawable.custom_cover_7, R.drawable.big_custom_cover_7));
        arrayList.add(new MixCoverModel(7, R.drawable.custom_cover_8, R.drawable.big_custom_cover_8));
        return arrayList;
    }

    public static SoundModel getSoundItemById(int i) {
        return soundItemSparseArray.get(i);
    }

    public static MixModel getMixItemById(int i) {
        return mixItemSparseArray.get(i);
    }

    public static MixCoverModel getMixCoverItemById(int i) {
        return mixCoverItemSparseArray.get(i);
    }
}
