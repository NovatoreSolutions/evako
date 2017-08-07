package com.example.intern.Evakoapp.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.Evakoapp.Activities.MainActivity;
import com.example.intern.Evakoapp.Models.SliderItems;
import com.example.intern.Evakoapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by intern on 26/01/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    MainActivity ma = new MainActivity();
    int length;
    List<SliderItems> sliderarray;
    int count = 0;
    ViewPager viewPager;

    ImageView BackImage;
    CircleImageView Logo;
    TextView Name;
    TextView Description;
    TextView ItemPrice;
    RelativeLayout innersliderback;
    ////


    public CustomPagerAdapter(Context context , int i , List<SliderItems> sliderarray) {

        mContext = context;
        this.length = i;
        this.sliderarray = sliderarray;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View v = collection;



//        ItemPrice = (TextView) v.findViewById(R.id.price);


//        SliderItems customPagerEnum = new SliderItems(mContext);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.slider_inflate_layout, collection, false);

        BackImage = (ImageView) layout.findViewById(R.id.sliderInnerLayoutImage);
        Logo = (CircleImageView) layout.findViewById(R.id.sliderInnerLayoutTitleImage);
        Name = (TextView) layout.findViewById(R.id.brandName);
        Description = (TextView) layout.findViewById(R.id.branddescription);
        ItemPrice = (TextView) layout.findViewById(R.id.price);
        innersliderback = (RelativeLayout) layout.findViewById(R.id.innerlayoutback);

//            ItemPrice.setText("414");
//        Toast.makeText(mContext , "pos = "+position, Toast.LENGTH_SHORT).show();
        changeSliderItem(position);

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        count=0;
        collection.removeView((View) view);
    }

    @Override public float getPageWidth(int position) { return(0.85f); }
    @Override
    public int getCount() {
        return length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return null;
    }



    public void changeSliderItem (int position){




        if (position < length ){

             SliderItems sliderObject = sliderarray.get(position);

              Picasso.with(mContext).load(sliderObject.getBackImage()).into(BackImage, new Callback() {
                  @Override
                  public void onSuccess() {

                      innersliderback.setBackgroundDrawable(BackImage.getDrawable());
                  }

                  @Override
                  public void onError() {

                  }
              });
             innersliderback.setVisibility(View.VISIBLE);
              BackImage.setAdjustViewBounds(true);
              Picasso.with(mContext).load(sliderObject.getLogo()).into(Logo);
              Name.setText(sliderObject.getName());
//Toast.makeText(mContext , "From else  name "+sliderObject.getName()+" \n text  "+ItemPrice , Toast.LENGTH_SHORT).show();
              Description.setText(sliderObject.getDescription());
              ItemPrice.setText(sliderObject.getItemPrice().toString());
              count = count+1;

//              Toast.makeText(MainActivity.this , "From else  name "+sliderObject.getName()+" \n text  "+text+"\n  Item "+ItemPrice , Toast.LENGTH_SHORT).show();
//              Toast.makeText(MainActivity.this , "From else inside "+slidercount+" array is "+sliderItemArr.size() , Toast.LENGTH_SHORT).show()

        }
        else
        {

            Toast.makeText(mContext , "Toast over " , Toast.LENGTH_LONG).show();
//              Toast.makeText(MainActivity.this , "From else "+slidercount+" array is "+sliderItemArr.size() , Toast.LENGTH_SHORT).show();
        }

    }//changeSliderEndsHere...


}