package com.example.bookzilla;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class WriteReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        UpdateTitleText();
    }

    private void UpdateTitleText() {
        TextView titleText = (TextView) findViewById(R.id.textView2);
        if (CurrentUserProfile.currentBook == null) {
            titleText.setText("Write Review for: NULL");
        } else {
            titleText.setText("Write Review for: " + CurrentUserProfile.currentBook.getTitle());
        }
    }

    public void OnSaveReviewButtonClick(View view) {
        SaveReview();
    }

    private void SaveReview() {
        EditText reviewText = (EditText) findViewById(R.id.editText);
        if (reviewText.getText().length() == 0) {
            Toast toast = Toast.makeText(this, "Please type a review before saving!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else {
            UserProfileDataWriter.SaveReview(this, CurrentUserProfile.currentBook, reviewText.getText().toString());
            Toast toast = Toast.makeText(this, "Review saved", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
