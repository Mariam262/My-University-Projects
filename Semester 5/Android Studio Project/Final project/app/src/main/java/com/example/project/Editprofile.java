package com.example.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class Editprofile extends AppCompatActivity {


    FloatingActionButton BSelectImage;

    EditText name, email, phonenum;
    DBHelper dbHelper;
    Button editpro;
    private Bitmap imagetostore;


    // One Preview Image
    ImageView IVPreviewImage;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);


        BSelectImage = (FloatingActionButton) findViewById(R.id.iv_camera);
        IVPreviewImage = (ImageView) findViewById(R.id.IVPreviewImage);

        //name = (EditText) findViewById(R.id.editTextname);
        email = (EditText) findViewById(R.id.editemail);

        editpro = (Button) findViewById(R.id.button2);

        dbHelper = new DBHelper(this);
        try {
            if (dbHelper.getImage(1) != null) {
                //IVPreviewImage.setImageBitmap(dbHelper.getImage(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        BSelectImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                imageChooser();
            }
        });


        editpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    saveImage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//              retreiveimage();
            }
        });


    }

    private void saveImage() throws IOException {
        dbHelper.saveImage(imagetostore);
        Toast.makeText(this, "Save Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    try {
                        imagetostore = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    IVPreviewImage.setImageURI(selectedImageUri);
                }
            }
        }
    }

}




