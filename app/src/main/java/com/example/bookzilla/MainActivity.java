package com.example.bookzilla;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(pos).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        //this is working code we need a book class, so we can get the book name
        if(item.equals("Wishlist")){
            //add to Wishlist
        }
        else {
            //if book was in wishlist then remove it
           // wishlist.remove("Book");
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}


public class MainActivity extends AppCompatActivity {
    ArrayList<String> wishlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpinnerActivity spinnerActivity = new SpinnerActivity();

        // Hello world :)
        
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

}
