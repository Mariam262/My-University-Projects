package com.example.project;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    private static String DB_NAME = "users.db";
    private static int DB_VERSION = 1;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageinBytes;


    private static String createTableQuery = "Create table LoginUser (id INTEGER PRIMARY KEY " + ",image BlOB)";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
        Toast.makeText(context.getApplicationContext(), "table created sucessfull", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void saveImage(Bitmap image) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, stream);
        values.put("image", stream.toByteArray());
        values.put("id", 1);
        db.insert("LoginUser", null, values);
        stream.close();
    }

    public void storeData(Model model)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap imagetostorebitmap = model.getProfileImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        imagetostorebitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        imageinBytes = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        // contentValues.put("name",model.getName());
        //contentValues.put("email",model.getEmail());
        //contentValues.put("phonenumher",model.getPhonenum());
        contentValues.put("image", imageinBytes);


        long checkifqueryrun = db.insert("LoginUser", null, contentValues);
        if (checkifqueryrun != -1) {
            Toast.makeText(context.getApplicationContext(), "image added sucessfully", Toast.LENGTH_LONG).show();
            db.close();
        } else {
            Toast.makeText(context.getApplicationContext(), "fail to add", Toast.LENGTH_LONG).show();
        }


    }

    public Cursor getuser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from LoginUser ", null);
        return cursor;
    }


    public Bitmap getImage(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("LoginUser", new String[]{"image"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));
            cursor.close();
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return null;
    }
}
