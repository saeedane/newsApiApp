package com.example.recipeappapi.fragments;

import com.example.recipeappapi.R;
import com.github.machinarius.preferencefragment.PreferenceFragment;

public class FragmentSettings  extends PreferenceFragment {
    @Override
    public void addPreferencesFromResource(int preferencesResId) {
        super.addPreferencesFromResource(R.xml.setting_pref);
    }
}
