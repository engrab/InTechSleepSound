package com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.apphubstudio.sleepingmusic.rainsounds.relaxmusic.whitenoises.model.ModelMix;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HelperSaveData {
    private static final String LIST_MIX = "List_mix_custom";
    private static final String PREFS_TAG = "MyCustomPrefs";

    public static List<ModelMix> getCustomMixList(Context context) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        return (List) gson.fromJson(context.getSharedPreferences(PREFS_TAG, 0).getString(LIST_MIX, ""), new TypeToken<List<ModelMix>>() {
        }.getType());
    }

    public static void addCustomMixInJSONArray(Context context, ModelMix modelMix) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, 0);
        String str = LIST_MIX;
        String string = sharedPreferences.getString(str, "");
        String toJson = gson.toJson((Object) modelMix);
        JSONArray jSONArray = new JSONArray();
        try {
            if (string.length() != 0) {
                jSONArray = new JSONArray(string);
            }
            jSONArray.put(new JSONObject(toJson));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(str, jSONArray.toString());
        edit.commit();
    }

    public static void removeCustomMixInJSONArray(Context context, ModelMix modelMix) {
        Gson gson = new Gson();
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, 0);
        String str = LIST_MIX;
        String string = sharedPreferences.getString(str, "");
        gson.toJson((Object) modelMix);
        JSONArray jSONArray = new JSONArray();
        try {
            if (string.length() != 0) {
                jSONArray = new JSONArray(string);
            }
            while (i < jSONArray.length()) {
                if (((ModelMix) gson.fromJson(jSONArray.get(i).toString(), ModelMix.class)).getmMixSoundId() == modelMix.getmMixSoundId()) {
                    jSONArray.remove(i);
                }
                i++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Editor edit = sharedPreferences.edit();
        edit.putString(str, jSONArray.toString());
        edit.commit();
    }
}
