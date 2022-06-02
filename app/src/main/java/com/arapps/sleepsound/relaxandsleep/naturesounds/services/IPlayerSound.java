package com.arapps.sleepsound.relaxandsleep.naturesounds.services;

import com.arapps.sleepsound.relaxandsleep.naturesounds.model.ModelSound;

public interface IPlayerSound {
    void init(ModelSound modelSound);

    void pause();

    void play();

    void release();

    void resume();

    void setVolume(float f);

    void stop();
}
