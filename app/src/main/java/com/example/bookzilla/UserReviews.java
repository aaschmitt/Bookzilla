package com.example.bookzilla;//MCG 2-21-2021
import java.io.File;    //Eventually we are going to want to save this info to a text file I believe

public class UserReviews {

    //Written Review Inner Class
    private class WrittenReview {
        private String writtenReview;

        private String getWrittenReview() {
            return writtenReview;
        }

        private int getCharacterCountNoSpace() {    //Return length of written review minus number of spaces.
            int spaceCount = 0;
            for (char ch : writtenReview.toCharArray()) {
                if (ch == ' ') {
                    spaceCount++;
                }
            }
            return writtenReview.length() - spaceCount;
        }

        private int getCharCount(){
            return writtenReview.length();
        }

        /*
        TODO
        stretch goal word count method per review method
         */

        private void setWrittenReview(String writtenReview) {
            this.writtenReview = writtenReview;
        }

        public WrittenReview() {
            this.writtenReview = "";
        }

        public WrittenReview(String review) {
            this.writtenReview = review;
        }
    }
    //-----------------------------------------------------------------


    //Instance variables
    private WrittenReview wr;
    private int numeralScore = 0;


    //Constructors
    public UserReviews(){
        wr = new WrittenReview();
    }
    public UserReviews(String r){
        wr = new WrittenReview(r);
    }
    public UserReviews(int score){
        wr = new WrittenReview();
        numeralScore = score;
    }
    public UserReviews(String r, int score){
        wr = new WrittenReview(r);
        numeralScore = score;
    }


    //Getters and setters
    public int getNumeralScore() {
        return numeralScore;
    }
    public void setNumeralScore(int numeralScore) {
        this.numeralScore = numeralScore;
    }
    public String getWrittenReview(){
        return wr.getWrittenReview();
    }
    public void setWrittenReview(String review) {
        this.wr.setWrittenReview(review);
    }

}
