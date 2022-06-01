package com.arapps.sleepsound.relaxandsleep.naturesounds.Receiver;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Helper.Constant;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.ArrayUtils;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Utils.SharedPrefsUtils;

public class RemindService extends JobService {
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        String str = "onStartJob";
        Log.e(str, str);
        long j = jobParameters.getExtras().getLong(Constant.EXTRA_TIME_REMIND);
        boolean booleanPreference = SharedPrefsUtils.getBooleanPreference(this, Constant.KEY_ALARM_STATE, false);
        boolean isDayRemind = ArrayUtils.isDayRemind(this);
        if (Math.abs(j - System.currentTimeMillis()) < 600000 && booleanPreference && isDayRemind) {
            AlarmUtils.createBedNotification(this, j);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("FAIL ");
            stringBuilder.append(booleanPreference);
            stringBuilder.append(" / ");
            stringBuilder.append(Math.abs(j - System.currentTimeMillis()));
            Log.e("FAIL", stringBuilder.toString());
        }
        if (booleanPreference) {
            AlarmUtils.nextAlarm(this);
        }
        jobFinished(jobParameters, false);
        return false;
    }
}
