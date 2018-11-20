package com.jhonarendra.restoran.customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jhonarendra.restoran.customer.api.Result;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
 
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "restoran.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Result.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Result.TABLE_NAME);
        onCreate(db);
    }


     public long insertNote(String nama, String deskripsi, String kategori, String harga) {
         SQLiteDatabase db = this.getWritableDatabase();
    
         ContentValues values = new ContentValues();

         values.put(Result.COLUMN_NAMA, nama);
         values.put(Result.COLUMN_DESKRIPSI, deskripsi);
         values.put(Result.COLUMN_KATEGORI, kategori);
         values.put(Result.COLUMN_HARGA, harga);

         long id = db.insert(Result.TABLE_NAME, null, values);
         db.close();
         return id;
     }

     public List<Result> getHidanganperKategori(String kategori){
         List<Result> resultList = new ArrayList<>();
//         String queryPerKategori = "SELECT  * FROM " + Result.TABLE_NAME + " WHERE " + Result.COLUMN_KATEGORI + " = " + kategori;
//         Cursor cursor = db.rawQuery(queryPerKategori, null);

         SQLiteDatabase db = this.getWritableDatabase();

//         Cursor cursor = db.query(Result.TABLE_NAME, new String[]{Result.COLUMN_ID}, Result.COLUMN_KATEGORI, new String[]{kategori}, null, null, null);

         Cursor cursor = db.query(Result.TABLE_NAME,
                 new String[]{Result.COLUMN_ID, Result.COLUMN_NAMA, Result.COLUMN_DESKRIPSI, Result.COLUMN_HARGA, Result.COLUMN_KATEGORI},
                 Result.COLUMN_KATEGORI + "=?",
                 new String[]{kategori}, null, null, null, null);

         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do {
                 Result result = new Result();
                 result.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAMA)));
                 result.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_DESKRIPSI)));
                 result.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_HARGA)));
                 result.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_KATEGORI)));
                 resultList.add(result);
             } while (cursor.moveToNext());
         }

         // close db connection
         db.close();

         // return notes list
         return resultList;
     }

     public List<Result> getAllHidangan() {
         List<Result> resultList = new ArrayList<>();
    
         // Select All Query
         String selectQuery = "SELECT  * FROM " + Result.TABLE_NAME ;
    
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);
//         Cursor cursor = db.query(Result.TABLE_NAME,
//                 new String[]{Result.COLUMN_ID, Result.COLUMN_NAMA, Result.COLUMN_DESKRIPSI, Result.COLUMN_HARGA, Result.COLUMN_KATEGORI},
//                 null,
//                 new String[]{null}, null, null, null, String.valueOf(5));
    
         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             int i = 0;
             do {
                 Result result = new Result();
                 result.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAMA)));
                 result.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_DESKRIPSI)));
                 result.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_HARGA)));
                 result.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_KATEGORI)));
                 resultList.add(result);
                 i++;
             } while (cursor.moveToNext() && i<5);
         }
    
         // close db connection
         db.close();
    
         // return notes list
         return resultList;
     }
    
     public int getHidanganCount() {
         List<Result> resultList = new ArrayList<>();
         String countQuery = "SELECT  * FROM " + Result.TABLE_NAME;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);

         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do {
                 Result result = new Result();
                 result.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_NAMA)));
                 result.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_DESKRIPSI)));
                 result.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_HARGA)));
                 result.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Result.COLUMN_KATEGORI)));
                 resultList.add(result);
             } while (cursor.moveToNext());
         }

         // close db connection
         db.close();
         cursor.close();
    
         int count = resultList.size();

         // return count
         return count;
     }

    // public Note getNote(long id) {
    //     // get readable database as we are not inserting anything
    //     SQLiteDatabase db = this.getReadableDatabase();

    //     Cursor cursor = db.query(Note.TABLE_NAME,
    //             new String[]{Note.COLUMN_ID, Note.COLUMN_NOTE, Note.COLUMN_TIMESTAMP},
    //             Note.COLUMN_ID + "=?",
    //             new String[]{String.valueOf(id)}, null, null, null, null);

    //     if (cursor != null)
    //         cursor.moveToFirst();

    //     // prepare note object
    //     Note note = new Note(
    //             cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
    //             cursor.getString(cursor.getColumnIndex(Note.COLUMN_NOTE)),
    //             cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));

    //     // close the db connection
    //     cursor.close();

    //     return note;
    // }
    
    // public int updateNote(Note note) {
    //     SQLiteDatabase db = this.getWritableDatabase();
    
    //     ContentValues values = new ContentValues();
    //     values.put(Note.COLUMN_NOTE, note.getNote());
    
    //     // updating row
    //     return db.update(Note.TABLE_NAME, values, Note.COLUMN_ID + " = ?",
    //             new String[]{String.valueOf(note.getId())});
    // }
    
    // public void deleteNote(Note note) {
    //     SQLiteDatabase db = this.getWritableDatabase();
    //     db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
    //             new String[]{String.valueOf(note.getId())});
    //     db.close();
    // }
























    // ini manggilnya

    // private List<Note> notesList = new ArrayList<>();

    // private DatabaseHelper db;
     
    //     @Override
    //     protected void onCreate(Bundle savedInstanceState) {
    //         super.onCreate(savedInstanceState);
    //         setContentView(R.layout.activity_main);
    //         Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //         setSupportActionBar(toolbar);
     
    //         coordinatorLayout = findViewById(R.id.coordinator_layout);
    //         recyclerView = findViewById(R.id.recycler_view);
    //         noNotesView = findViewById(R.id.empty_notes_view);
     
    //         db = new DatabaseHelper(this);
     
    //         notesList.addAll(db.getAllNotes());
}