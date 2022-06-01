package com.arapps.sleepsound.relaxandsleep.naturesounds.Services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Builder;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.arapps.sleepsound.relaxandsleep.naturesounds.R;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.SoundListDataSource;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.SoundModel;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.TimeUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.timer.Hourglass;

public class SoundPlayerService extends Service {
    public static final String ACTION_CMD = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_CMD";
    public static final String ACTION_OPEN_FROM_NOTIFI = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_OPEN_FROM_NOTIFI";
    public static final String ACTION_UPDATE_BED = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_UPDATE_BED";
    public static final String ACTION_UPDATE_PLAY_STATE = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_UPDATE_PLAY_STATE";
    public static final String ACTION_UPDATE_SOUND_SELECTED = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_UPDATE_SOUND_SELECTED";
    public static final String ACTION_UPDATE_TIME = "com.arapps.sleepsound.relaxandsleep.naturesounds.ACTION_UPDATE_TIME";
    public static final String CMD_NAME = "CMD_NAME";
    public static final String CMD_NOTIFICATION_CLOSE = "CMD_NOTIFICATION_CLOSE";
    public static final String CMD_NOTIFICATION_PLAY = "CMD_NOTIFICATION_PLAY";
    public static final String CMD_PAUSE = "CMD_PAUSE";
    public static final String CMD_PAUSE_ALL = "CMD_PAUSE_ALL";
    public static final String CMD_PLAY = "CMD_PLAY";
    public static final String CMD_PLAY_ALL = "CMD_PLAY_ALL";
    public static final String CMD_PLAY_MIX = "CMD_PLAY_MIX";
    public static final String CMD_RELEASE = "CMD_RELEASE";
    public static final String CMD_RELEASE_ALL = "CMD_RELEASE_ALL";
    public static final String CMD_RESUME = "CMD_RESUME";
    public static final String CMD_RESUME_ALL = "CMD_RESUME_ALL";
    public static final String CMD_STOP = "CMD_STOP";
    public static final String CMD_STOP_ALL = "CMD_STOP_ALL";
    public static final String CMD_UPDATE_TIME = "CMD_UPDATE_TIME";
    public static final String COVERT_ID = "COVERT_ID";
    public static final String EXTRA_CONNECTED_CAST = "CAST_NAME";
    public static final String MIX_ID = "MIX_ID";
    private static final int REQ_TIMER = 111;
    public static final String RESULT_CODE = "RESULT_CODE";
    public static final int RESULT_ERROR = -1;
    public static final int RESULT_EXISTED = 2;
    public static final String RESULT_MESSAGE = "RESULT_MESSAGE";
    public static final int RESULT_OK = 1;
    public static final String SOUND_ID = "SOUND_ID";
    private static final int STOP_DELAY = 3600000;
    public static final String UPDATE_PLAY_STATE = "com.arapps.sleepsound.relaxandsleep.naturesounds.UPDATE_PLAY_STATE";
    public static final String UPDATE_SOUND_SELECTED = "com.arapps.sleepsound.relaxandsleep.naturesounds.UPDATE_SOUND_SELECTED";
    public static final String UPDATE_TIME = "com.arapps.sleepsound.relaxandsleep.naturesounds.UPDATE_TIME";
    private Hourglass countDownTimer;
    private Handler handler = new Handler();
    private boolean isEndTime = false;
    private SoundPlayerManager soundPlayerManager;
    private SoundPlayerServiceBinder soundPlayerServiceBinder = new SoundPlayerServiceBinder();
    private long timeToStopSound = 0;
    private long timeToStopSound1 = 0;

    public class SoundPlayerServiceBinder extends Binder {
        public SoundPlayerService getService() {
            return SoundPlayerService.this;
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public SoundPlayerManager getSoundPlayerManager() {
        return this.soundPlayerManager;
    }

    public long getTimeToStopSound() {
        return this.timeToStopSound1;
    }

    public void onCreate() {
        super.onCreate();
        this.soundPlayerManager = SoundPlayerManager.getInstance(this);
        Log.e("SERVICE", "START");
        String str = Constant.KEY_PLAY_TIME;
        this.timeToStopSound = SharedPrefsUtils.getLongPreference(this, str, 1140000);
        this.timeToStopSound1 = SharedPrefsUtils.getLongPreference(this, str, 1140000);
    }

    private void updateTime(long j) {
        Intent intent = new Intent(ACTION_UPDATE_TIME);
        intent.putExtra(RESULT_CODE, 1);
        intent.putExtra(UPDATE_TIME, j);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        NotificationHandler.buildNotification(this);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        startForeground(NotificationHandler.notificationID, new Builder(this, VERSION.SDK_INT >= 26 ? createNotificationChannel((NotificationManager) getSystemService("notification")) : "").setOngoing(true).setSmallIcon(R.drawable.notification_icon).setPriority(-2).setCategory(NotificationCompat.CATEGORY_SERVICE).build());
        if (!(intent == null || intent.getAction() == null)) {
            String action = intent.getAction();
            String stringExtra = intent.getStringExtra(CMD_NAME);
            if ("ACTION_CMD_1".equals(action)) {
                handlerPauseAll(null);
                Hourglass hourglass = this.countDownTimer;
                if (hourglass != null) {
                    hourglass.pauseTimer();
                }
                NotificationHandler.removeNotification(getApplicationContext());
                stopForeground(true);
                onDestroy();
            }
            if (ACTION_CMD.equals(action)) {
                int obj = -1;
                int hashCode = stringExtra.hashCode();
                String str = CMD_NOTIFICATION_PLAY;
                switch (hashCode) {
                    case -1835509959:
                        if (stringExtra.equals(CMD_PLAY)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -1835412473:
                        if (stringExtra.equals(CMD_STOP)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case -1066542479:
                        if (stringExtra.equals(CMD_PAUSE)) {
                            obj = 0;
                            break;
                        }
                        break;
                    case -933927245:
                        if (stringExtra.equals(CMD_PAUSE_ALL)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case -864311870:
                        if (stringExtra.equals(CMD_RELEASE)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case 163903710:
                        if (stringExtra.equals(CMD_UPDATE_TIME)) {
                            obj = 11;
                            break;
                        }
                        break;
                    case 507970761:
                        if (stringExtra.equals(CMD_NOTIFICATION_CLOSE)) {
                            obj = 12;
                            break;
                        }
                        break;
                    case 575407796:
                        if (stringExtra.equals(CMD_RESUME_ALL)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case 709509667:
                        if (stringExtra.equals(str)) {
                            obj = 13;
                            break;
                        }
                        break;
                    case 922427780:
                        if (stringExtra.equals(CMD_RELEASE_ALL)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 1243522377:
                        if (stringExtra.equals(CMD_STOP_ALL)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case 1357816562:
                        if (stringExtra.equals(CMD_RESUME)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 1407467387:
                        if (stringExtra.equals(CMD_PLAY_ALL)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case 1407478838:
                        if (stringExtra.equals(CMD_PLAY_MIX)) {
                            obj = 10;
                            break;
                        }
                        break;
                }
                Hourglass hourglass2;
                switch (obj) {
                    case 0:
                        handlerPause(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 1:
                        handlerPauseAll(intent);
                        hourglass2 = this.countDownTimer;
                        if (hourglass2 != null) {
                            hourglass2.pauseTimer();
                        }
                        NotificationHandler.buildNotification(this);
                        break;
                    case 2:
                        handlerPlay(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 3:
                        handlerPlayAll(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 4:
                        handlerRelease(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 5:
                        handlerReleaseAll(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 6:
                        handlerResume(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 7:
                        handlerResumeAll(intent);
                        hourglass2 = this.countDownTimer;
                        if (hourglass2 != null) {
                            if (this.isEndTime) {
                                hourglass2.startTimer();
                                this.isEndTime = false;
                            } else {
                                hourglass2.resumeTimer();
                            }
                        }
                        NotificationHandler.buildNotification(this);
                        break;
                    case 8:
                        handlerStop(intent);
                        break;
                    case 9:
                        handlerStopAll(intent);
                        break;
                    case 10:
                        handlerPlayMix(intent);
                        NotificationHandler.buildNotification(this);
                        break;
                    case 11:
                        this.timeToStopSound = SharedPrefsUtils.getLongPreference(this, Constant.KEY_PLAY_TIME, 1140000);
                        handlerUpdateTime(intent);
                        break;
                    case 12:
                        handlerPauseAll(intent);
                        hourglass2 = this.countDownTimer;
                        if (hourglass2 != null) {
                            hourglass2.pauseTimer();
                        }
                        NotificationHandler.removeNotification(getApplicationContext());
                        stopForeground(true);
                        onDestroy();
                        break;
                    case 13:
                        if (this.soundPlayerManager.getPlayerState() == 1) {
                            handlerPauseAll(intent);
                            hourglass2 = this.countDownTimer;
                            if (hourglass2 != null) {
                                hourglass2.pauseTimer();
                            }
                        } else {
                            handlerResumeAll(intent);
                            hourglass2 = this.countDownTimer;
                            if (hourglass2 != null) {
                                if (this.isEndTime) {
                                    hourglass2.startTimer();
                                    this.isEndTime = false;
                                } else {
                                    hourglass2.resumeTimer();
                                }
                            }
                        }
                        NotificationHandler.buildNotification(this);
                        Log.e(str, str);
                        break;
                }
            }
        }
        return START_NOT_STICKY;
    }

    private void handlerUpdateTime(Intent intent) {
        Hourglass hourglass = this.countDownTimer;
        if (hourglass != null) {
            hourglass.stopTimerWithNoAction();
        }
        if (SharedPrefsUtils.getLongPreference(this, Constant.KEY_PLAY_TIME, 1140000) > 0) {
            this.countDownTimer = new Hourglass(this.timeToStopSound, 1000) {
                public void onTimerTick(long j) {
                    SoundPlayerService.this.updateTime(j);
                    SoundPlayerService.this.timeToStopSound1 = j;
                    Log.e("TICK", TimeUtils.convertMillisecondsToString(j));
                }

                public void onTimerFinish() {
                    String str = "PAUSE_PLAY_COUNT";
                    if (SoundPlayerService.this.soundPlayerManager.getPlayerState() == 1) {
                        SoundPlayerService.this.handlerPauseAll(new Intent());
                        SoundPlayerService soundPlayerService = SoundPlayerService.this;
                        soundPlayerService.updateTime(soundPlayerService.timeToStopSound);
                        SoundPlayerService.this.countDownTimer.setTime(SoundPlayerService.this.timeToStopSound);
                        SoundPlayerService.this.isEndTime = true;
                        Log.e(str, "TRUE");
                        return;
                    }
                    Log.e(str, "FALSE");
                }
            };
            String str = "START_COUNT";
            if (this.soundPlayerManager.getPlayerState() == 1) {
                this.countDownTimer.startTimer();
                this.isEndTime = false;
                Log.e(str, "TRUE");
                return;
            }
            Log.e(str, "FALSE");
            return;
        }
        updateTime(-1);
    }

    private void handlerPlayMix(Intent intent) {
        MixModel mixModelById = SoundListDataSource.getMixItemById(intent.getIntExtra(MIX_ID, -1));
        MixModel mixModel = this.soundPlayerManager.getMixItem();
        if (mixModelById == null || mixModel == null || mixModelById.getMixSoundId() != mixModel.getMixSoundId()) {
            handlerReleaseAll(intent);
            this.soundPlayerManager.setMixItem(mixModelById);
            for (SoundModel soundModel : mixModelById.getSoundList()) {
                if (soundModel != null) {
                    this.soundPlayerManager.play(soundModel);
                }
            }
            handlerUpdateTime(intent);
        } else {
            handlerResumeAll(new Intent());
            Log.e("PLAY", "SAME_MIX");
        }
        updatePlayerState();
    }

    private void handlerPause(Intent intent) {
        SoundModel soundModelById = SoundListDataSource.getSoundItemById(intent.getIntExtra(SOUND_ID, -1));
        if (soundModelById != null) {
            this.soundPlayerManager.pause(soundModelById);
        }
    }

    private void handlerPauseAll(Intent intent) {
        this.soundPlayerManager.pauseAllPlayer();
        updatePlayerState();
    }

    private void handlerPlay(Intent intent) {
        SoundModel soundModelById = SoundListDataSource.getSoundItemById(intent.getIntExtra(SOUND_ID, -1));
        if (soundModelById != null) {
            this.soundPlayerManager.play(soundModelById);
        }
        updateSoundSelect();
    }

    private void handlerPlayAll(Intent intent) {
        this.soundPlayerManager.playAllPlayer();
        updatePlayerState();
    }

    private void handlerRelease(Intent intent) {
        SoundModel soundModelById = SoundListDataSource.getSoundItemById(intent.getIntExtra(SOUND_ID, -1));
        if (soundModelById != null) {
            this.soundPlayerManager.release(soundModelById);
        }
    }

    private void handlerReleaseAll(Intent intent) {
        this.soundPlayerManager.removeAllPlayer();
    }

    private void handlerResume(Intent intent) {
        SoundModel soundModelById = SoundListDataSource.getSoundItemById(intent.getIntExtra(SOUND_ID, -1));
        if (soundModelById != null) {
            this.soundPlayerManager.resume(soundModelById);
        }
    }

    private void handlerResumeAll(Intent intent) {
        this.soundPlayerManager.resumeAllPlayer();
        updatePlayerState();
    }

    private void handlerStop(Intent intent) {
        SoundModel soundModelById = SoundListDataSource.getSoundItemById(intent.getIntExtra(SOUND_ID, -1));
        if (soundModelById != null) {
            this.soundPlayerManager.stop(soundModelById);
        }
    }

    private void handlerStopAll(Intent intent) {
        this.soundPlayerManager.stopAllPlayer();
        this.countDownTimer.stopTimer();
        updatePlayerState();
    }

    public void onDestroy() {
        super.onDestroy();
        Log.e("SERVICE", "STOP");
    }

    private String createNotificationChannel(NotificationManager notificationManager) {
        String str = "com.arapps.sleepsound.relaxandsleep.naturesounds.notification";
        NotificationChannel notificationChannel = null;
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(str, "NotificationService", 4);
        }
        if (notificationChannel != null) {
            if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel.setImportance(NotificationManager.IMPORTANCE_NONE);
            }
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel.setLockscreenVisibility(0);
        }
        if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return str;
    }

    private void updateSoundSelect() {
        Intent intent = new Intent(ACTION_UPDATE_SOUND_SELECTED);
        intent.putExtra(RESULT_CODE, 1);
        intent.putExtra(UPDATE_SOUND_SELECTED, this.soundPlayerManager.getSizePlayer());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE_SOUND_SELECTED ");
        stringBuilder.append(this.soundPlayerManager.getSizePlayer());
        Log.e("SEND_BROADCAST", stringBuilder.toString());
    }

    private void updatePlayerState() {
        Intent intent = new Intent(ACTION_UPDATE_PLAY_STATE);
        intent.putExtra(RESULT_CODE, 1);
        intent.putExtra(UPDATE_PLAY_STATE, this.soundPlayerManager.getPlayerState());
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE_PLAY_STATE ");
        stringBuilder.append(this.soundPlayerManager.getPlayerState());
        Log.e("SEND_BROADCAST", stringBuilder.toString());
    }
}
