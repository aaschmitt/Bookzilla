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
        /*
        else if (UserAlreadyExists(usernameInput.getText().toString())) {
            Toast toast = Toast.makeText(this, "Username is taken -- please try another", toastDuration);
            toast.show();
            return;
        }
         */
        else {
            // Create new userprofile & change to be current profile
            UserProfile newProfile = new UserProfile(nameInput.getText().toString(), usernameInput.getText().toString());
            CurrentUserProfile.profile = newProfile;
            UpdateCurrentUserTextView();
            WriteUserInfoToFile(UserProfileToDataString(CurrentUserProfile.profile));

            /*
            Toast toast = Toast.makeText(this, "Successfully made new user: " + CurrentUserProfile.profile.getUsername(), toastDuration);
            toast.show();
             */
            return;
        }
    }

    /* Writes the new userprofile information to the external database / text file */
    private void WriteUserInfoToFile(String data) {
        try {
            FileOutputStream fileout = openFileOutput(userFileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write(data);
            outputWriter.close();

            Toast toast = Toast.makeText(this, "Successfully wrote to file", toastDuration);
            toast.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Reads the text file to see if the user already exists */
    private boolean UserAlreadyExists(String username) {
        ArrayList<String> usernamesInUse = new ArrayList<String>();
        try {
            DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format(userFileName)));
            Scanner scanner = new Scanner(textFileStream);
            while (scanner.hasNextLine()) {
                if (scanner.next().equals(username)) {
                    return true;
                }
                scanner.nextLine();
            }
            scanner.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private String UserProfileToDataString(UserProfile userProfile) {
        // STRUCTURE: [Username] [Name]
        return userProfile.getUsername() + " " + userProfile.getName() + "\n";
    }
}
