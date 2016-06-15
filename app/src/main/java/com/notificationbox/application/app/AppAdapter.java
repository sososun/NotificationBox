
package com.notificationbox.application.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import com.notificationbox.application.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AppAdapter extends BaseAdapter {

    Context context;
    ArrayList<AppInfo> dataList = new ArrayList<AppInfo>();
    private static HashMap<Integer, Integer> isSelected = new HashMap<Integer, Integer>();
    public static ArrayList<String> cancellist = new ArrayList<String>();
    public static HashMap<String, String> cancelmap = new HashMap<String, String>();

    public AppAdapter(Context context, ArrayList<AppInfo> applist)
    {
        this.context = context;
        dataList.clear();
        for (int i = 0; i < applist.size(); i++)
        {
            dataList.add(applist.get(i));
        }
        if (isSelected.size() == 0) {
            for (int i = 0; i < applist.size(); i++)
            {
                isSelected.put(i, 1);
            }
        }
    }

    @Override
    public int getCount() {

        return dataList.size();
    }

    @Override
    public Object getItem(int position) {

        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View v = convertView;
        if (v == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.app_item, null);
            v.setClickable(true);
            viewHolder.appName = (TextView) v.findViewById(R.id.appName);
            viewHolder.appIcon = (ImageView) v.findViewById(R.id.icon);
            viewHolder.switch1 = (Switch) v.findViewById(R.id.switch1);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (viewHolder.appName != null) {
            viewHolder.appName.setText(dataList.get(position).getAppName());
        }
        if (viewHolder.appIcon != null) {
            viewHolder.appIcon.setImageDrawable(dataList.get(position).getAppIcon());
        }
        viewHolder.switch1.setOnCheckedChangeListener(null);
        if (isSelected.get(position) == 0) {
            viewHolder.switch1.setChecked(true);
        } else if (isSelected.get(position) == 1) {
            viewHolder.switch1.setChecked(false);
        }
        viewHolder.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cancellist.add(dataList.get(position).getPackageName());
                    isSelected.put(position, 0);
                } else {
                    cancellist.remove(dataList.get(position).getPackageName());
                    isSelected.put(position, 1);
                }
            }
        });

        return v;
    }

    static class ViewHolder {
        TextView appName;

        ImageView appIcon;

        Switch switch1;
    }
}
