package com.arapps.sleepsound.relaxandsleep.naturesounds.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.arapps.sleepsound.relaxandsleep.naturesounds.Model.MixModel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SaveDataHelper {
    private static final String LIST_MIX = "List_mix_custom";
    private static final String LIST_MIX_PREMIUM = "List_mix_premium";
    private static final String LIST_SOUND_PREMIUM = "List_sound_premium";
    private static final String PREFS_TAG = "MyCustomPrefs";

    public static List<MixModel> getCustomMixList(Context context) {
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        return (List) gson.fromJson(context.getSharedPreferences(PREFS_TAG, 0).getString(LIST_MIX, ""), new TypeToken<List<MixModel>>() {
        }.getType());
    }

    public static void addCustomMixInJSONArray(Context context, MixModel mixModel) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, 0);
        String str = LIST_MIX;
        String string = sharedPreferences.getString(str, "");
        String toJson = gson.toJson((Object) mixModel);
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

    public static void removeCustomMixInJSONArray(Context context, MixModel mixModel) {
        Gson gson = new Gson();
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_TAG, 0);
        String str = LIST_MIX;
        String string = sharedPreferences.getString(str, "");
        gson.toJson((Object) mixModel);
        JSONArray jSONArray = new JSONArray();
        try {
            if (string.length() != 0) {
                jSONArray = new JSONArray(string);
            }
            while (i < jSONArray.length()) {
                if (((MixModel) gson.fromJson(jSONArray.get(i).toString(), MixModel.class)).getMixSoundId() == mixModel.getMixSoundId()) {
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
