package com.farhan.dingdong;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   private Button button;
   private MediaPlayer player;

   AnimationDrawable animationDrawable;

    @SuppressLint("ClickableViewAccessibility")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.BUTTON);

        ImageView imageView = findViewById(R.id.imageV);
        imageView.setBackgroundResource(R.drawable.animation);
        animationDrawable = (AnimationDrawable) imageView.getBackground();


        final int[] sound = {R.raw.xp_error_mix, R.raw.yeahyeah, R.raw.dingdong, R.raw.dingdong};



        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    animationDrawable.start();
                    Random random = new Random();
                    int low = 0;
                    int high = 4;
                    int rnd = random.nextInt(high - low) + low;
                    player = MediaPlayer.create(MainActivity.this, sound[rnd]);
                    player.start();
                    v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    animationDrawable.stop();
                    animationDrawable.selectDrawable(0);
                    player.stop();
                    player.release();
                    v.getBackground().clearColorFilter();
                    v.invalidate();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_us:
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.alartdialog);
                dialog.show();
                break;

            case R.id.shareme:
                Intent i = new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                startActivity(Intent.createChooser(i, "Share Via"));
                break;

        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you really want to exit DingDong \uD83D\uDE22\uD83D\uDE22 ??").setCancelable(false).
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void facebook(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.facebook.com/farhansadikgalib"));
        startActivity(i);
        return ;

    }

    public void github(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://github.com/farhansadikgalib"));
        startActivity(i);
        return ;
    }

    public void aboutme(View view) {

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://about.me/farhansadikgalib"));
        startActivity(i);
        return ;


    }
}
