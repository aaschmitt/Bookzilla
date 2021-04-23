package com.example.bookzilla;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class BookViewActivity extends AppCompatActivity {

    private Book book;

    private int toastDuration = 2;

    private final String CURRENTBOOK = "Current Book: ";
    private final String CURRENTUSER = "Current User: ";

    @Override
    protected void onStart() {
        super.onStart();

        // TODO DELETE THIS BOOK AND USER (THEY WERE USED FOR TESTING) ------------------------------------------------------
        book = new Book("Catcher in The Rye", "J.D. Salinger", "www.amazon.com/catcher_in_the_rye");
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
        for (Book book : CurrentUserProfile.profile.getBooks()) {
            if (this.book.getTitle() == book.getTitle() && this.book.getAuthor() == book.getAuthor()) {
                Toast toast = Toast.makeText(this, book.getTitle() + " is already in your Favorites!", toastDuration);
                toast.show();
                return;
            }
        }

        // Book not found in user's list, add it
        CurrentUserProfile.profile.AddBook(book);
        UserProfileDataWriter.WriteCurrentUserBookInfo(this);
        Toast toast = Toast.makeText(this, book.getTitle() + " was added to Favorites", toastDuration);
        toast.show();
    }

    //TODO demo method -- displays functionality for viewing a different book in the bookview activity
    public void onViewAnotherBookButtonClick(View view) {
        // Display a different book
        setBook(new Book("To Kill A Mockingbird", "Audrey Schmitt", "www.mywebsite.com"));
    }

    public void onViewReviewButtonClick(View view) {
        CurrentUserProfile.currentBook = book;

        if (UserProfileDataWriter.ReadReview(CurrentUserProfile.currentBook.getTitle(), this).length() > 1) {
            Intent intent = new Intent(this, ReadReviewActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, WriteReviewActivity.class);
            startActivity(intent);
        }
    }

    /* Removes the book currently being displayed to the user's book list, if in the list */
    public void onRemoveBookButtonClick(View view) {
        if (isUserOrBookNull()) {
            return;
        }

        for (Book book : CurrentUserProfile.profile.getBooks()) {
            if (this.book.getTitle().equals(book.getTitle()) && this.book.getAuthor().equalsIgnoreCase(book.getAuthor())) {
                CurrentUserProfile.profile.RemoveBook(book);
                UserProfileDataWriter.WriteCurrentUserBookInfo(this);
                Toast toast = Toast.makeText(this, "Removed " + book.getTitle() + " from Favorites", toastDuration);
                toast.show();
                return;
            }
        }

        Toast toast = Toast.makeText(this, book.getTitle() + " is not in your Favorites!", toastDuration);
        toast.show();
    }

    private boolean isUserOrBookNull() {
        if (book == null || CurrentUserProfile.profile == null) {
            Toast toast = Toast.makeText(this, "Failed -- Book or UserProfile is null!", 2);
            toast.show();
            return true;
        }
        return false;
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
        if (CurrentUserProfile.profile == null) {
            textView.setText(CURRENTUSER + "NULL");
        }
        else {
            textView.setText(CURRENTUSER + CurrentUserProfile.profile.getName());
        }
    }

    /* Method to refresh / configure all displays */
    private void ConfigureDisplays() {
        ConfigureBookDisplay();
        ConfigureUserProfileDisplay();
    }
}
