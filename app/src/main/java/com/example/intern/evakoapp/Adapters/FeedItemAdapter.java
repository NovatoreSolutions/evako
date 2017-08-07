package com.example.intern.Evakoapp.Adapters;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.Evakoapp.Activities.MainActivity;
import com.example.intern.Evakoapp.Models.FoodFeed;
import com.example.intern.Evakoapp.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class FeedItemAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<FoodFeed> feedList;
    MainActivity rs = new MainActivity();
    int viewtype=0;
    Context context;

    private static ClickListener clickListener;


    public FeedItemAdapter(List<FoodFeed> sliderItemsList , Context context ) {
        this.feedList = sliderItemsList;
        this.context= context;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public
        TextView posttitle, posttime , pashto, english ,likenum ,sharenum , ordernum;
        ImageView  postimage ;
        CircleImageView postititlemageview;

        public TextView getPosttime() {
            return posttime;
        }

        public void setPosttime(TextView posttime) {
            this.posttime = posttime;
        }

        public TextView getPosttitle() {
            return posttitle;
        }

        public void setPosttitle(TextView posttitle) {
            this.posttitle = posttitle;
        }

        public TextView getPashto() {
            return pashto;
        }

        public void setPashto(TextView pashto) {
            this.pashto = pashto;
        }

        public TextView getEnglish() {
            return english;
        }

        public void setEnglish(TextView english) {
            this.english = english;
        }

        public TextView getLikenum() {
            return likenum;
        }

        public void setLikenum(TextView likenum) {
            this.likenum = likenum;
        }

        public TextView getSharenum() {
            return sharenum;
        }

        public void setSharenum(TextView sharenum) {
            this.sharenum = sharenum;
        }

        public TextView getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(TextView ordernum) {
            this.ordernum = ordernum;
        }

        public ImageView getPostimage() {
            return postimage;
        }

        public void setPostimage(ImageView postimage) {
            this.postimage = postimage;
        }

        public CircleImageView getPostititlemageview() {
            return postititlemageview;
        }

        public void setPostititlemageview(CircleImageView postititlemageview) {
            this.postititlemageview = postititlemageview;
        }




        public MyViewHolder(View view) {
            super(view);

            postititlemageview = (CircleImageView) view.findViewById(R.id.imageview);
            posttitle = (TextView) view.findViewById(R.id.posttitle);
            posttime = (TextView) view.findViewById(R.id.posttime);
            pashto = (TextView) view.findViewById(R.id.pashto);
            english = (TextView) view.findViewById(R.id.english);
            postimage = (ImageView) view.findViewById(R.id.postimage);
            likenum = (TextView) view.findViewById(R.id.likenum);
            sharenum = (TextView) view.findViewById(R.id.sharenum);
            ordernum = (TextView) view.findViewById(R.id.ordernum);

//            rs.idmain= year.getId();
            view.setOnClickListener(this);
            // view.setOnClickListener((View.OnClickListener) this);
//            year.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // Log.v("Ali ", ""+rs.idmain);
//                     }
//            });title.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // Log.v("Ali ", ""+title.getText());
//                }
//            });
//            genre.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   // Log.v("Ali ", ""+genre.getText());
//                }
//            });

        }


        @Override
        public void onClick(View v) {

            clickListener.onItemClick(getAdapterPosition(),v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
       FeedItemAdapter.clickListener = clickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
            return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FoodFeed movie = feedList.get(position);

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.getPosttitle().setText(movie.getFeedName());

        Picasso.with(context).load(movie.getFeedLogo()).into(myViewHolder.postititlemageview);

        myViewHolder.getPosttime().setText(movie.getFeedTime());
        myViewHolder.pashto.setText(movie.getFeedDescArabic());
        myViewHolder.english.setText(movie.getFeedDescEnglish());
        Picasso.with(context).load(movie.getFeedImage()).into(myViewHolder.postimage);
        myViewHolder.likenum.setText(movie.getFeedLikes());
        myViewHolder.sharenum.setText(movie.getFeedShares());
        myViewHolder.ordernum.setText(movie.getFeedOrders());



    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }



    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }//InterfaceEndsHere




}
