package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;

interface IPlayerSoundManager {
    void pause(SoundModel soundModel);

    void pauseAllPlayer();

    void play(SoundModel soundModel);

    void playAllPlayer();

    void release(SoundModel soundModel);

    void removeAllPlayer();

    void resume(SoundModel soundModel);

    void resumeAllPlayer();

    void setVolume(SoundModel soundModel, float f);

    void stop(SoundModel soundModel);

    void stopAllPlayer();
}
