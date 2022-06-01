package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;

public interface IPlayerSound {
    void init(SoundModel soundModel);

    void pause();

    void play();

    void release();

    void resume();

    void setVolume(float f);

    void stop();
}
