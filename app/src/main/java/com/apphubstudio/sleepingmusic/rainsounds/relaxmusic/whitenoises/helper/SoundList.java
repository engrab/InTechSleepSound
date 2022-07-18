package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.facebook.imageutils.JfifUtil;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.R;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMixCategory;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMixCover;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMix;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSetting;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSound;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.utils.ArrayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SoundList {
    private static final SparseArray<ModelMixCover> spareArray = new SparseArray();
    private static SparseArray<ModelMix> mixModelSparseArray = new SparseArray();
    public static int numerTab = 7;
    private static SparseArray<ModelSound> soundItemSparseArray = new SparseArray();
    private static List<ModelSound> modelSounds = new ArrayList();

    public static void createData(Context context) {
        soundItemSparseArray = new SparseArray();
        modelSounds = new ArrayList();
        mixModelSparseArray = new SparseArray();
        createSoundData(context);
        createMixCoverItemData(context);
        createMixItemData(context);
    }

    private static void createMixItemData(Context context) {
        ModelMix modelMix = new ModelMix(201, 3, context.getResources().getString(R.string.mix_sound_name_spring_rain), createMixCoverItem(201), createListSoundItem(new int[]{102, 104}, new int[]{50, 50}));
        ModelMix modelMix2 = new ModelMix(202, 4, context.getResources().getString(R.string.mix_sound_name_peaceful_night), createMixCoverItem(202), createListSoundItem(new int[]{111}, new int[]{50}));
        ModelMix modelMix3 = new ModelMix(203, 5, context.getResources().getString(R.string.mix_sound_name_beach), createMixCoverItem(203), createListSoundItem(new int[]{112}, new int[]{50}));
        ModelMix modelMix4 = modelMix2;
        modelMix2 = new ModelMix(JfifUtil.MARKER_SOI, 3, context.getResources().getString(R.string.sound_name_light_rain), createMixCoverItem(JfifUtil.MARKER_SOI), createListSoundItem(new int[]{109}, new int[]{50}));
        ModelMix modelMix5 = new ModelMix(204, 5, context.getResources().getString(R.string.mix_sound_name_forest_adventure), createMixCoverItem(204), createListSoundItem(new int[]{116, 115}, new int[]{50, 50}));
        modelMix3 = new ModelMix(JfifUtil.MARKER_RST7, 2, context.getResources().getString(R.string.mix_sound_name_fire), createMixCoverItem(JfifUtil.MARKER_RST7), createListSoundItem(new int[]{118}, new int[]{50}));
        modelMix5 = new ModelMix(205, 2, context.getResources().getString(R.string.mix_sound_name_train_journey), createMixCoverItem(205), createListSoundItem(new int[]{119, 107}, new int[]{50, 5}));
        ModelMix modelMix6 = new ModelMix(210, 5, context.getResources().getString(R.string.mix_sound_name_babbling_brook), createMixCoverItem(210), createListSoundItem(new int[]{115, 125}, new int[]{50, 50}));
        modelMix5 = new ModelMix(209, 3, context.getResources().getString(R.string.mix_sound_name_rain_in_forest), createMixCoverItem(209), createListSoundItem(new int[]{101, 104}, new int[]{50, 50}));
        modelMix6 = new ModelMix(207, 2, context.getResources().getString(R.string.mix_sound_name_countryside), createMixCoverItem(207), createListSoundItem(new int[]{111, 118}, new int[]{50, 50}));
        ModelMix modelMix7 = new ModelMix(JfifUtil.MARKER_RST0, 3, context.getResources().getString(R.string.mix_sound_name_rain_on_roof), createMixCoverItem(JfifUtil.MARKER_RST0), createListSoundItem(new int[]{107, 118}, new int[]{50, 50}));
        ModelMix modelMix8 = new ModelMix(206, 2, context.getResources().getString(R.string.mix_sound_name_cold_winter), createMixCoverItem(206), createListSoundItem(new int[]{108, 113, 118}, new int[]{50, 50, 50}));
        ModelMix modelMix9 = new ModelMix(213, 2, context.getResources().getString(R.string.mix_sound_name_city_life), createMixCoverItem(213), createListSoundItem(new int[]{121, 106}, new int[]{50, 10}));
        ModelMix modelMix10 = new ModelMix(211, 5, context.getResources().getString(R.string.mix_sound_name_autumn_jungle), createMixCoverItem(211), createListSoundItem(new int[]{105, 125}, new int[]{50, 50}));
        ModelMix modelMix11 = modelMix10;
        ModelMix modelMix12 = new ModelMix(212, 2, context.getResources().getString(R.string.mix_sound_name_flying_sky), createMixCoverItem(212), createListSoundItem(new int[]{120}, new int[]{50}));
        modelMix10 = new ModelMix(214, 2, context.getResources().getString(R.string.mix_sound_name_cave), createMixCoverItem(214), createListSoundItem(new int[]{117, 118}, new int[]{50, 50}));
        mixModelSparseArray.put(201, modelMix12);
        mixModelSparseArray.put(202, modelMix2);
        mixModelSparseArray.put(203, modelMix3);
        mixModelSparseArray.put(JfifUtil.MARKER_SOI, modelMix4);
        mixModelSparseArray.put(204, modelMix5);
        mixModelSparseArray.put(JfifUtil.MARKER_RST7, modelMix3);
        mixModelSparseArray.put(205, modelMix5);
        mixModelSparseArray.put(210, modelMix6);
        mixModelSparseArray.put(209, modelMix5);
        mixModelSparseArray.put(207, modelMix6);
        mixModelSparseArray.put(JfifUtil.MARKER_RST0, modelMix7);
        mixModelSparseArray.put(206, modelMix8);
        mixModelSparseArray.put(213, modelMix9);
        mixModelSparseArray.put(211, modelMix11);
        mixModelSparseArray.put(212, modelMix12);
        mixModelSparseArray.put(214, modelMix10);
        ModelMix modelMix13 = new ModelMix(220, 4, context.getResources().getString(R.string.mix_sound_name_nature_melody), createMixCoverItem(220), createListSoundItem(new int[]{141, 116}, new int[]{50, 50}));
        ModelMix modelMix14 = new ModelMix(222, 4, context.getResources().getString(R.string.mix_sound_name_relax_time), createMixCoverItem(222), createListSoundItem(new int[]{138, 109}, new int[]{50, 50}));
        ModelMix modelMix15 = new ModelMix(228, 5, context.getResources().getString(R.string.mix_sound_name_meditate_in_forest), createMixCoverItem(228), createListSoundItem(new int[]{136, 115}, new int[]{50, 50}));
        modelMix2 = new ModelMix(JfifUtil.MARKER_EOI, 2, context.getResources().getString(R.string.mix_sound_name_evening_beach), createMixCoverItem(JfifUtil.MARKER_EOI), createListSoundItem(new int[]{135, 148}, new int[]{50, 50}));
        ModelMix modelMix16 = new ModelMix(223, 4, context.getResources().getString(R.string.mix_sound_name_yoga_music), createMixCoverItem(223), createListSoundItem(new int[]{147}, new int[]{50}));
        modelMix2 = new ModelMix(235, 3, context.getResources().getString(R.string.sound_name_rain_on_leaves), createMixCoverItem(235), createListSoundItem(new int[]{101}, new int[]{50}));
        modelMix16 = new ModelMix(224, 4, context.getResources().getString(R.string.mix_sound_name_memories), createMixCoverItem(224), createListSoundItem(new int[]{143}, new int[]{50, 50}));
        modelMix2 = new ModelMix(JfifUtil.MARKER_APP1, 4, context.getResources().getString(R.string.mix_sound_name_deep_relaxation), createMixCoverItem(JfifUtil.MARKER_APP1), createListSoundItem(new int[]{144}, new int[]{50}));
        modelMix16 = new ModelMix(219, 2, context.getResources().getString(R.string.mix_sound_name_lullaby_music_box), createMixCoverItem(219), createListSoundItem(new int[]{142}, new int[]{50}));
        modelMix2 = new ModelMix(227, 5, context.getResources().getString(R.string.mix_sound_name_zen), createMixCoverItem(227), createListSoundItem(new int[]{148}, new int[]{50}));
        modelMix3 = new ModelMix(226, 5, context.getResources().getString(R.string.mix_sound_name_meditation), createMixCoverItem(226), createListSoundItem(new int[]{140}, new int[]{50}));
        ModelMix modelMix17 = new ModelMix(229, 6, context.getResources().getString(R.string.mix_sound_name_universe), createMixCoverItem(229), createListSoundItem(new int[]{146}, new int[]{50}));
        modelMix3 = new ModelMix(231, 6, context.getResources().getString(R.string.mix_sound_name_deep_thinking), createMixCoverItem(231), createListSoundItem(new int[]{133}, new int[]{50}));
        modelMix3 = modelMix3;
        modelMix17 = new ModelMix(234, 6, context.getResources().getString(R.string.mix_sound_name_library), createMixCoverItem(234), createListSoundItem(new int[]{130}, new int[]{50}));
        modelMix4 = modelMix17;
        ModelMix modelMix18 = new ModelMix(232, 6, context.getResources().getString(R.string.mix_sound_name_focus), createMixCoverItem(232), createListSoundItem(new int[]{137}, new int[]{50}));
        modelMix9 = new ModelMix(233, 6, context.getResources().getString(R.string.mix_sound_name_rainy_weekend), createMixCoverItem(233), createListSoundItem(new int[]{139, 109}, new int[]{50, 16}));
        ModelMix modelMix19 = new ModelMix(230, 6, context.getResources().getString(R.string.mix_sound_name_cafe), createMixCoverItem(230), createListSoundItem(new int[]{131, 132}, new int[]{24, 86}));
        modelMix9 = new ModelMix(JfifUtil.MARKER_SOS, 2, context.getResources().getString(R.string.mix_sound_name_dryer), createMixCoverItem(JfifUtil.MARKER_SOS), createListSoundItem(new int[]{134}, new int[]{50}));
        mixModelSparseArray.put(modelMix13.getmMixSoundId(), modelMix13);
        mixModelSparseArray.put(modelMix14.getmMixSoundId(), modelMix14);
        mixModelSparseArray.put(modelMix15.getmMixSoundId(), modelMix15);
        mixModelSparseArray.put(modelMix2.getmMixSoundId(), modelMix2);
        mixModelSparseArray.put(modelMix16.getmMixSoundId(), modelMix16);
        mixModelSparseArray.put(modelMix2.getmMixSoundId(), modelMix2);
        mixModelSparseArray.put(modelMix16.getmMixSoundId(), modelMix16);
        mixModelSparseArray.put(modelMix2.getmMixSoundId(), modelMix2);
        mixModelSparseArray.put(modelMix16.getmMixSoundId(), modelMix16);
        mixModelSparseArray.put(modelMix2.getmMixSoundId(), modelMix2);
        mixModelSparseArray.put(modelMix3.getmMixSoundId(), modelMix3);
        mixModelSparseArray.put(modelMix17.getmMixSoundId(), modelMix17);
        mixModelSparseArray.put(modelMix3.getmMixSoundId(), modelMix3);
        mixModelSparseArray.put(modelMix4.getmMixSoundId(), modelMix4);
        mixModelSparseArray.put(modelMix18.getmMixSoundId(), modelMix18);
        mixModelSparseArray.put(modelMix9.getmMixSoundId(), modelMix9);
        mixModelSparseArray.put(modelMix19.getmMixSoundId(), modelMix19);
        mixModelSparseArray.put(modelMix9.getmMixSoundId(), modelMix9);
        List<ModelMix> customMixList = HelperSaveData.getCustomMixList(context);
        if (customMixList != null && customMixList.size() > 0) {
            for (ModelMix modelMix20 : customMixList) {
                mixModelSparseArray.put(modelMix20.getmMixSoundId(), modelMix20);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(modelMix20.getmMixSoundId());
                stringBuilder.append("");
                Log.e("CUSTOM_MIX", stringBuilder.toString());
            }
        }
    }

    private static List<ModelSound> createListSoundItem(int[] iArr, int[] iArr2) {
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            ModelSound modelSound = new ModelSound(soundItemSparseArray.get(iArr[i]));
            modelSound.setmVol(iArr2[i]);
            arrayList.add(modelSound);
        }
        return arrayList;
    }

    private static ModelMixCover createMixCoverItem(int i) {
        return spareArray.get(i);
    }

    private static void createMixCoverItemData(Context context) {
        spareArray.put(0, new ModelMixCover(0, R.drawable.custom_cover_1, R.drawable.big_custom_cover_1));
        spareArray.put(1, new ModelMixCover(1, R.drawable.custom_cover_2, R.drawable.big_custom_cover_2));
        spareArray.put(2, new ModelMixCover(2, R.drawable.custom_cover_3, R.drawable.big_custom_cover_3));
        spareArray.put(3, new ModelMixCover(3, R.drawable.custom_cover_4, R.drawable.big_custom_cover_4));
        spareArray.put(4, new ModelMixCover(4, R.drawable.custom_cover_5, R.drawable.big_custom_cover_5));
        spareArray.put(5, new ModelMixCover(5, R.drawable.custom_cover_6, R.drawable.big_custom_cover_6));
        spareArray.put(6, new ModelMixCover(6, R.drawable.custom_cover_7, R.drawable.big_custom_cover_7));
        spareArray.put(7, new ModelMixCover(7, R.drawable.custom_cover_8, R.drawable.big_custom_cover_8));
        spareArray.put(201, new ModelMixCover(201, R.drawable.mix_spring_rain, R.drawable.big_mix_spring_rain));
        spareArray.put(202, new ModelMixCover(202, R.drawable.mix_peaceful_night, R.drawable.big_mix_peaceful_night));
        spareArray.put(203, new ModelMixCover(203, R.drawable.mix_beach, R.drawable.big_mix_beach));
        spareArray.put(204, new ModelMixCover(204, R.drawable.mix_forest_adventure, R.drawable.big_mix_forest_adventure));
        spareArray.put(205, new ModelMixCover(205, R.drawable.mix_train_journey, R.drawable.big_mix_train_journey));
        spareArray.put(206, new ModelMixCover(206, R.drawable.mix_cold_winter, R.drawable.big_mix_cold_winter));
        spareArray.put(207, new ModelMixCover(207, R.drawable.mix_countryside, R.drawable.big_mix_countryside));
        spareArray.put(JfifUtil.MARKER_RST0, new ModelMixCover(JfifUtil.MARKER_RST0, R.drawable.mix_rain_on_roof, R.drawable.big_mix_rain_on_roof));
        spareArray.put(209, new ModelMixCover(209, R.drawable.mix_rain_in_forest, R.drawable.big_mix_rain_in_forest));
        spareArray.put(210, new ModelMixCover(210, R.drawable.mix_babbling_brook, R.drawable.big_mix_babbling_brook));
        spareArray.put(211, new ModelMixCover(211, R.drawable.mix_autumn_jungle, R.drawable.big_mix_autumn_jungle));
        spareArray.put(212, new ModelMixCover(212, R.drawable.mix_flying_sky, R.drawable.big_mix_flying_sky));
        spareArray.put(213, new ModelMixCover(213, R.drawable.mix_city_life, R.drawable.big_mix_city_life));
        spareArray.put(214, new ModelMixCover(214, R.drawable.mix_cave, R.drawable.big_mix_cave));
        spareArray.put(JfifUtil.MARKER_RST7, new ModelMixCover(JfifUtil.MARKER_RST7, R.drawable.mix_fire, R.drawable.big_mix_fire));
        spareArray.put(JfifUtil.MARKER_SOI, new ModelMixCover(JfifUtil.MARKER_SOI, R.drawable.mix_light_rain, R.drawable.big_mix_light_rain));
        spareArray.put(JfifUtil.MARKER_EOI, new ModelMixCover(JfifUtil.MARKER_EOI, R.drawable.mix_evening_beach, R.drawable.big_mix_evening_beach));
        spareArray.put(JfifUtil.MARKER_SOS, new ModelMixCover(JfifUtil.MARKER_SOS, R.drawable.mix_dryer, R.drawable.big_mix_dryer));
        spareArray.put(219, new ModelMixCover(219, R.drawable.mix_lullaby_music_box, R.drawable.big_mix_lullaby_music_box));
        spareArray.put(220, new ModelMixCover(220, R.drawable.mix_nature_melody, R.drawable.big_mix_nature_melody));
        spareArray.put(222, new ModelMixCover(222, R.drawable.mix_relax_time, R.drawable.big_mix_relax_time));
        spareArray.put(223, new ModelMixCover(223, R.drawable.mix_yoga_music, R.drawable.big_mix_yoga_music));
        spareArray.put(224, new ModelMixCover(224, R.drawable.mix_memories, R.drawable.big_mix_memories));
        spareArray.put(JfifUtil.MARKER_APP1, new ModelMixCover(JfifUtil.MARKER_APP1, R.drawable.mix_deep_relaxation, R.drawable.big_mix_deep_relaxation));
        spareArray.put(226, new ModelMixCover(226, R.drawable.mix_meditation, R.drawable.big_mix_meditation));
        spareArray.put(227, new ModelMixCover(227, R.drawable.mix_zen, R.drawable.big_mix_zen));
        spareArray.put(228, new ModelMixCover(228, R.drawable.mix_meditation_in_forest, R.drawable.big_mix_meditation_in_forest));
        spareArray.put(229, new ModelMixCover(229, R.drawable.mix_universe, R.drawable.big_mix_universe));
        spareArray.put(230, new ModelMixCover(230, R.drawable.mix_cafe, R.drawable.big_mix_cafe));
        spareArray.put(231, new ModelMixCover(231, R.drawable.mix_deep_thinking, R.drawable.big_mix_deep_thinking));
        spareArray.put(232, new ModelMixCover(232, R.drawable.mix_focus, R.drawable.big_mix_focus));
        spareArray.put(233, new ModelMixCover(233, R.drawable.mix_rainy_weekend, R.drawable.big_mix_rainy_weekend));
        spareArray.put(234, new ModelMixCover(234, R.drawable.mix_library, R.drawable.big_mix_library));
        spareArray.put(235, new ModelMixCover(235, R.drawable.mix_rain_on_leaves, R.drawable.big_mix_rain_on_leaves));
    }

    private static void createSoundData(Context context) {
        ModelSound modelSound = new ModelSound(101, context.getResources().getString(R.string.sound_name_rain_on_leaves), "rain_on_leaves", R.drawable.vector_sound_ic_rain_on_leaves, 50);
        ModelSound modelSound2 = new ModelSound(102, context.getResources().getString(R.string.sound_name_rain), "rain", R.drawable.vector_sound_ic_rain, 50);
        ModelSound modelSound3 = new ModelSound(103, context.getResources().getString(R.string.sound_name_storm), "storm", R.drawable.vector_sound_ic_storm, 50);
        modelSound2 = new ModelSound(104, context.getResources().getString(R.string.sound_name_thunder), "rain_thunders", R.drawable.vector_sound_ic_thunder, 50);
        modelSound3 = new ModelSound(105, context.getResources().getString(R.string.sound_name_wind_leaves), "forrest_wind", R.drawable.vector_sound_ic_wind_leaves, 50);
        modelSound2 = new ModelSound(106, context.getResources().getString(R.string.sound_name_rain_on_window), "rain_on_window", R.drawable.vector_sound_ic_rain_on_window, 50);
        modelSound3 = new ModelSound(107, context.getResources().getString(R.string.sound_name_rain_on_roof), "rain_on_roof", R.drawable.vector_sound_ic_rain_on_roof, 50);
        modelSound2 = new ModelSound(108, context.getResources().getString(R.string.sound_name_wind), "wind", R.drawable.vector_sound_ic_wind, 75);
        ModelSound modelSound4 = new ModelSound(109, context.getResources().getString(R.string.sound_name_light_rain), "rain_light", R.drawable.vector_sound_ic_light_rain, 50);
        ModelSound modelSound5 = new ModelSound(110, context.getResources().getString(R.string.sound_name_rainstorm), "rain_thunders", R.drawable.vector_sound_ic_rainstorm, 50);
        modelSound4 = new ModelSound(111, context.getResources().getString(R.string.sound_name_night), "night", R.drawable.vector_sound_ic_night, 50);
        modelSound5 = new ModelSound(112, context.getResources().getString(R.string.sound_name_waves), "meditation_stones", R.drawable.vector_sound_ic_waves, 50);
        modelSound4 = new ModelSound(113, context.getResources().getString(R.string.sound_name_snow), "snow", R.drawable.vector_sound_ic_snow, 50);
        modelSound5 = new ModelSound(114, context.getResources().getString(R.string.sound_name_heavy_rain), "heavy_rain", R.drawable.vector_sound_ic_heavy_rain, 50);
        ModelSound modelSound6 = modelSound4;
        modelSound4 = new ModelSound(115, context.getResources().getString(R.string.sound_name_river), "river", R.drawable.vector_sound_ic_river, 50);
        ModelSound modelSound7 = new ModelSound(116, context.getResources().getString(R.string.sound_name_forest), "forest_forest", R.drawable.vector_sound_ic_forest, 50);
        ModelSound modelSound8 = modelSound4;
        modelSound4 = new ModelSound(117, context.getResources().getString(R.string.sound_name_cave), "cave", R.drawable.vector_sound_ic_cave, 50);
        ModelSound modelSound9 = new ModelSound(118, context.getResources().getString(R.string.sound_name_fire), "forest_fire", R.drawable.vector_sound_ic_fire, 50);
        ModelSound modelSound10 = modelSound4;
        modelSound4 = new ModelSound(119, context.getResources().getString(R.string.sound_name_train), "city_subway", R.drawable.vector_sound_ic_train, 75);
        ModelSound modelSound11 = new ModelSound(120, context.getResources().getString(R.string.sound_name_flight), "city_airplane", R.drawable.vector_sound_ic_flight, 50);
        ModelSound modelSound12 = modelSound4;
        modelSound4 = new ModelSound(121, context.getResources().getString(R.string.sound_name_driving), "city_traffic", R.drawable.vector_sound_ic_driving, 75);
        ModelSound modelSound13 = new ModelSound(122, context.getResources().getString(R.string.sound_name_rain_on_tent), "rain_on_tent", R.drawable.vector_sound_ic_rain_on_tent, 50);
        ModelSound modelSound14 = modelSound4;
        modelSound4 = new ModelSound(123, context.getResources().getString(R.string.sound_name_urban_rain), "urban_rain", R.drawable.vector_sound_ic_urban_rain, 50);
        ModelSound modelSound15 = new ModelSound(124, context.getResources().getString(R.string.sound_name_white_noise), "meditation_white_noise", R.drawable.vector_sound_ic_white_noise, 50);
        ModelSound modelSound16 = modelSound4;
        modelSound4 = new ModelSound(125, context.getResources().getString(R.string.sound_name_bird), "forest_birds", R.drawable.vector_sound_ic_bird, 50);
        ModelSound modelSound17 = new ModelSound(126, context.getResources().getString(R.string.sound_name_frog), "forest_frogs", R.drawable.vector_sound_ic_frog, 50);
        ModelSound modelSound18 = modelSound4;
        modelSound4 = new ModelSound(127, context.getResources().getString(R.string.sound_name_owl), "forest_owls", R.drawable.vector_sound_ic_owl, 50);
        ModelSound modelSound19 = new ModelSound(128, context.getResources().getString(R.string.sound_name_cricket), "forest_crickets", R.drawable.vector_sound_ic_cricket, 50);
        ModelSound modelSound20 = modelSound4;
        modelSound4 = new ModelSound(129, context.getResources().getString(R.string.sound_name_parrot), "parrot", R.drawable.vector_sound_ic_parrot, 50);
        modelSounds.add(modelSound);
        modelSounds.add(modelSound2);
        modelSounds.add(modelSound3);
        modelSounds.add(modelSound2);
        modelSounds.add(modelSound3);
        modelSounds.add(modelSound2);
        modelSounds.add(modelSound3);
        modelSounds.add(modelSound2);
        modelSounds.add(modelSound4);
        modelSounds.add(modelSound5);
        modelSounds.add(modelSound4);
        modelSounds.add(modelSound5);
        modelSounds.add(modelSound4);
        modelSounds.add(modelSound5);
        modelSounds.add(modelSound6);
        modelSounds.add(modelSound7);
        modelSounds.add(modelSound8);
        modelSounds.add(modelSound9);
        modelSounds.add(modelSound10);
        modelSounds.add(modelSound11);
        modelSounds.add(modelSound12);
        modelSounds.add(modelSound13);
        modelSounds.add(modelSound14);
        modelSounds.add(modelSound15);
        modelSounds.add(modelSound16);
        modelSounds.add(modelSound17);
        modelSounds.add(modelSound18);
        modelSounds.add(modelSound19);
        modelSounds.add(modelSound20);
        ModelSound modelSound21 = new ModelSound(131, context.getResources().getString(R.string.sound_name_cafe), "cafe", R.drawable.vector_sound_ic_cafe, 50);
        ModelSound modelSound22 = new ModelSound(136, context.getResources().getString(R.string.sound_name_flute), "flute", R.drawable.vector_sound_ic_flute, 50);
        modelSound21 = new ModelSound(138, context.getResources().getString(R.string.sound_name_harp), "harp", R.drawable.vector_sound_ic_harp, 50);
        ModelSound modelSound23 = new ModelSound(139, context.getResources().getString(R.string.sound_name_jazz), "jazz", R.drawable.vector_sound_ic_jazz, 50);
        ModelSound modelSound24 = new ModelSound(141, context.getResources().getString(R.string.sound_name_melody), "melody", R.drawable.vector_sound_ic_melody, 50);
        modelSound23 = new ModelSound(142, context.getResources().getString(R.string.sound_name_music_box), "music_box", R.drawable.vector_sound_ic_music_box, 50);
        modelSound24 = new ModelSound(143, context.getResources().getString(R.string.sound_name_piano), "meditation_piano", R.drawable.vector_sound_ic_piano, 50);
        modelSound23 = new ModelSound(144, context.getResources().getString(R.string.sound_name_piano_2), "meditation_piano_2", R.drawable.vector_sound_ic_piano, 50);
        modelSound24 = new ModelSound(147, context.getResources().getString(R.string.sound_name_yoga_music), "yoga_music", R.drawable.vector_sound_ic_yoga_music, 50);
        modelSound23 = new ModelSound(135, context.getResources().getString(R.string.sound_name_evening_beach), "evening_beach", R.drawable.vector_sound_ic_evening_beach, 50);
        ModelSound modelSound25 = new ModelSound(145, context.getResources().getString(R.string.sound_name_tide), "tide", R.drawable.vector_sound_ic_tide, 50);
        modelSound9 = new ModelSound(148, context.getResources().getString(R.string.sound_name_zen), "zen", R.drawable.vector_sound_ic_zen, 50);
        modelSound25 = new ModelSound(146, context.getResources().getString(R.string.sound_name_universe), "universe", R.drawable.vector_sound_ic_universe, 50);
        modelSound9 = new ModelSound(140, context.getResources().getString(R.string.sound_name_meditation), "meditation", R.drawable.vector_sound_ic_meditation, 50);
        modelSound25 = new ModelSound(137, context.getResources().getString(R.string.sound_name_focus), "focus", R.drawable.vector_sound_ic_focus, 50);
        ModelSound modelSound26 = new ModelSound(133, context.getResources().getString(R.string.sound_name_dream), "dream", R.drawable.vector_sound_ic_dream, 50);
        ModelSound modelSound27 = new ModelSound(130, context.getResources().getString(R.string.sound_name_astral), "astral", R.drawable.vector_sound_ic_astral, 50);
        ModelSound modelSound28 = new ModelSound(134, context.getResources().getString(R.string.sound_name_dryer), "city_washing_machine", R.drawable.vector_sound_ic_dryer, 50);
        modelSound5 = new ModelSound(132, context.getResources().getString(R.string.sound_name_crowd), "crowd", R.drawable.vector_sound_ic_crowd, 50);
        modelSound7 = new ModelSound(149, context.getResources().getString(R.string.sound_name_thunderstorm), "rain_thunders", R.drawable.vector_sound_ic_thunderstorm, 50);
        ModelSound modelSound29 = new ModelSound(150, context.getResources().getString(R.string.sound_name_rain_on_car_window), "rain_on_car_windows", R.drawable.vector_sound_ic_rain_on_car_window, 50);
        modelSound11 = new ModelSound(151, context.getResources().getString(R.string.sound_name_rain_in_city), "rain_in_city", R.drawable.vector_sound_ic_rain_in_city, 50);
        modelSound13 = new ModelSound(152, context.getResources().getString(R.string.sound_name_rain_on_car_roof), "rain_on_car_roof", R.drawable.vector_sound_ic_rain_on_car_roof, 50);
        modelSound15 = new ModelSound(153, context.getResources().getString(R.string.sound_name_pink_noise), "meditation_pink_noise", R.drawable.vector_sound_ic_pink_noise, 50);
        modelSound6 = new ModelSound(154, context.getResources().getString(R.string.sound_name_brown_noise), "meditation_brown_noise", R.drawable.vector_sound_ic_brown_noise, 50);
        modelSound17 = new ModelSound(155, context.getResources().getString(R.string.sound_name_wolf), "wolf", R.drawable.vector_sound_ic_wolf, 50);
        modelSound19 = new ModelSound(156, context.getResources().getString(R.string.sound_name_cat_purring), "cat_purring", R.drawable.vector_sound_ic_cat_purring, 50);
        modelSound14 = new ModelSound(157, context.getResources().getString(R.string.sound_name_whales), "whales", R.drawable.vector_sound_ic_whales, 50);
        ModelSound modelSound30 = new ModelSound(158, context.getResources().getString(R.string.sound_name_deep_sea), "deep_sea", R.drawable.vector_sound_ic_deep_sea, 50);
        ModelSound modelSound31 = new ModelSound(159, context.getResources().getString(R.string.sound_name_womb), "womb", R.drawable.vector_sound_ic_womb, 50);
        ModelSound modelSound32 = new ModelSound(160, context.getResources().getString(R.string.sound_name_brahms_lullaby), "brahms_lullaby", R.drawable.vector_sound_ic_brahms_lullaby, 50);
        modelSound16 = new ModelSound(161, "Test Sound", "brahms_lullaby", R.drawable.vector_sound_ic_brahms_lullaby, 50);
        modelSounds.add(modelSound21);
        modelSounds.add(modelSound22);
        modelSounds.add(modelSound21);
        modelSounds.add(modelSound23);
        modelSounds.add(modelSound24);
        modelSounds.add(modelSound23);
        modelSounds.add(modelSound24);
        modelSounds.add(modelSound23);
        modelSounds.add(modelSound24);
        modelSounds.add(modelSound23);
        modelSounds.add(modelSound25);
        modelSounds.add(modelSound9);
        modelSounds.add(modelSound25);
        modelSounds.add(modelSound9);
        modelSounds.add(modelSound25);
        modelSounds.add(modelSound26);
        modelSounds.add(modelSound27);
        modelSounds.add(modelSound28);
        modelSounds.add(modelSound5);
        modelSounds.add(modelSound7);
        modelSounds.add(modelSound29);
        modelSounds.add(modelSound11);
        modelSounds.add(modelSound13);
        modelSounds.add(modelSound15);
        modelSounds.add(modelSound6);
        modelSounds.add(modelSound17);
        modelSounds.add(modelSound19);
        modelSounds.add(modelSound14);
        modelSounds.add(modelSound30);
        modelSounds.add(modelSound31);
        modelSounds.add(modelSound32);
        modelSounds.add(modelSound16);
        for (ModelSound modelSound33 : modelSounds) {
            soundItemSparseArray.put(modelSound33.getmSoundId(), modelSound33);
        }
    }

    public static List<ModelMix> getListMixItem(Context context) {
        ArrayList arrayList = new ArrayList();
        List asList = ArrayUtils.asList(mixModelSparseArray);
        Collections.sort(asList, new Comparator<ModelMix>() {
            public int compare(ModelMix modelMix, ModelMix modelMix2) {
                return modelMix.getmMixSoundId() - modelMix2.getmMixSoundId();
            }
        });
        return asList;
    }

    public static List<ModelSound> getListSoundItem(Context context) {
        return ArrayUtils.asList(soundItemSparseArray);
    }

    public static List<ModelMixCover> getListMixCoverItem(Context context) {
        ArrayList arrayList = new ArrayList();
        return ArrayUtils.asList(spareArray);
    }

    public static List<ModelSetting> getListSettingItem(Context context) {
        return new ArrayList();
    }

    public static List<ModelMixCategory> getListMixCategoryItem(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ModelMixCategory(0, "All"));
        List customMixList = HelperSaveData.getCustomMixList(context);
        String str = "Work";
        String str2 = "Mediation";
        String str3 = "Relax";
        String str4 = "Rain";
        String str5 = "Sleep";
        if (customMixList == null || customMixList.size() <= 0) {
            arrayList.add(new ModelMixCategory(1, str5));
            arrayList.add(new ModelMixCategory(2, str4));
            arrayList.add(new ModelMixCategory(3, str3));
            arrayList.add(new ModelMixCategory(4, str2));
            arrayList.add(new ModelMixCategory(5, str));
        } else {
            arrayList.add(new ModelMixCategory(1, "Custom"));
            arrayList.add(new ModelMixCategory(2, str5));
            arrayList.add(new ModelMixCategory(3, str4));
            arrayList.add(new ModelMixCategory(4, str3));
            arrayList.add(new ModelMixCategory(5, str2));
            arrayList.add(new ModelMixCategory(6, str));
        }
        ((ModelMixCategory) arrayList.get(0)).setOk(true);
        return arrayList;
    }

    public static List<ModelMixCover> getCustomCoverList(Context context) {
        ArrayList arrayList = new ArrayList(8);
        arrayList.add(new ModelMixCover(0, R.drawable.custom_cover_1, R.drawable.big_custom_cover_1));
        arrayList.add(new ModelMixCover(1, R.drawable.custom_cover_2, R.drawable.big_custom_cover_2));
        arrayList.add(new ModelMixCover(2, R.drawable.custom_cover_3, R.drawable.big_custom_cover_3));
        arrayList.add(new ModelMixCover(3, R.drawable.custom_cover_4, R.drawable.big_custom_cover_4));
        arrayList.add(new ModelMixCover(4, R.drawable.custom_cover_5, R.drawable.big_custom_cover_5));
        arrayList.add(new ModelMixCover(5, R.drawable.custom_cover_6, R.drawable.big_custom_cover_6));
        arrayList.add(new ModelMixCover(6, R.drawable.custom_cover_7, R.drawable.big_custom_cover_7));
        arrayList.add(new ModelMixCover(7, R.drawable.custom_cover_8, R.drawable.big_custom_cover_8));
        return arrayList;
    }

    public static ModelSound getSoundItemById(int i) {
        return soundItemSparseArray.get(i);
    }

    public static ModelMix getMixItemById(int i) {
        return mixModelSparseArray.get(i);
    }

    public static ModelMixCover getMixCoverItemById(int i) {
        return spareArray.get(i);
    }
}
