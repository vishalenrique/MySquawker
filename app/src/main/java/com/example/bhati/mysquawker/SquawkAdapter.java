package com.example.bhati.mysquawker;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bhati.mysquawker.provider.MySquawkContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bhati on 8/2/2017.
 */

public class SquawkAdapter extends RecyclerView.Adapter<SquawkAdapter.SquawkViewHolder> {

    private static final long MIN_MILLI =60*1000;
    private static final long HOUR_MILLI = 60*MIN_MILLI;
    private static final long DAY_MILLI=24*HOUR_MILLI;
    private static SimpleDateFormat sDateFormat = new SimpleDateFormat("dd MMM");

    Context context;
    Cursor cursor;

    public SquawkAdapter(Context context) {
        this.context = context;
    }

    @Override
    public SquawkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new SquawkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SquawkViewHolder holder, int position) {
        cursor.moveToPosition(position);
        String name=cursor.getString(cursor.getColumnIndex(MySquawkContract.COLUMN_AUTHOR));
        String authorKey=cursor.getString(cursor.getColumnIndex(MySquawkContract.COLUMN_AUTHOR_KEY));
        String message=cursor.getString(cursor.getColumnIndex(MySquawkContract.COLUMN_MESSAGE));
        long date=cursor.getLong(cursor.getColumnIndex(MySquawkContract.COLUMN_DATE));
        String dateString;

        long currentTime=System.currentTimeMillis();

        if((currentTime-date)<HOUR_MILLI){
            date=Math.round((currentTime-date)/MIN_MILLI);
            dateString=String.valueOf(date)+"m";
        }else if((currentTime-date)<DAY_MILLI){
            date=Math.round((currentTime-date)/DAY_MILLI);
            dateString=String.valueOf(date)+"h";
        }else{
            Date date1=new Date(date);
            dateString=sDateFormat.format(date1);
        }
        dateString = "\u2022 " + dateString;
        int drawable;
        switch (authorKey){
            case MySquawkContract.ASSER_KEY:drawable=R.drawable.asser;
                break;
            case MySquawkContract.CEZANNE_KEY: drawable=R.drawable.cezanne;
                break;
            case MySquawkContract.JLIN_KEY: drawable=R.drawable.jlin;
                break;
            case MySquawkContract.LYLA_KEY: drawable=R.drawable.lyla;
                break;
            case MySquawkContract.NIKITA_KEY: drawable=R.drawable.nikita;
                break;
            default:drawable=R.drawable.test;
        }
        holder.authorName.setText(name);
        holder.date.setText(dateString);
        holder.authorMessage.setText(message);
        holder.imageView.setImageResource(drawable);
    }

    @Override
    public int getItemCount() {
        return cursor==null?0:cursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
            cursor=newCursor;
        notifyDataSetChanged();
    }

    public class SquawkViewHolder extends RecyclerView.ViewHolder{

        final ImageView imageView;
        final TextView authorName;
        final TextView date;
        final TextView authorMessage;

        public SquawkViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.imageView);
            authorName= (TextView) itemView.findViewById(R.id.authorName);
            date= (TextView) itemView.findViewById(R.id.date);
            authorMessage= (TextView) itemView.findViewById(R.id.authorMessage);
        }
    }
}
