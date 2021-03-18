package jepara.app.kinar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import jepara.app.kinar.Adapter.SliderAdapter;
import jepara.app.kinar.R;

public class Onboarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button btStart,btSkip;
    Animation animation;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        //find
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        btStart= findViewById(R.id.start_btn);
        btSkip= findViewById(R.id.skip_btn);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    // onClick Mulai & Transisi
    public void start(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View,String>(findViewById(R.id.start_btn),"transisi_start");
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Onboarding.this);
            startActivity(intent,options.toBundle());
    }

    // onClick Skip
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    // Navigasi Onboarding
    private void addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.primary));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);

            if(position == 0){
                btStart.setVisibility(View.INVISIBLE);
            }
            else if(position == 1){
                btStart.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(Onboarding.this,R.anim.anim_btn_start);
                btStart.setAnimation(animation);
                btStart.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan 'Back' sekali lagi", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);    }
}