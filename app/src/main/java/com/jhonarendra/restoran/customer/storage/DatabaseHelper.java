package com.jhonarendra.restoran.customer.storage;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.jhonarendra.restoran.customer.model.Hidangan;
import com.jhonarendra.restoran.customer.model.Komentar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        db.execSQL(Komentar.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Hidangan.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Komentar.TABLE_NAME);
        onCreate(db);
    }

     public long insertHidangan(String nama, String deskripsi, String kategori, String harga, String foto) {
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
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.query(Hidangan.TABLE_NAME,
                 new String[]{Hidangan.COLUMN_ID, Hidangan.COLUMN_NAMA, Hidangan.COLUMN_DESKRIPSI, Hidangan.COLUMN_HARGA, Hidangan.COLUMN_KATEGORI},
                 Hidangan.COLUMN_KATEGORI + "=?",
                 new String[]{kategori}, null, null, null, null);
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
         db.close();
         return hidanganList;
     }

    public List<Hidangan> getAllHidangan() {
        List<Hidangan> hidanganList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Hidangan.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Hidangan hidangan = new Hidangan();
                hidangan.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_NAMA)));
                hidangan.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_DESKRIPSI)));
                hidangan.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_HARGA)));
                hidangan.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_KATEGORI)));
                hidangan.setFoto_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_FOTO)));
                hidanganList.add(hidangan);
            } while (cursor.moveToNext());
        }
        db.close();
        return hidanganList;
    }

     public List<Hidangan> getHidanganLimit() {
         List<Hidangan> hidanganList = new ArrayList<>();
         String selectQuery = "SELECT  * FROM " + Hidangan.TABLE_NAME ;
    
         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(selectQuery, null);
         if (cursor.moveToFirst()) {
             int i = 0;
             do {
                 Hidangan hidangan = new Hidangan();
                 hidangan.setNama_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_NAMA)));
                 hidangan.setDeskripsi_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_DESKRIPSI)));
                 hidangan.setHarga_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_HARGA)));
                 hidangan.setKategori_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_KATEGORI)));
                 hidangan.setFoto_hidangan(cursor.getString(cursor.getColumnIndex(Hidangan.COLUMN_FOTO)));
                 hidanganList.add(hidangan);
                 i++;
             } while (cursor.moveToNext() && i<5);
         }
         db.close();
         return hidanganList;
     }
    
     public int getHidanganCount() {
         List<Hidangan> hidanganList = new ArrayList<>();
         String countQuery = "SELECT  * FROM " + Hidangan.TABLE_NAME;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(countQuery, null);

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
         db.close();
         cursor.close();
         int count = hidanganList.size();
         return count;
     }

    public long insertKomentar(String komentar){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Komentar.COLUMN_KOMENTAR, komentar);

        long id = db.insert(Komentar.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    public List<Komentar> getAllKomentar(){
        List<Komentar> komentarList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + Komentar.TABLE_NAME ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Komentar komentar = new Komentar();
                komentar.setIsi_komentar(cursor.getString(cursor.getColumnIndex(Komentar.COLUMN_KOMENTAR)));
                komentarList.add(komentar);
            } while (cursor.moveToNext());
        }
        db.close();
        return komentarList;
    }

    public int getKomentarCount() {
        List<Komentar> komentarList = new ArrayList<>();
        String countQuery = "SELECT  * FROM " + Komentar.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Komentar komentar = new Komentar();
                komentar.setIsi_komentar(cursor.getString(cursor.getColumnIndex(Komentar.COLUMN_KOMENTAR)));
                komentarList.add(komentar);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        int count = komentarList.size();
        return count;
    }





//    private Bitmap loadImageFromStorage(ImageView imageView, String path, String nama) {
//        Bitmap b = null;
//         try {
//            File f=new File(path, nama);
//            //nama = es_krim.png
//            b = BitmapFactory.decodeStream(new FileInputStream(f));
//            imageView.setImageBitmap(b);
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//        return b;
//
//    }









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