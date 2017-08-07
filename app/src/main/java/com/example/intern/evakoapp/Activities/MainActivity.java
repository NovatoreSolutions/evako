package com.example.intern.Evakoapp.Activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.intern.Evakoapp.Controllers.AppController;
import com.example.intern.Evakoapp.Adapters.CustomPagerAdapter;
import com.example.intern.Evakoapp.Adapters.FeedItemAdapter;
import com.example.intern.Evakoapp.Models.FoodFeed;
import com.example.intern.Evakoapp.R;
import com.example.intern.Evakoapp.Models.SliderItems;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    public List<SliderItems> sliderItemArr = new ArrayList();
    public List<FoodFeed> feedItemArr = new ArrayList();
    public RecyclerView recyclerView;
    FeedItemAdapter feedItemAdapter;
    public String sliderimage1 , sliderimage2 , sliderimage3;
      View mainsliderback;
    Timer repeatIt = new Timer();
      ViewPager viewPager;
     ImageView rightArrow , leftArrow, searchicon ;
      EditText search;
      Button burger;
      View navigation;
   public  RelativeLayout.LayoutParams relativeParams;

      RelativeLayout pagerlayout, loweractionlayout , leftmenu , navimain;

    int p= 0 , n=0 , loweractioncount=0;
    int repeatTime =2000;
    ImageView sliderBack;
//    JSONParsingClass parser ;


      ////NavigationViews
      TextView login , register , menu , promotion , track, pages , help ,language , helpcentre , account , report;


    ///parsing Data
    String TAG = "ParseActivity";
    String tag_json_obj= "json_obj_req";
    Context context;
    SliderItems sliderItems = new SliderItems(MainActivity.this);
    FoodFeed foodFeed;
    int slidercount=0;
    /////

      ////SliderItemsData

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerlayout = (RelativeLayout) findViewById(R.id.pagerlayout);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pagerlayout.setClipToOutline(true);
        }
        mainsliderback = findViewById(R.id.sliderback);
        navimain = (RelativeLayout) findViewById(R.id.navimain);
        loweractionlayout = (RelativeLayout) findViewById(R.id.loweraction);
        leftmenu = (RelativeLayout) findViewById(R.id.leftmenu);
         viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageMargin(30);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(20,0,20,0);
        searchicon = (ImageView) findViewById(R.id.searchicon);
        burger  = (Button) findViewById(R.id.burgericon);
        navigation =findViewById(R.id.navigationmenu);


        ////navigation...
        login = (TextView) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.Register);
        menu = (TextView) findViewById(R.id.menu);
        promotion = (TextView) findViewById(R.id.Promotions);
        track = (TextView) findViewById(R.id.track);
        pages = (TextView) findViewById(R.id.Pages);
        help = (TextView) findViewById(R.id.help);
        language = (TextView) findViewById(R.id.languagetext);
        helpcentre = (TextView) findViewById(R.id.helpcentretext);
        account = (TextView) findViewById(R.id.accounttext);
        report = (TextView) findViewById(R.id.problemtext);


        prepareFeedItemListData();
        feedItemAdapter = new FeedItemAdapter(feedItemArr , MainActivity.this);
        RecyclerViewHeader header = (RecyclerViewHeader) findViewById(R.id.header);

        sliderBack = (ImageView) findViewById(R.id.sliderbackimage);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewlist);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        header.attachTo(recyclerView);



        searchicon.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {


                if (loweractioncount==0) {
                    loweractionlayout.setVisibility(View.VISIBLE);
                loweractioncount=1;


//                    navigation.setVisibility(View.VISIBLE);

                }

                else {
                    loweractionlayout.setVisibility(View.GONE);

                    loweractioncount=0;
                }


            }
        });
        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  marginleft=0 , marginright=0;

                Animation animationslideback ,animation1 , animationmain;



                WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                DisplayMetrics metrics = new DisplayMetrics();
                display.getMetrics(metrics);
                int width = metrics.widthPixels;
                int height = metrics.heightPixels;
//                    Toast.makeText(getApplication() , "W = "+width+" \n H ="+height , Toast.LENGTH_SHORT).show();

                marginleft = width-(width*180)/100;


                if (n==0){
                    float density = getApplicationContext().getResources().getDisplayMetrics().density;
                    // float px = marginright * density;
                    float dp = marginright / density;
//                        Toast.makeText(getApplication() , "in density ="+density , Toast.LENGTH_SHORT).show();

//
// return 0.75 if it's LDPI
// return 1.0 if it's MDPI
// return 1.5 if it's HDPI
// return 2.0 if it's XHDPI
// return 3.0 if it's XXHDPI
// return 4.0 if it's XXXHDPI
//
                    if (density == (float) 0.75) {
                        marginright= 500;
                    }
                    else if (density == (float) 1.0){
                        marginright = 250;
                    }
                    else if (density == (float) 1.5){
                        marginright = 375;
                    }
                    else if (density == (float) 2.0){
                        marginright = 500;
                        Toast.makeText(getApplication() ,"from is  = "+density +"\n margin = "+marginright, Toast.LENGTH_SHORT ).show();
                    }
                    else if (density == (float) 3.0){
                        marginright = 750;
                    }
                    else if (density == (float) 4.0){
                        marginright = 1000;
                    }
                    else {}


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        burger.setWidth(20);
                        burger.setHeight(20);
                        burger.setBackgroundResource(R.drawable.cross);

                      relativeParams = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            relativeParams = new RelativeLayout.LayoutParams(
                                    new RelativeLayout.LayoutParams(
                                            RelativeLayout.LayoutParams.MATCH_PARENT,
                                            RelativeLayout.LayoutParams.WRAP_CONTENT));
                        }
                        relativeParams.setMargins(marginleft, 0,marginright, 0);
                        //navigation.getLayoutParams().width=marginright;
                    }
                    n=1;

                    animation1 =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                    navigation.startAnimation(animation1);

                    navigation.setVisibility(View.VISIBLE);


                    animation1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

//                            leftmenu.setLayoutParams(relativeParams);
//                            leftmenu.requestLayout();
//                            navigation.offsetLeftAndRight(navigation.getLayoutParams().width);
//                            navigation.invalidate();
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            leftmenu.setLayoutParams(relativeParams);
                            leftmenu.requestLayout();

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    animationmain =
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.main);
                    leftmenu.startAnimation(animationmain);

                    animationmain.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {


                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

//                    ObjectAnimator objectAnimator= ObjectAnimator.ofFloat(leftmenu, "translationX", 0, -70);
//                    objectAnimator.setDuration(1000);
//                    objectAnimator.start();


                }
                else {


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        burger.setBackgroundResource(R.drawable.burger);


                        relativeParams.setMargins(0, 0, 0, 0);

                        Animation animationmainback =
                                AnimationUtils.loadAnimation(getApplicationContext(), R.anim.mainback);
                        leftmenu.startAnimation(animationmainback);
                        animationmainback.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                leftmenu.setLayoutParams(relativeParams);
                                leftmenu.requestLayout();
                                navigation.setVisibility(View.GONE);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
//                        navigation.setVisibility(View.GONE);


                    animation1=
                            AnimationUtils.loadAnimation(getApplicationContext(), R.anim.backslide);
                    navigation.startAnimation(animation1);

                    animation1.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

//                            leftmenu.setLayoutParams(relativeParams);
//                            leftmenu.requestLayout();

//                            navigation.offsetLeftAndRight(-(navigation.getLayoutParams().width) );
//                            navigation.invalidate();

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });





                    }
                    n=0;
                }
            }
        });


        ////NavigationItemClickLsteners...
          login.setOnClickListener(new View.OnClickListener() {
                 @Override
          public void onClick(View v) {
        Toast.makeText(getApplicationContext() , "u clicked "+login.getText() ,Toast.LENGTH_SHORT).show();
          }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+register.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+menu.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        promotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+promotion.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+track.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        pages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+pages.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+help.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+language.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        helpcentre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+helpcentre.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+account.getText() ,Toast.LENGTH_SHORT).show();
            }
        });
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "u clicked "+report.getText() ,Toast.LENGTH_SHORT).show();
            }
        });




        ///NavigationEnds here...




}//OnCreateEnds





    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                            if(p==0) {
                            Picasso.with(MainActivity.this).load(sliderimage1)
                                    .into(sliderBack, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            mainsliderback.setBackgroundDrawable(sliderBack.getDrawable());

                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    });

//                                sliderBack.setScaleType(ImageView.ScaleType.FIT_XY);
//                                sliderBack.setAdjustViewBounds(true);
                          // Toast.makeText(MainActivity.this, "1st " , Toast.LENGTH_SHORT).show();
                                p++;
                            }
                            else if (p==1){
                                Picasso.with(getApplicationContext()).load(sliderimage2).into(sliderBack, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        mainsliderback.setBackgroundDrawable(sliderBack.getDrawable());

                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });
//                                sliderBack.setScaleType(ImageView.ScaleType.FIT_XY);

                                //    Toast.makeText(getApplicationContext(), "2 " , Toast.LENGTH_SHORT).show();

                                p++;
                            }
                            else {
                                Picasso.with(getApplicationContext()).load(sliderimage3).into(sliderBack, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        mainsliderback.setBackgroundDrawable(sliderBack.getDrawable());

                                    }

                                    @Override
                                    public void onError() {

                                    }
                                });
//                                sliderBack.setScaleType(ImageView.ScaleType.FIT_XY);

                                //   Toast.makeText(getApplicationContext(), "3 " , Toast.LENGTH_SHORT).show();

                                p=0;
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
    }


    public void prepareFeedItemListData(){
//        parser = new JSONParsingClass(MainActivity.this);
        response("https://api.myjson.com/bins/x3f23");

    }

//////parsingStartsHere...
    public void response (String url){
    Log.e("hp", "in response start ");

    JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
            url, null,
            new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    parseData(response);
//                        Log.v("InGood" , "Before Hide response ");
//                        Toast.makeText(getApplicationContext() , " from parse ",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyLog.d(TAG, "Error: " + error.getMessage());
            Toast.makeText(getApplicationContext() , " from parse error ",Toast.LENGTH_SHORT).show();


        }
    });
    AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);

}

    public void parseData (JSONObject response){
//
//        Log.d("InParseData" , "Before Hide response ");
//        Toast.makeText(context , " from parse data "+response,Toast.LENGTH_SHORT).show();

        if (response!=null){
            try {
                feedItemArr.clear();
                JSONArray sliderImageArray = response.getJSONArray("array_slide_show");
                sliderimage1 = sliderImageArray.getJSONObject(0).getString("url");
                sliderimage2 = sliderImageArray.getJSONObject(1).getString("url");
                sliderimage3 = sliderImageArray.getJSONObject(2).getString("url");
                callAsynchronousTask();
//
                JSONArray sliderItemArray = response.getJSONArray("array_silder_items");
//
                for (int i= 0 ; i<sliderItemArray.length(); i++){
                    JSONObject sliderObject = sliderItemArray.getJSONObject(i);

                    sliderItems = new SliderItems(MainActivity.this);

                    sliderItems.setBackImage(sliderObject.getString("slider_image"));
                    sliderItems.setLogo(sliderObject.getString("slider_logo"));
                    sliderItems.setName(sliderObject.getString("slider_brand_name"));
                    sliderItems.setDescription(sliderObject.getString("slider_brand_description"));
                    sliderItems.setItemPrice(sliderObject.getString("slider_brand_item_price"));

                    sliderItemArr.add(sliderItems);
//                    Toast.makeText(getApplicationContext() , " from parse data "+sliderItems.getName()+"\n 2nd is  = "+sliderItems.getItemPrice(),Toast.LENGTH_LONG).show();


                }
                viewPager.setAdapter(new CustomPagerAdapter(MainActivity.this , sliderItemArr.size() ,sliderItemArr) );


                JSONArray FoodFeedArray = response.getJSONArray("array_food_feed");

                for (int i= 0 ; i<FoodFeedArray.length(); i++){
                    JSONObject feedObject = FoodFeedArray.getJSONObject(i);
                    foodFeed = new FoodFeed();
                    foodFeed.setFeedLogo(feedObject.getString("brand_logo"));
                    foodFeed.setFeedName(feedObject.getString("brand_name"));
                    foodFeed.setFeedTime(feedObject.getString("time"));
                    foodFeed.setFeedDescEnglish(feedObject.getString("description_eng"));
                    foodFeed.setFeedDescArabic(feedObject.getString("description_arb"));
                    foodFeed.setFeedImage(feedObject.getString("food_itemm_image"));
                    foodFeed.setFeedLikes(feedObject.getString("likes"));
                    foodFeed.setFeedShares(feedObject.getString("shares"));
                    foodFeed.setFeedOrders(feedObject.getString("orders"));

                    feedItemArr.add(foodFeed);
                    //feedItemAdapter.notifyDataSetChanged();
                }
                recyclerView.setAdapter(feedItemAdapter);
                Log.e("hp",""+feedItemArr.size());
//                String name = response.getString("name");
//                JSONObject obj = response.getJSONObject("phone");

//


            }
            catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });


            }
        }
        //if ends here..
        else {
            Log.e(TAG, "Couldn't get json from server.");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(),
                            "Couldn't get json from server. Check LogCat for possible errors!",
                            Toast.LENGTH_LONG)
                            .show();
                }
            });

        }


    }//







    /////parsingEndsHere..
}
