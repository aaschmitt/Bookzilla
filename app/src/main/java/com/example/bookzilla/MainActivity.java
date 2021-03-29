package com.example.bookzilla;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Book book = new Book();

    WishlistActivity wishlistActivity = new WishlistActivity();

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        //this is working code we need a book class, so we can get the book name
        if(item.equals("Wishlist")){
           book.setStatus(Book.Status.Wishlist);
        }
        else if (item.equals("Reading")){
            if(book.getCurrentStatus() == Book.Status.Wishlist){
                wishlistActivity.removeBook(book);
            }
            book.setStatus(Book.Status.Reading);
        }
        else if(item.equals("Read")){
            if(book.getCurrentStatus() == Book.Status.Wishlist){
                wishlistActivity.removeBook(book);
            }
            book.setStatus(Book.Status.Read);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // idk what needs to be done when nothing is selected
    }
}


public class MainActivity extends AppCompatActivity {

    /* Instance Variables */
    ArrayList<String> wishlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpinnerActivity spinnerActivity = new SpinnerActivity();
        
/*-------------------Start Code for dropdownBox-----------*/
        Spinner spinner = findViewById(R.id.status);

        spinner.setOnItemSelectedListener(spinnerActivity);

        //categories for the user to pick from
        List<String> statusCategories = new ArrayList<>();

        statusCategories.add("Status");
        statusCategories.add("Read");
        statusCategories.add("Wishlist");
        statusCategories.add("Reading");

        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusCategories);
        //set the spinner to the adapter array
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    /*-------------end for the code of dropdownBox---------------*/
        UpdateCurrentUserTextView();
    }

    // TODO code for the button that navigates to the BookViewActivity -- delete when another way of navigation has been developed
    public void onGotoBookViewClick(View view) {
        Intent intent = new Intent(this, BookViewActivity.class);
        startActivity(intent);
    }

    // TODO code for the button that navigates to the UserProfilePageActivity -- delete when another way of navigation has been developed
    public void onGotoUserProfileClick(View view) {
        Intent intent = new Intent(this, UserProfilePageActivity.class);
        startActivity(intent);
    }

    public void onCreateNewUserProfileClick(View view) {
        Intent intent = new Intent(this, CreateNewUserProfileActivity.class);
        startActivity(intent);
    }

    public void onChooseExistingUserProfileClick(View view) {
        Intent intent = new Intent(this, ChooseExistingUserProfileActivity.class);
        startActivity(intent);
    }

    public void goToWishlist(View view){
        Intent intent = new Intent(this, WishlistActivity.class);
        startActivity(intent);
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


}
