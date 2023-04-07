package com.example.unihelp;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class PreferenceFragment extends PreferenceFragmentCompat{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        PreferenceManager manager = getPreferenceManager();
        manager.setSharedPreferencesName("MyPrefs");
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference_screen);
    }
}