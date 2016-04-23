package com.g4.progark.battleships.controllers.menus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.g4.progark.battleships.R;

/**
 * Created by rout on 18.04.2016.
 */
public class ListAdapter extends ArrayAdapter<String> {
    int vg;
    String[] game_list;
    Context context;
    public ListAdapter(Context context,int vg,int id,String[] game_list){
        super(context,vg,id,game_list);
        this.context= context;
        this.game_list= game_list;
        this.vg=vg;

    }
    static class ViewHolder{
        public TextView txtMode;
        public TextView txtDetails;

    }

    public View getView(int position,View convertView,ViewGroup parent){
        View rowView=convertView;
        if(rowView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView=inflater.inflate(vg,parent,false);
            ViewHolder holder=new ViewHolder();
            holder.txtMode=(TextView)rowView.findViewById(R.id.txtmode);
            holder.txtDetails=(TextView)rowView.findViewById(R.id.txtdetails);
            rowView.setTag(holder);
        }
        String[] items=game_list[position].split("_");
        ViewHolder holder=(ViewHolder)rowView.getTag();
        holder.txtMode.setText(items[0]);
        holder.txtDetails.setText(items[1]);
        return rowView;
    }


}
