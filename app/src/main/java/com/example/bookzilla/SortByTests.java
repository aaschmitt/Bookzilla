package com.example.bookzilla;
//import org.j
import java.util.ArrayList;

public class SortByTests {

    public SortBy sb;

    public void setUp() {

        sb = new SortBy();
        sb.getPrime().add(new Book("Wuthering Heights", "Emily Bronte"));
        sb.getPrime().get(0).getReview().setNumeralScore(8);
        sb.getPrime().add(new Book("Jane Eyre", "Charlotte Bronte"));
        sb.getPrime().get(1).getReview().setNumeralScore(8);

        sb.getPrime().add(new Book("Fahrenheit 451", "Ray Bradbury"));
        sb.getPrime().get(2).getReview().setNumeralScore(9);
        sb.getPrime().add(new Book("Extremely L & I C", "Jonathan Safran Foer"));
        sb.getPrime().get(3).getReview().setNumeralScore(7);

        sb.getPrime().add(new Book("A1", "Tim Smith"));
        sb.getPrime().get(4).getReview().setNumeralScore(3);
        sb.getPrime().add(new Book("A1", "Tim Smith"));
        sb.getPrime().get(5).getReview().setNumeralScore(10);

    }

    public void testAlphabetical(){

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("A1");
        test1.add("A1");
        test1.add("Extremely L & I C");
        test1.add("Fahrenheit 451");
        test1.add("Jane Eyre");
        test1.add("Wuthering Heights");

        sb.sortByTitle();

        for(int i = 0; i < sb.getPrime().size(); i++){
            assert( sb.getPrime().get(i).getTitle().compareTo(test1.get(i)) == 0 );
        }


    }

    public void testAuthor(){

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("Charlotte Bronte");
        test1.add("Emily Bronte");
        test1.add("Jonathan Safran Foer");
        test1.add("Ray Bradbury");
        test1.add("Tim Smith");
        test1.add("Tim Smith");

        sb.sortByAuthor();

        for(int i = 0; i < sb.getPrime().size(); i++){
            assert( sb.getPrime().get(i).getAuthor().compareTo(test1.get(i)) == 0 );
        }


    }

    public void testScore(){

        ArrayList<Integer> test1 = new ArrayList<Integer>();
        test1.add(10);
        test1.add(9);
        test1.add(8);
        test1.add(8);
        test1.add(7);
        test1.add(3);

        sb.sortByScore();

        for(int i = 0; i < sb.getPrime().size(); i++){
            assert( sb.getPrime().get(i).getReview().getNumeralScore() == test1.get(i));
        }


    }



}
