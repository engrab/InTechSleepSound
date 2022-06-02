package com.arapps.sleepsound.relaxandsleep.naturesounds.utils.timer;

import android.os.Handler;
import android.os.Message;

public abstract class Hourglass implements HourglassListener {
    private static final int INTERVAL = 1000;
    private static final int MSG = 1;
    private Handler handler;
    private long interval;
    private boolean isPaused;
    private boolean isRunning;
    private long localTime;
    private long time;

    public Hourglass() {
        init(0, 1000);
    }

    public Hourglass(long j) {
        init(j, 1000);
    }

    public Hourglass(long j, long j2) {
        init(j, j2);
    }

    private void init(long j, long j2) {
        setTime(j);
        setInterval(j2);
        initHourglass();
    }

    private void initHourglass() {
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 1 && !Hourglass.this.isPaused) {
                    if (Hourglass.this.localTime <= Hourglass.this.time) {
                        Hourglass hourglass = Hourglass.this;
                        hourglass.onTimerTick(hourglass.time - Hourglass.this.localTime);
                        hourglass = Hourglass.this;
                        hourglass.localTime = hourglass.localTime + Hourglass.this.interval;
                        sendMessageDelayed(Hourglass.this.handler.obtainMessage(1), Hourglass.this.interval);
                        return;
                    }
                    Hourglass.this.stopTimer();
                }
            }
        };
    }

    public boolean isRunning() {
        return this.isRunning;
    }

    public void startTimer() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.isPaused = false;
            this.localTime = 0;
            Handler handler = this.handler;
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    public void stopTimer() {
        this.isRunning = false;
        this.handler.removeMessages(1);
        onTimerFinish();
    }

    public void stopTimerWithNoAction() {
        this.isRunning = false;
        this.handler.removeMessages(1);
    }

    public synchronized boolean isPaused() {
        return this.isPaused;
    }

    private synchronized void setPaused(boolean z) {
        this.isPaused = z;
    }

    public synchronized void pauseTimer() {
        setPaused(true);
    }

    public synchronized void resumeTimer() {
        setPaused(false);
        this.handler.sendMessage(this.handler.obtainMessage(1));
    }

    public void setTime(long j) {
        if (!this.isRunning) {
            if (this.time <= 0 && j < 0) {
                j *= -1;
            }
            this.time = j;
        }
    }

    public long getRemainingTime() {
        return this.isRunning ? this.time : 0;
    }

    public void setInterval(long j) {
        if (!this.isRunning) {
            if (this.interval <= 0 && j < 0) {
                j *= -1;
            }
            this.interval = j;
        }
    }
}
