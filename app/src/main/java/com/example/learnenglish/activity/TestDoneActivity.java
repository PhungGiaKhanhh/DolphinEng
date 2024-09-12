package com.example.learnenglish.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish.model.Question;
import com.example.learnenglish.R;

import java.io.File;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

public class TestDoneActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<Question> arr_QuesBegin= new ArrayList<Question>();
    int numTrue=0;
    int numFalse=0;
    int numNoAns=0;
    TextView txtCaudung, txtCausai, txtChuaTraloi, txtTongdiem;
    Button btnChoilai, btnThoat;
    ImageButton btnShare;
    String sharePath = "no";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_done);
        printKeyHash();
        Intent intent= getIntent();
        arr_QuesBegin= (ArrayList<Question>) intent.getSerializableExtra("arr_Ques");
        anhXa();
        checkResult();
        txtCaudung.setText(""+numTrue);
        txtCausai.setText(""+numFalse);
        txtChuaTraloi.setText(""+numNoAns);
        int tongDiem=10*numTrue;
        txtTongdiem.setText(""+tongDiem);
        final int viTri= Integer.valueOf(arr_QuesBegin.get(2).getBaiTest())-1;
        btnChoilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(TestDoneActivity.this, ScreenSlidePagerActivity.class);
                intent.putExtra("viTri", viTri);
                startActivity(intent);
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(TestDoneActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                takeScreenShot();
            }
        }, 1000);

        btnShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        share(sharePath);
    }

    public void anhXa(){
        txtCaudung=(TextView) findViewById(R.id.textview_caudung);
        txtCausai=(TextView) findViewById(R.id.textview_causai);
        txtChuaTraloi=(TextView) findViewById(R.id.textview_chuaTraloi);
        txtTongdiem=(TextView) findViewById(R.id.textview_tongDiem);
        btnChoilai=(Button) findViewById(R.id.Button_Choilai);
        btnThoat=(Button)findViewById(R.id.Button_Thoat);
        btnShare=(ImageButton) findViewById(R.id.button_share);
    }
    public void checkResult(){
        for(int i=0;i<arr_QuesBegin.size();i++){
            if(arr_QuesBegin.get(i).getTraLoi().equals("")==true){
                numNoAns++;
            }else if(arr_QuesBegin.get(i).getTraLoi().equals(arr_QuesBegin.get(i).getAnswer())==true){
                numTrue++;
            }else numFalse++;
        }
    }

    public void takeScreenShot(){
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
        try {
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpeg";
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache(), 0, 0, v1.getWidth(), v1.getHeight() - 500);
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            String filePath = imageFile.getPath();
            sharePath = filePath;
            Log.d("sharepath",sharePath);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void share(String sharePath) {
        File file = new File(sharePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent .setType("image/*");
        intent .putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }

    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.learnenglish",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
