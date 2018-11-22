package com.jhonarendra.restoran.customer.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jhonarendra.restoran.customer.model.Hidangan;

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
        db.execSQL(Hidangan.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Hidangan.TABLE_NAME);
        onCreate(db);
    }

// public void insertData(String name, String price, byte[] image){
    //
    // pas nginsert:
    // sqLiteHelper.insertData(
    //     edtName.getText().toString().trim(),
    //     edtPrice.getText().toString().trim(),
    //     imageViewToByte(imageView)
    // );



//    Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
//    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//    byte[] byteArray = stream.toByteArray();
//        return byteArray;

     public long insertHidangan(String nama, String deskripsi, String kategori, String harga, byte[] foto) {
         SQLiteDatabase db = this.getWritableDatabase();
    
         ContentValues values = new ContentValues();

         values.put(Hidangan.COLUMN_NAMA, nama);
         values.put(Hidangan.COLUMN_DESKRIPSI, deskripsi);
         values.put(Hidangan.COLUMN_KATEGORI, kategori);
         values.put(Hidangan.COLUMN_HARGA, harga);
         values.put(Hidangan.COLUMN_FOTO, foto);

         long id = db.insert(Hidangan.TABLE_NAME, null, values);
         db.close();
         return id;
     }

     public List<Hidangan> getHidanganperKategori(String kategori){
         List<Hidangan> hidanganList = new ArrayList<>();
//         String queryPerKategori = "SELECT  * FROM " + Hidangan.TABLE_NAME + " WHERE " + Hidangan.COLUMN_KATEGORI + " = " + kategori;
//         Cursor cursor = db.rawQuery(queryPerKategori, null);

         SQLiteDatabase db = this.getWritableDatabase();

//         Cursor cursor = db.query(Hidangan.TABLE_NAME, new String[]{Hidangan.COLUMN_ID}, Hidangan.COLUMN_KATEGORI, new String[]{kategori}, null, null, null);

         Cursor cursor = db.query(Hidangan.TABLE_NAME,
                 new String[]{Hidangan.COLUMN_ID, Hidangan.COLUMN_NAMA, Hidangan.COLUMN_DESKRIPSI, Hidangan.COLUMN_HARGA, Hidangan.COLUMN_KATEGORI},
                 Hidangan.COLUMN_KATEGORI + "=?",
                 new String[]{kategori}, null, null, null, null);

         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do {
                 Hidangan hidangan = new Hidangan();
                 hidangan.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_NAMA)));
                 hidangan.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_DESKRIPSI)));
                 hidangan.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_HARGA)));
                 hidangan.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_KATEGORI)));
                 hidanganList.add(hidangan);
             } while (cursor.moveToNext());
         }

         // close db connection
         db.close();

         // return notes list
         return hidanganList;
     }


// ini cara nge get

// get all data from sqlite
        // Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM FOOD");
        // list.clear();
        // while (cursor.moveToNext()) {
        //     int id = cursor.getInt(0);
        //     String name = cursor.getString(1);
        //     String price = cursor.getString(2);
        //     byte[] image = cursor.getBlob(3);

        //     list.add(new Food(name, price, image, id));
        // }


// ini cara nampilin



     public List<Hidangan> getAllHidangan() {
         List<Hidangan> hidanganList = new ArrayList<>();
    
         // Select All Query
         String selectQuery = "SELECT  * FROM " + Hidangan.TABLE_NAME ;
    
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);
//         Cursor cursor = db.query(Hidangan.TABLE_NAME,
//                 new String[]{Hidangan.COLUMN_ID, Hidangan.COLUMN_NAMA, Hidangan.COLUMN_DESKRIPSI, Hidangan.COLUMN_HARGA, Hidangan.COLUMN_KATEGORI},
//                 null,
//                 new String[]{null}, null, null, null, String.valueOf(5));
    
         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             int i = 0;
             do {
                 Hidangan hidangan = new Hidangan();
                 hidangan.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_NAMA)));
                 hidangan.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_DESKRIPSI)));
                 hidangan.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_HARGA)));
                 hidangan.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_KATEGORI)));
                 hidangan.setByte_foto_hidangan(cursor.getBlob(cursor.getColumnIndex(Hidangan.COLUMN_FOTO)));
                 hidanganList.add(hidangan);
                 i++;
             } while (cursor.moveToNext() && i<5);
         }
    
         // close db connection
         db.close();
    
         // return notes list
         return hidanganList;
     }
    
     public int getHidanganCount() {
         List<Hidangan> hidanganList = new ArrayList<>();
         String countQuery = "SELECT  * FROM " + Hidangan.TABLE_NAME;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);

         // looping through all rows and adding to list
         if (cursor.moveToFirst()) {
             do {
                 Hidangan hidangan = new Hidangan();
                 hidangan.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_NAMA)));
                 hidangan.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_DESKRIPSI)));
                 hidangan.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_HARGA)));
                 hidangan.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_KATEGORI)));
                 hidanganList.add(hidangan);
             } while (cursor.moveToNext());
         }

         // close db connection
         db.close();
         cursor.close();
    
         int count = hidanganList.size();

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

}