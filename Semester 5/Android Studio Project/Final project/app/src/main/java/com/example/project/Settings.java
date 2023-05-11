package com.example.project;

import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.mikhaellopez.circularimageview.CircularImageView;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    AppCompatButton editprofile;
    ImageView IVPreviewImage;

    TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        editprofile = (AppCompatButton) findViewById(R.id.editpro);
        logout = (TextView) findViewById(R.id.logout);
        IVPreviewImage = (CircularImageView) findViewById(R.id.IVPreviewImage);

        try {
            DBHelper dbHelper = new DBHelper(this);

            if (dbHelper.getImage(1) != null) {
//                IVPreviewImage.setImageBitmap(dbHelper.getImage(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, Editprofile.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        try {
            DBHelper dbHelper = new DBHelper(this);
            if (dbHelper.getImage(1) != null) {
                //IVPreviewImage.setImageBitmap(dbHelper.getImage(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(Settings.this, Login.class));
            }
        });
    }
    public void rate(View view) {
        Uri uri= Uri.parse("https://play.google.com/store/apps/details?id=com.hidden4world.newmyicecreamparlour"+getApplicationContext().getPackageName());
        Intent i=new Intent(Intent.ACTION_VIEW,uri);
        try{
            startActivity(i);}
        catch (Exception e){
            Toast.makeText(this,"Unable to open\n"+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }



    public void security(View view) {
        Intent intent=new Intent(Settings.this, security1.class );
        startActivity(intent);
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:03245110039"));
        startActivity(intent);
    }


    public void share(View view) {
        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.setType("text/plan");
        intent.putExtra(Intent.EXTRA_SUBJECT,"cHECKOUT THIS COOL aPPLICATION");
        intent.putExtra(Intent.EXTRA_TEXT,"Your Application link here");
        startActivity(Intent.createChooser(intent,"Share via"));

    }

    public void about(View view) {
        Intent intent=new Intent(Settings.this, Aboutus.class );
        startActivity(intent);
    }


    public void Help(View view) {
        Intent intent=new Intent(Settings.this, Help.class );
        startActivity(intent);
    }
}




