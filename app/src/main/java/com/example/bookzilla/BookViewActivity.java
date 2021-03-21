package com.example.bookzilla;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BookViewActivity extends AppCompatActivity {

    private Book book;
    private UserProfile currentUserProfile;

    private int toastDuration = 2;

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
        if (isUserOrBookNull()) {
            return;
        }

        // Check to see if book is already in the user's list
        for (Book book : currentUserProfile.getBooks()) {
            if (this.book.getTitle() == book.getTitle() && this.book.getAuthor() == book.getAuthor()) {
                Toast toast = Toast.makeText(this, book.getTitle() + " is already in your Favorites!", toastDuration);
                toast.show();
                return;
            }
        }

        // Book not found in user's list, add it
        currentUserProfile.AddBook(book);
        Toast toast = Toast.makeText(this, book.getTitle() + " was added to Favorites", toastDuration);
        toast.show();

        // Display a different book
        setBook(new Book("To Kill A Mockingbird", "Audrey Schmitt", "www.mywebsite.com"));
    }

    /* Removes the book currently being displayed to the user's book list, if in the list */
    public void onRemoveBookButtonClick(View view) {
        if (isUserOrBookNull()) {
            return;
        }

        for (Book book : currentUserProfile.getBooks()) {
            if (this.book.getTitle() == book.getTitle() && this.book.getAuthor() == book.getAuthor()) {
                currentUserProfile.RemoveBook(book);
                Toast toast = Toast.makeText(this, "Removed " + book.getTitle() + " from Favorites", toastDuration);
                toast.show();
                return;
            }
        }

        Toast toast = Toast.makeText(this, book.getTitle() + " is not in your Favorites!", toastDuration);
        toast.show();
    }

    private boolean isUserOrBookNull() {
        if (book == null || currentUserProfile == null) {
            Toast toast = Toast.makeText(this, "Failed -- Book or UserProfile is null!", 2);
            toast.show();
            return true;
        }
        return false;
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