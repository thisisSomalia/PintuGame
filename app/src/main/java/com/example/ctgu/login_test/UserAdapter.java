package com.example.ctgu.login_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class UserAdapter extends ArrayAdapter<UsersInfo> {

    private int resourceId;

    private static HashMap<Integer,Boolean> isSelected;

    public UserAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<UsersInfo> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        UsersInfo user = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.userId = (TextView)view.findViewById(R.id.tv_id);
            viewHolder.userName = (TextView)view.findViewById(R.id.tv_name);
            viewHolder.userScore = (TextView)view.findViewById(R.id.tv_score);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        String s1=""+user.get_id();
        String s2=""+user.getScore();
        viewHolder.userId.setText(s1);
        viewHolder.userName.setText(user.getName());
        viewHolder.userScore.setText(s2);
        return view;
    }

    class ViewHolder{
        TextView userId;
        TextView userName;
        TextView userScore;
    }
}
