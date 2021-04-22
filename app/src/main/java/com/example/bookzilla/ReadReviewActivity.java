package com.example.bookzilla;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ReadReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_review);

        UpdateTitle();
        DisplayReview();
    }

    private void UpdateTitle() {
        TextView textView = findViewById(R.id.textView7);
        textView.setText("Review for: " + CurrentUserProfile.currentBook.getTitle());
    }

    private void DisplayReview() {
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        String review = UserProfileDataWriter.ReadReview(CurrentUserProfile.currentBook.getTitle(), this);

        if (review.length() > 1) {
            TextView textView = findViewById(R.id.reviewTextView);
            textView.setText(review);
        }
    }

    public void onWriteNewReviewButtonClick(View view) {
        Intent intent = new Intent(this, WriteReviewActivity.class);
        startActivity(intent);
    }
}
