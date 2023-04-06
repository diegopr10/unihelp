package com.example.unihelp;

import androidx.preference.PreferenceFragmentCompat;
import android.os.Bundle;

public class PreferenceFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference_screen, rootKey);
        // Add any additional preferences programmatically here
    }
}