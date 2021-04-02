package com.example.bookzilla;
import java.util.ArrayList;

public class SortBy {

    //These sorts are bubble sorts.

    private ArrayList<Book> prime = new ArrayList<Book>(); //= get list of books from profile (probably the database or a plaintext doc will be added later)
    private ArrayList<Book> nega = new ArrayList<Book>(); //temp arraylist for storing books not being searched for

    public void sortByScore(){
        while(!sortedByScore()) {
            for (int i = 0; i < prime.size() - 1; i++) {
                if (prime.get(i).getReview().getNumeralScore() > prime.get(i + 1).getReview().getNumeralScore()) {

                    Book temp = prime.get(i);
                    prime.set(i, prime.get(i + 1));
                    prime.set(i + 1, temp);

                }
            }
        }
    }

    private boolean sortedByScore(){

        for(int i = 0; i < prime.size() - 1; i++){
            if(prime.get(i).getReview().getNumeralScore() > prime.get(i+1).getReview().getNumeralScore()){
                return false;
            }
        }
        return true;
    }

    public void sortByTitle(){

        while(!sortedByTitle()) {
            for (int i = 0; i < prime.size() - 1; i++) {
                if (prime.get(i).getTitle().compareTo(prime.get(i+1).getTitle()) > 0) {

                    Book temp = prime.get(i);
                    prime.set(i, prime.get(i + 1));
                    prime.set(i + 1, temp);

                }
            }
        }
    }

    private boolean sortedByTitle(){

        for(int i = 0; i < prime.size() - 1; i++){
            if(prime.get(i).getTitle().compareTo(prime.get(i+1).getTitle()) > 0){
                return false;
            }
        }
        return true;
    }

    public void sortByAuthor(){ //sorts by author first name based on our implementation of Book; should alter?

        while(!sortedByAuthor()) {
            for (int i = 0; i < prime.size() - 1; i++) {
                if (prime.get(i).getAuthor().compareTo(prime.get(i+1).getAuthor()) > 0) {

                    Book temp = prime.get(i);
                    prime.set(i, prime.get(i + 1));
                    prime.set(i + 1, temp);

                }
            }
        }
    }

    private boolean sortedByAuthor(){

        for(int i = 0; i < prime.size() - 1; i++){
            if(prime.get(i).getAuthor().compareTo(prime.get(i+1).getAuthor()) > 0){
                return false;
            }
        }
        return true;
    }


    public void selectByGenre(Book.Genre genre){
        for(Book b : prime){    //for each book in the users list

            if(!b.getGenres().contains(genre)){ //if a book is not the genre being searched by
                nega.add(b);    //add book to temp arraylsit
                prime.remove(b);    //remove book from main array list
            }
        }
    }

    public void selectNoGenre(){
        for(Book b : nega){     //for each book in the temp list
            prime.add(b);       //add back to the main list
            nega.remove(b);     //remove book from temp list
        }
    }

    public ArrayList<Book> getPrime() {
        return prime;
    }

    public void addToPrime(int index, Book b){
        prime.add(index, b);
    }


}
