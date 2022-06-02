package com.arapps.sleepsound.relaxandsleep.naturesounds.services;

import com.arapps.sleepsound.relaxandsleep.naturesounds.model.ModelSound;

interface IPlayerSoundManager {
    void pause(ModelSound modelSound);

    void pauseAllPlayer();

    void play(ModelSound modelSound);

    void playAllPlayer();

    void release(ModelSound modelSound);

    void removeAllPlayer();

    void resume(ModelSound modelSound);

    void resumeAllPlayer();

    void setVolume(ModelSound modelSound, float f);

    void stop(ModelSound modelSound);

    void stopAllPlayer();
}
