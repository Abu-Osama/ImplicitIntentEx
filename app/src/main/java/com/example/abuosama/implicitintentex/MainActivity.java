package com.example.abuosama.implicitintentex;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button button,button2,button3;
    EditText editText;
    int REQ_CD=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button1);
        button2= (Button) findViewById(R.id.button2);
        button3= (Button) findViewById(R.id.button3);
        editText= (EditText) findViewById(R.id.edittext1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open other application
                Intent intent=new Intent();
                //intent.setAction(Intent.ACTION_DIAL);
                intent.setAction(Intent.ACTION_CALL);
                //Uri uri=Uri.parse("tel:901423232");
                Uri uri=Uri.parse("tel:+91-"+editText.getText().toString());
                intent.setData(uri);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri= Uri.parse("http://skillgun.com");
                intent.setData(uri);
                startActivity(intent);

            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,REQ_CD);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQ_CD&& requestCode==RESULT_OK){

            Uri imagepath=data.getData();
            Bitmap image=null;
            try {
                image= MediaStore.Images.Media.getBitmap(getContentResolver(),imagepath);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ImageView imageView= (ImageView) findViewById(R.id.imageview1);
            imageView.setImageBitmap(image);
//            Bundle bundle=data.getExtras();
//            String image=bundle.getString("data");
//            Bitmap bitmap= BitmapFactory.decodeFile(image);
//            ImageView imageView= (ImageView) findViewById(R.id.imageview1);
//            imageView.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
