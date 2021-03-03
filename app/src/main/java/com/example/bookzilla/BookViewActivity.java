package com.example.bookzilla;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BookViewActivity extends AppCompatActivity {

    private Book book;
    private UserProfile currentUserProfile;

    private final String CURRENTBOOK = "Current Book: ";
    private final String CURRENTUSER = "Current User: ";

    @Override
    protected void onStart() {
        super.onStart();

        // TODO DELETE THIS BOOK AND USER (THEY WERE USED FOR TESTING) ------------------------------------------------------
        book = new Book("Catcher in The Rye", "J.D. Salinger", "www.amazon.com/catcher_in_the_rye");
        currentUserProfile = new UserProfile("Audrey", "audrey123");
        // TODO -------------------------------------------------------------------------------------------------------------

        ConfigureDisplays();
        System.out.println("App started!");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_view);
    }

    /* Adds the book currently being displayed to the user's book list, if not already in it */
    public void onAddBookButtonClick(View view) {
        // If userprofile or book is null, display an error message
        if (book == null || currentUserProfile == null) {
            Toast toast = Toast.makeText(this, "Failed -- Book or UserProfile is null!", 2);
            toast.show();
            return;
        }

        // Check to see if book is already in the user's list
        for (Book book : currentUserProfile.getBooks()) {
            if (this.book.getTitle() == book.getTitle() && this.book.getAuthor() == book.getAuthor()) {
                Toast toast = Toast.makeText(this, "This book is already in your list!", 2);
                toast.show();
                return;
            }
        }

        // Book not found in user's list, add it
        currentUserProfile.AddBook(book);
        Toast toast = Toast.makeText(this, "This book was successfully added to your list", 2);
        toast.show();

        // Display a different book
        setBook(new Book("SecondBook", "Audrey Schmitt", "www.mywebsite.com"));
    }

    /* Set the current UserProfile and reload page */
    public void setCurrentUserProfile(UserProfile currentUserProfile) {
        this.currentUserProfile = currentUserProfile;
        ConfigureUserProfileDisplay();
    }

    /* Set the current book and reload page */
    public void setBook(Book book) {
        this.book = book;
        ConfigureBookDisplay();
    }

    /* Refresh the Book UI */
    private void ConfigureBookDisplay() {
        TextView textView = (TextView) findViewById(R.id.textView);
        if (book == null) {
            textView.setText(CURRENTBOOK + "NULL");
        }
        else {
            textView.setText(CURRENTBOOK + book.getTitle());
        }
    }

    /* Refresh the UserProfile UI */
    private void ConfigureUserProfileDisplay() {
        TextView textView = (TextView) findViewById(R.id.textView3);
        if (currentUserProfile == null) {
            textView.setText(CURRENTUSER + "NULL");
        }
        else {
            textView.setText(CURRENTUSER + currentUserProfile.getName());
        }
    }

    /* Method to refresh / configure all displays */
    private void ConfigureDisplays() {
        ConfigureBookDisplay();
        ConfigureUserProfileDisplay();
    }
}
