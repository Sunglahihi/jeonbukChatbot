package com.example.jeonbuckchatbot;

import static android.view.animation.AnimationUtils.loadAnimation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends Activity {

    ImageButton b;
    ImageView i;
    private Intent intent;
    Animation slowly_appear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (ImageButton) findViewById(R.id.go);
        i = (ImageView) findViewById(R.id.onn);


        // 쉐도우 넣기
        Bitmap bitmapOriginal = BitmapFactory.decodeResource(getResources(),
                R.drawable.chatbotcharacter);

        int nWidth = bitmapOriginal.getWidth();
        int nHeight = bitmapOriginal.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap bitmapReflection = Bitmap.createBitmap(bitmapOriginal, 0,
                nHeight / 2, nWidth, nHeight / 2, matrix, false);

        Bitmap bitmapOrigianlAndReflection = Bitmap.createBitmap(nWidth,
                (nHeight + nHeight / 3), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapOrigianlAndReflection);
        canvas.drawBitmap(bitmapOriginal, 0, 0, null);
        Paint deafaultPaint = new Paint();
        canvas.drawRect(0, nHeight, nWidth, nHeight + 0, deafaultPaint);
        canvas.drawBitmap(bitmapReflection, 0, nHeight + 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmapOriginal
                .getHeight(), 0, bitmapOrigianlAndReflection.getHeight() + 5,
                0x70ffffff, 0x00ffffff, Shader.TileMode.MIRROR);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, nHeight, nWidth, bitmapOrigianlAndReflection
                .getHeight() + 5, paint);

        i.setImageBitmap(bitmapOrigianlAndReflection);

        // 버튼 속도 fade in (3초설정)
        slowly_appear = AnimationUtils.loadAnimation(this,R.anim.fadein);
        b.setAnimation(slowly_appear);
//      AnimationUtils.loadAnimation(this,R.anim.fadeout);

        // 애니매이션
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,chatroomActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(i,"imgTransition");
                //액티비티에서 움직일 뷰와 트랜지션이름을 Pair배열에 담아둔다.

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                //액티비티 옵션을 적용하기 위해 ActivityOptions객체를 만들고 트랜지션 에니메이션에 대한 설정을 넣는다

                startActivity(intent,options.toBundle());

            }
        });

    }
}