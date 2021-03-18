package jepara.app.kinar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import jepara.app.kinar.R;

public class SliderAdapter extends PagerAdapter{

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){this.context = context;}

    int images[] = {
            R.drawable.vector_onboarding1,
            R.drawable.vector_onboarding2,
            R.drawable.vector_onboarding3
    };

    int headings[] = {
            R.string.title_onboarding1,
            R.string.title_onboarding2,
            R.string.title_onboarding3
    };

    int descriptions[] = {
            R.string.body_onboarding1,
            R.string.body_onboarding2,
            R.string.body_onboarding3
    };

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_layout,container,false);

        ImageView imageView = view.findViewById(R.id.slider_image);
        TextView title = view.findViewById(R.id.slider_title);
        TextView desc = view.findViewById(R.id.slider_desc);

        imageView.setImageResource(images[position]);
        title.setText(headings[position]);
        desc.setText(descriptions[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
