package com.example.bookzilla;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseExistingUserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_existing_user_profile);
        UpdateCurrentUserTextView();
    }

    private void UpdateCurrentUserTextView() {
        TextView userProfileText = (TextView) findViewById(R.id.currentUserTextView);
        if (CurrentUserProfile.profile == null) {
            userProfileText.setText("CURRENT USER: NULL");
        }
        else {
            userProfileText.setText("CURRENT USER: " + CurrentUserProfile.profile.getName());
        }
    }

    /* Method to create a new userprofile (goes to CreateUserProfileActivity)*/
    public void onCreateNewUserProfileClick(View view) {
        Intent intent = new Intent(this, CreateNewUserProfileActivity.class);
        startActivity(intent);
    }


}
