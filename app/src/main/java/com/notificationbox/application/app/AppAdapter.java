
package com.notificationbox.application.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;


import com.notificationbox.application.BaseContact;
import com.notificationbox.application.R;
import com.notificationbox.application.db.CancelListDBHelper;

import java.util.ArrayList;

public class AppAdapter extends BaseAdapter {

    Context context;
    ArrayList<AppInfo> dataList = new ArrayList<AppInfo>();
    CancelListDBHelper cancelListdbHelper;


    public AppAdapter(Context context, ArrayList<AppInfo> applist)
    {
        this.context = context;
        dataList.clear();
        cancelListdbHelper = new CancelListDBHelper(context);
        for (int i = 0; i < applist.size(); i++)
        {
            dataList.add(applist.get(i));
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
        if (getCheckStatus(position)) {
            viewHolder.switch1.setChecked(true);
        } else {
            viewHolder.switch1.setChecked(false);
        }
        viewHolder.switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    BaseContact.cancellist.add(dataList.get(position).getPackageName());
                    Log.e("SXY","add:"+BaseContact.cancellist.size());
                    cancelListdbHelper.insertCancelListDB(dataList.get(position).getPackageName());
                    saveCheckStatus(position,true);
                } else {
                    BaseContact.cancellist.remove(dataList.get(position).getPackageName());
                    Log.e("SXY","remove:"+BaseContact.cancellist.size()+"");
                    cancelListdbHelper.deleteCancelListDB(dataList.get(position).getPackageName());
                    saveCheckStatus(position,false);
                }
            }
        });

        return v;
    }
    private void saveCheckStatus(int position,boolean isCheck){
        BaseContact.setBooleanSharedPreferences(context,dataList.get(position).getPackageName(),isCheck);
    }
    private boolean getCheckStatus(int position){
        return BaseContact.getBooleanSharedPreferences(context,dataList.get(position).getPackageName(), false);
    }
    static class ViewHolder {
        TextView appName;

        ImageView appIcon;

        Switch switch1;
    }
}
