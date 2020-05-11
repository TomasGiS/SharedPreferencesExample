package com.tomasgis.android.sharedpreferences;

public class Config {

    enum AppState {TUTORIAL_DONE, TUTORIAL_PENDING};

    //SharedPreferences
    public static final String INITIAL_STATE_APP_PREFERENCES = "INITIAL_STATE_APP_PREFERENCES";

    //KEYS
    public static final String KEY_INITIAL_STATE_APP = "KEY_INITIAL_STATE_APP";
    //DEFAULT VALUES
    public static final AppState DEFAULT_INITIAL_APP_STATE= AppState.TUTORIAL_PENDING;

}
