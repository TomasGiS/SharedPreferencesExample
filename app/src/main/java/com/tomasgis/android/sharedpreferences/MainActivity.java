package com.tomasgis.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private class ViewHolder
    {
        private Button pendingButton;
        private Button doneButton;
        private Button clearButton;

        private  TextView currentStateTextView;
        private TextView defaultStateTextView;

        protected void bindViews(final AppCompatActivity activity){
            //Inputs Views
            pendingButton = activity.findViewById(R.id.pending_button);
            doneButton = activity.findViewById(R.id.done_button);
            clearButton = activity.findViewById(R.id.clear_data_button);
            //Output Views
            currentStateTextView = activity.findViewById(R.id.current_state_text_view);
            defaultStateTextView = activity.findViewById(R.id.default_state_text_view);

            pendingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences changePreference = getSharedPreferences(Config.INITIAL_STATE_APP_PREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = changePreference.edit();
                    editor.putString(Config.KEY_INITIAL_STATE_APP,Config.AppState.TUTORIAL_PENDING.toString());
                    editor.apply();

                    updateViews(activity);
                }
            });

            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences changePreference = getSharedPreferences(Config.INITIAL_STATE_APP_PREFERENCES,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = changePreference.edit();
                    editor.putString(Config.KEY_INITIAL_STATE_APP,Config.AppState.TUTORIAL_DONE.toString());
                    editor.apply();

                    updateViews(activity);
                }
            });

            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clearPreferences();
                    updateViews(activity);
                }
            });


        }
        protected void updateViews(AppCompatActivity activity){
            SharedPreferences sharedPreferences = getSharedPreferences(Config.INITIAL_STATE_APP_PREFERENCES, Context.MODE_PRIVATE);
            String storedStatus = sharedPreferences.getString(Config.KEY_INITIAL_STATE_APP,Config.DEFAULT_INITIAL_APP_STATE.toString());

            String currentState = activity.getString(R.string.current_state,storedStatus);
            String defaultState = activity.getString(R.string.default_state,Config.DEFAULT_INITIAL_APP_STATE.toString());

            currentStateTextView.setText(currentState);
            defaultStateTextView.setText(defaultState);
        }
        protected void clearPreferences(){
            SharedPreferences sharedPreferences = getSharedPreferences(Config.INITIAL_STATE_APP_PREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ViewHolder viewHolder = new ViewHolder();

        viewHolder.bindViews(this);

        viewHolder.updateViews(this);


    }
}
