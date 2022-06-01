package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SoundPlayerManager implements IPlayerSoundManager {
    public static SparseArray<SoundModel> soundItemSparseArray = new SparseArray();
    static SoundPlayerManager soundPlayerManager;
    public static SparseArray<SoundPlayer> sparseArray = new SparseArray();
    Context context;
    public MixModel mixModel;
    int playerState = -1;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPlayerState(int i) {
        this.playerState = i;
    }

    public Context getContext() {
        return this.context;
    }

    public SoundPlayerManager(Context context) {
        this.context = context;
    }

    public static SoundPlayerManager getInstance(Context context) {
//        SoundPlayerManager soundPlayerManager;
        if (soundPlayerManager != null) {
            return soundPlayerManager;
        }
        soundPlayerManager = new SoundPlayerManager(context);
        return soundPlayerManager;
    }

    public MixModel getMixItem() {
        return this.mixModel;
    }

    public void setMixItem(MixModel mixModel) {
        this.mixModel = mixModel;
    }

    public void play(SoundModel soundModel) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            String str = "";
            if (((SoundPlayer) sparseArray.get(soundModel.getSoundId())) != null) {
                release(soundModel);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(sparseArray.size());
                stringBuilder.append(str);
                Log.e("release ", stringBuilder.toString());
                if (soundPlayerManager.getSizePlayer() == 0) {
                    this.mixModel = null;
                    Intent intent = new Intent(SoundPlayerService.ACTION_UPDATE_PLAY_STATE);
                    intent.putExtra(SoundPlayerService.RESULT_CODE, 1);
                    intent.putExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                    LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                    Log.e("SEND_BROADCAST", "UPDATE_PLAY_STATE 0");
                    intent = new Intent(this.context, SoundPlayerService.class);
                    intent.setAction(SoundPlayerService.ACTION_CMD);
                    intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_NOTIFICATION_CLOSE);
                    this.context.startService(intent);
                }
                return;
            }
            StringBuilder stringBuilder2;
            if (sparseArray.size() < 8) {
                SoundPlayer soundPlayer = new SoundPlayer(this.context, soundModel);
                soundPlayer.play();
                sparseArray.put(soundModel.getSoundId(), soundPlayer);
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(soundModel.getSoundId());
                stringBuilder2.append(str);
                Log.e("SoundPlayer play ", stringBuilder2.toString());
            } else {
                Log.e("MAX_SIZE_PLAYER", "TRUE");
            }
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(sparseArray.size());
            stringBuilder2.append(str);
            Log.e("SIZE_PLAYER ", stringBuilder2.toString());
            setVolume(soundModel, (float) soundModel.getVolume());
        }
    }

    public void resume(SoundModel soundModel) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            SoundPlayer soundPlayer = (SoundPlayer) sparseArray.get(soundModel.getSoundId());
            if (soundPlayer != null) {
                soundPlayer.play();
            } else {
                Log.e("SoundPlayer", "SoundPlayer null");
            }
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SoundPlayer resume ");
            stringBuilder.append(soundModel.getSoundId());
            printStream.println(stringBuilder.toString());
        }
    }

    public void pause(SoundModel soundModel) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            SoundPlayer soundPlayer = (SoundPlayer) sparseArray.get(soundModel.getSoundId());
            if (soundPlayer != null) {
                soundPlayer.pause();
            } else {
                Log.e("SoundPlayer", "SoundPlayer null");
            }
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SoundPlayer pause ");
            stringBuilder.append(soundModel.getSoundId());
            printStream.println(stringBuilder.toString());
        }
    }

    public void stop(SoundModel soundModel) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            SoundPlayer soundPlayer = (SoundPlayer) sparseArray.get(soundModel.getSoundId());
            if (soundPlayer != null) {
                soundPlayer.stop();
            } else {
                Log.e("SoundPlayer", "SoundPlayer null");
            }
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SoundPlayer stop ");
            stringBuilder.append(soundModel.getSoundId());
            printStream.println(stringBuilder.toString());
        }
    }

    public void release(SoundModel soundModel) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            SoundPlayer soundPlayer = (SoundPlayer) sparseArray.get(soundModel.getSoundId());
            if (soundPlayer != null) {
                soundPlayer.release();
                sparseArray.remove(soundModel.getSoundId());
            } else {
                Log.e("SoundPlayer", "SoundPlayer null");
            }
            PrintStream printStream = System.out;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("SoundPlayer release ");
            stringBuilder.append(soundModel.getSoundId());
            printStream.println(stringBuilder.toString());
            if (soundPlayerManager.getSizePlayer() == 0) {
                this.mixModel = null;
                Intent intent = new Intent(SoundPlayerService.ACTION_UPDATE_PLAY_STATE);
                intent.putExtra(SoundPlayerService.RESULT_CODE, 1);
                intent.putExtra(SoundPlayerService.UPDATE_PLAY_STATE, 0);
                LocalBroadcastManager.getInstance(this.context).sendBroadcast(intent);
                Log.e("SEND_BROADCAST", "UPDATE_PLAY_STATE 0");
                intent = new Intent(this.context, SoundPlayerService.class);
                intent.setAction(SoundPlayerService.ACTION_CMD);
                intent.putExtra(SoundPlayerService.CMD_NAME, SoundPlayerService.CMD_NOTIFICATION_CLOSE);
                this.context.startService(intent);
            }
        }
    }

    public void setVolume(SoundModel soundModel, float f) {
//        SparseArray sparseArray = sparseArray;
        if (sparseArray != null) {
            SoundPlayer soundPlayer = (SoundPlayer) sparseArray.get(soundModel.getSoundId());
            if (soundPlayer != null) {
                soundPlayer.setVolume(f);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(f);
                stringBuilder.append("");
                Log.e("CURRENR_VOLUME", stringBuilder.toString());
            } else {
                Log.e("SoundPlayer", "SoundPlayer null");
            }
        }
    }

    public void removeAllPlayer() {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                ((SoundPlayer) sparseArray.valueAt(i)).release();
            }
            sparseArray.clear();
        }
    }

    public void pauseAllPlayer() {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                SoundPlayer soundPlayer = (SoundPlayer) sparseArray.valueAt(i);
                if (soundPlayer != null) {
                    soundPlayer.pause();
                } else {
                    Log.e("SoundPlayer", "SoundPlayer null");
                }
            }
            System.out.println("SoundPlayer pauseAllPlayer");
        }
    }

    public void stopAllPlayer() {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                SoundPlayer soundPlayer = (SoundPlayer) sparseArray.valueAt(i);
                if (soundPlayer != null) {
                    soundPlayer.stop();
                } else {
                    Log.e("SoundPlayer", "SoundPlayer null");
                }
            }
            System.out.println("SoundPlayer stopAllPlayer");
        }
    }

    public void playAllPlayer() {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                SoundPlayer soundPlayer = (SoundPlayer) sparseArray.valueAt(i);
                if (soundPlayer != null) {
                    soundPlayer.play();
                } else {
                    Log.e("SoundPlayer", "SoundPlayer null");
                }
            }
            System.out.println("SoundPlayer playAllPlayer");
        }
    }

    public void resumeAllPlayer() {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                SoundPlayer soundPlayer = (SoundPlayer) sparseArray.valueAt(i);
                if (soundPlayer != null) {
                    soundPlayer.resume();
                } else {
                    Log.e("SoundPlayer", "SoundPlayer null");
                }
            }
            System.out.println("SoundPlayer resumeAllPlayer");
        }
    }

    public boolean isMaxPlayerStart() {
        return sparseArray.size() >= 8;
    }

    public int getSizePlayer() {
        return sparseArray.size();
    }

    public SoundPlayer getSoundPlayerById(int i) {
        return (SoundPlayer) sparseArray.get(i);
    }

    public List<SoundModel> getPlayingSoundItem() {
        ArrayList arrayList = new ArrayList();
        for (SoundPlayer soundItem : ArrayUtils.asList(sparseArray)) {
            arrayList.add(soundItem.getSoundItem());
        }
        return arrayList;
    }

    public int getPlayerState() {
        if (sparseArray.size() > 0) {
            return ((SoundPlayer) sparseArray.valueAt(0)).getPlayerState();
        }
        return 0;
    }

    public void addCustomSound(SoundModel soundModel) {
        if (soundModel != null) {
            soundItemSparseArray.put(soundModel.getSoundId(), soundModel);
        }
    }

    public void addListCustomSound(List<SoundModel> list) {
        if (list != null) {
            for (SoundModel soundModel : list) {
                soundItemSparseArray.put(soundModel.getSoundId(), soundModel);
            }
        }
    }

    public void updateCustomSound(SoundModel soundModel) {
        if (soundModel != null) {
            ((SoundModel) soundItemSparseArray.get(soundModel.getSoundId())).update(soundModel);
            soundItemSparseArray.put(soundModel.getSoundId(), soundModel);
        }
    }

    public void removeCustomSound(int i) {
        soundItemSparseArray.remove(i);
    }

    public List<SoundModel> getAllCustomSound() {
        return ArrayUtils.asList(soundItemSparseArray);
    }

    public void removeAllCustomSound() {
        soundItemSparseArray.clear();
    }
}
