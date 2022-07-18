package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.services;

import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelSound;

public interface IPlayerSound {
    void init(ModelSound modelSound);

    void pause();

    void play();

    void release();

    void resume();

    void setVolume(float f);

    void stop();
}
