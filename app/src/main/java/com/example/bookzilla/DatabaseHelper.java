package com.example.bookzilla;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * books database
 *
 * Table: Books
 * COLS: Title, Author, Year, Link
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATA_BASENAME = "books_database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_BOOKS = "books";
    private static final String COL1_TITLE = "TITLE";
    private static final String COL2_AUTHOR = "AUTHOR";
    private static final String COL3_LINK = "LINK";




    public DatabaseHelper(Context context) {
        super(context, DATA_BASENAME, null, DATABASE_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + COL1_TITLE + "TEXT Primary Key,"
                + COL2_AUTHOR + "TEXT,"
                + COL3_LINK + "TEXT"
                + ")";

        db.execSQL(CREATE_BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If EXISTS " + TABLE_BOOKS);

        onCreate(db);
    }

    //add book to database
    void addBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL1_TITLE, book.getTitle());
        values.put(COL2_AUTHOR, book.getAuthor());

        db.insert(TABLE_BOOKS, null, values);
        db.close();
    }

    Book getBook(String title, String author){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKS, new String[] {COL1_TITLE, COL2_AUTHOR, COL3_LINK}, COL1_TITLE + "=?",
                new String[] {String.valueOf(title), String.valueOf(author)}, null,null,null,null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Book book = new Book(cursor.getString(0), cursor.getString(1), cursor.getString(2));
        return book;
    }

    public List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        String selectQuery = "Select * From " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Book book = new Book();
                book.setTitle(cursor.getString(0));
                book.setAuthor(cursor.getString(1));
                book.setExternalLink(cursor.getString(2));
                 bookList.add(book);
            } while (cursor.moveToNext());
        }

        return bookList;
    }

    public int updateBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COL1_TITLE, book.getTitle());
        values.put(COL2_AUTHOR, book.getAuthor());

        return db.update(TABLE_BOOKS, values, COL1_TITLE + "=?",
                new String[] {book.getTitle()});

    }

    public void deleteBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_BOOKS, COL1_TITLE + "=?",
                new String[] {book.getTitle()});
        db.close();

    }

    public int getBookCount(){
        String countQuery = "Select * From " + TABLE_BOOKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }


}
