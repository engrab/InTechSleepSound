package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;

public class SoundPlayer implements IPlayerSound {
    Context context;
    MediaPlayer mediaPlayer;
    int playerState = -1;
    SoundModel soundModel;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaPlayer getMediaPlayer() {
        return this.mediaPlayer;
    }

    public Context getContext() {
        return this.context;
    }

    public SoundPlayer(Context context, SoundModel soundModel) {
        this.soundModel = soundModel;
        this.context = context;
        loadMusic();
    }

    public SoundModel getSoundItem() {
        return this.soundModel;
    }

    public void setSoundItem(SoundModel soundModel) {
        this.soundModel = soundModel;
    }

    public int getPlayerState() {
        return this.playerState;
    }

    public void setPlayerState(int i) {
        this.playerState = i;
    }

    private void loadMusic() {
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setAudioStreamType(3);
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Constant.SOURCE_SOUND_PATH);
            stringBuilder.append(this.soundModel.getFileName());
            stringBuilder.append(".ogg");
            AssetFileDescriptor openFd = this.context.getAssets().openFd(stringBuilder.toString());
            this.mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            openFd.close();
            this.mediaPlayer.setLooping(true);
            this.mediaPlayer.prepare();
            float log = (float) (Math.log((double) (100 - this.soundModel.getVolume())) / Math.log(100.0d));
            this.mediaPlayer.setVolume(log, log);
            this.playerState = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
            this.playerState = 1;
        }
    }

    public void resume() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
            this.playerState = 1;
        }
    }

    public void pause() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            this.playerState = 2;
        }
    }

    public void stop() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.playerState = 3;
        }
    }

    public void release() {
        stop();
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            Log.e("RELEASE", "release");
        }
    }

    public void init(SoundModel soundModel) {
        this.soundModel = soundModel;
    }

    public void setVolume(float f) {
        float log = 1.0f - ((float) (Math.log((double) (100.0f - f)) / Math.log(100.0d)));
        this.mediaPlayer.setVolume(log, log);
        this.soundModel.setVolume((int) f);
    }
}
