package com.example.bookzilla;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ChooseExistingUserProfileActivity extends AppCompatActivity {
    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_existing_user_profile);

        UpdateCurrentUserTextView();
        CreateButtons();
        UpdateListOfAvailableUsers();
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

    private void CreateButtons() {
        buttons = new Button[CurrentUserProfile.allUsers.size()];
        for (int i = 0; i < buttons.length; i ++) {
            Button button = new Button(this);
            button.setId(i);
            UserProfile currentProfile = CurrentUserProfile.allUsers.get(i);
            button.setText(currentProfile.getUsername() + " " + currentProfile.getName());
            button.setOnClickListener( new View.OnClickListener() {
               public void onClick(View view) {
                   CurrentUserProfile.profile = CurrentUserProfile.allUsers.get(view.getId());
                   UpdateCurrentUserTextView();
               }
            });
            buttons[i] = button;
        }
    }

    private void UpdateListOfAvailableUsers() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        for (int i = 0; i < buttons.length; i ++) {
            layout.addView(buttons[i]);
        }
    }

    public void onDeleteCurrentUserProfileClick(View view) {
        UserProfileDataWriter.DeleteCurrentUserInfo(this);
    }

}
