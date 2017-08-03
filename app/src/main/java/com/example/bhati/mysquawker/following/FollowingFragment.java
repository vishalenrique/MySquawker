package com.example.bhati.mysquawker.following;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.bhati.mysquawker.R;

/**
 * Created by Bhati on 8/2/2017.
 */

public class FollowingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.following_preference);
    }
}
