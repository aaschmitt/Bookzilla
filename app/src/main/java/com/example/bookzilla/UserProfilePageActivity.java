package com.example.bookzilla;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UserProfilePageActivity extends AppCompatActivity {

    private ListView listView;
    private TextView textView;
    private UserProfile currentUserProfile;

    private final String CURRENTUSERSTR = "CURRENT USER: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_page);

        //TODO -- delete this user and books, was created for testing purposes -----------------------------------------------
        currentUserProfile = new UserProfile("Audrey", "audrey123");
        currentUserProfile.AddBook(new Book("Catcher in the Rye", "J.D. Salinger", "NA"));
        currentUserProfile.AddBook(new Book("The Little Prince", "Antoine de Saint-Exupery", "NA" ));
        currentUserProfile.AddBook(new Book("Dream of the Red Chamber", "Cao Xueqin", "NA"));
        currentUserProfile.AddBook(new Book("The Hobbit", "J.R.R. Tolkien", "NA"));
        currentUserProfile.AddBook(new Book("And Then There Were None", "Agatha Christie", "NA"));
        currentUserProfile.AddBook(new Book("Farenheit 451", "", "NA"));
        //TODO ----------------------------------------------------------------------------------------------------------------

        ConfigureDisplay(currentUserProfile);
    }

    public void SetCurrentUserProfile(UserProfile currentUserProfile) {
        this.currentUserProfile = currentUserProfile;
        // Update UI with new user's book list
        ConfigureDisplay(this.currentUserProfile);
    }

    private void ConfigureDisplay(UserProfile userProfile) {
        // Update list of books
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                userProfile.getBookTitles()
        );
        listView.setAdapter(adapter);

        // Update displayed username
        textView = (TextView) findViewById(R.id.currentUserTextView);
        textView.setText(CURRENTUSERSTR + userProfile.getName());
    }

    public void OnChangeCurrentUserButtonClick(View view) {
        // TODO delete this later, used for testing for switching between different users ---------------------
        UserProfile newUser = new UserProfile("Jesse", "Jesse123");
        newUser.AddBook(new Book("The Very Hungry Caterpillar", "", ""));
        newUser.AddBook(new Book("Flowers in the Attic", "", ""));
        newUser.AddBook(new Book("Sophie's World", "", ""));
        newUser.AddBook(new Book("Kane and Abel", "", ""));
        newUser.AddBook(new Book("To Kill a Mockingbird", "", ""));
        newUser.AddBook(new Book("The Girl with the Dragon Tattoo", "", ""));
        newUser.AddBook(new Book("The Hunger Games", "", ""));
        // TODO -----------------------------------------------------------------------------------------------

        SetCurrentUserProfile(newUser);
    }
}
