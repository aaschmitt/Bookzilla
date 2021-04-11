package com.example.bookzilla;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateNewUserProfileActivity extends AppCompatActivity {
    private int toastDuration = 2;
    private final String userFileName = "users.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user_profile);
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

    /* Creates a new userprofile with the inputted text -- sets current user to the newly created userprofile */
    public void onCreateNewUserProfileClick(View view) {
        // Check to see if entered values are null or equal to NAME
        EditText nameInput = (EditText) findViewById(R.id.nameTextInput);
        EditText usernameInput = (EditText) findViewById(R.id.usernameTextInput);

        if (nameInput.getText().length() == 0 || usernameInput.getText().length() == 0) {
            Toast toast = Toast.makeText(this, "Please enter both a Name and Username", toastDuration);
            toast.show();
            return;
        }
        else {
            // Create new userprofile & change to be current profile
            UserProfile newProfile = CreateNewUser(usernameInput.getText().toString(), nameInput.getText().toString());
            if (newProfile != null) {
                CurrentUserProfile.profile = newProfile;
                UpdateCurrentUserTextView();
                UserProfileDataWriter.WriteCurrentUserInfo(this);

                Toast toast = Toast.makeText(this, "Successfully made new user: " + CurrentUserProfile.profile.getUsername(), toastDuration);
                toast.show();
            }
            return;
        }
    }

    private UserProfile CreateNewUser(String username, String name) {
        // Check to see if username is available
        for (UserProfile profile : CurrentUserProfile.allUsers) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                Toast toast = Toast.makeText(this, "Username already in use. Please choose a different Username", toastDuration);
                toast.show();
                return null;
            }
        }

        // Create new user if username available
        UserProfile newUserProfile = new UserProfile(name, username);

        // Add to currentuserprofiles list
        CurrentUserProfile.allUsers.add(newUserProfile);

        return newUserProfile;
    }
}
