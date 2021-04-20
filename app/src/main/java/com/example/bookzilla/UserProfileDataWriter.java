package com.example.bookzilla;

import android.content.Context;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserProfileDataWriter {
    final static String usersFile = "users";

    public static void WriteCurrentUserBookInfo(Context mcoContext) {
        File dir = new File(mcoContext.getFilesDir(), "users");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, CurrentUserProfile.profile.getUsername());
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(GetCurrentUserBookDataString());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void WriteCurrentUserInfo(Context mcoContext) {

        File dir = new File(mcoContext.getFilesDir(), "mydir");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, usersFile);
            FileWriter writer = new FileWriter(gpxfile, true);
            writer.append(GetCurrentUserDataString());
            writer.flush();
            writer.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void DeleteCurrentUserInfo(Context context) {
        String usernameToDelete = "";
        for (String str : ReadUsers(context)) {
            String[] strings = str.split(" ");
            if (strings[0].equalsIgnoreCase(CurrentUserProfile.profile.getUsername())) {
                usernameToDelete = strings[0];
            }
        }
        for (UserProfile profile : CurrentUserProfile.allUsers) {
            if (profile.getUsername().equalsIgnoreCase(usernameToDelete)) {
                CurrentUserProfile.allUsers.remove(profile);
            }
        }

        if (CurrentUserProfile.profile == null && CurrentUserProfile.allUsers.size() == 0) {
            return;
        }
        else {
            CurrentUserProfile.profile = CurrentUserProfile.allUsers.get(0);
        }

        //TODO reload app to reflect removed user
    }

    private static List<String> ReadUsers(Context context) {
        List<String> readLines = new ArrayList<String>();
        try {
            File dir = new File(context.getFilesDir(), "mydir");
            if (!dir.exists()) {
                return null;
            }

            File myFile = new File(dir, usersFile);
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                readLines.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return readLines;
    }

    private static void ReadCurrentUserBooks(UserProfile profile, Context context) {
        List<String> readLines = new ArrayList<String>();
        try {
            File dir = new File(context.getFilesDir(), "users");
            if (!dir.exists()) {
                return;
            }

            File myFile = new File(dir, profile.getUsername());
            Scanner scanner = new Scanner(myFile);
            while (scanner.hasNextLine()) {
                readLines.add(scanner.nextLine());
            }
            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String readBook : readLines) {
            String[] bookInfo = readBook.split("_");
            profile.getBooks().add(new Book(bookInfo[0], bookInfo[1]));
        }
    }

    public static void LoadAllUserInfo(Context context) {
        if (ReadUsers(context).size() == 0) {
            return;
        }

        AddUserInfoToArrayList(ReadUsers(context));
        AddBooksToEachLoadedUser(context);

        if (CurrentUserProfile.allUsers.size() >= 1) {
            CurrentUserProfile.profile = CurrentUserProfile.allUsers.get(0);
        }
    }

    private static void AddBooksToEachLoadedUser(Context context) {
        if (CurrentUserProfile.allUsers.size() == 0) {
            return;
        }

        for (UserProfile profile : CurrentUserProfile.allUsers) {
            ReadCurrentUserBooks(profile, context);
        }
    }

    private static void AddUserInfoToArrayList(List<String> userInfo) {
        if (userInfo.size() == 0) {
            return;
        }

        for (String str : userInfo) {
            String[] strings = str.split(" ");
            UserProfile loadedProfile = new UserProfile(strings[1], strings[0]);
            CurrentUserProfile.allUsers.add(loadedProfile);
        }
    }

    private static String GetCurrentUserDataString() {
        if (CurrentUserProfile.profile != null) {
            return CurrentUserProfile.profile.getUsername() + " " + CurrentUserProfile.profile.getName() + "\n";
        }
        else {
            return "NULL";
        }
    }

    private static String GetCurrentUserBookDataString() {
        if (CurrentUserProfile.profile != null) {
            StringBuilder sb = new StringBuilder();
            for (Book book : CurrentUserProfile.profile.getBooks()) {
                sb.append(book.getTitle() + "_" + book.getAuthor() + "\n");
            }
            return sb.toString();
        }
        else {
            return "NULL";
        }
    }
}
