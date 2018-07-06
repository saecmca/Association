package com.association.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Localsorage {

  public static void setLangPref(Context context, String lang, String langcode) {
    SharedPreferences prefMember = context.getSharedPreferences("save_lang", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefMember.edit();
    editor.putString("lang", lang);
    editor.putString("langcode", langcode);
    editor.commit();
  }

  public static String getLang(Context context) {
    SharedPreferences pref = context.getSharedPreferences("save_lang", Context.MODE_PRIVATE);
    return pref.getString("lang", "");
  }

  public static String getLangcode(Context context) {
    SharedPreferences pref = context.getSharedPreferences("save_lang", Context.MODE_PRIVATE);
    return pref.getString("langcode", "");
  }
}
