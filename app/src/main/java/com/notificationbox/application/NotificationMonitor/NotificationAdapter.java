
package com.notificationbox.application.NotificationMonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.notificationbox.application.R;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {

    // NotificationInfo n = new NotificationInfo();
    private Context context;
    private ArrayList<String> notificationparentlist;
//    private ArrayList<CharSequence> notificationchildlist;
//    private NotificationMonitor mNotificationMonitor = null;
    
    public NotificationAdapter(Context context,ArrayList<String> appname){
        this.context = context;
        notificationparentlist = appname;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return notificationparentlist.size();
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return notificationparentlist.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(convertView == null){
            LayoutInflater vi = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            LayoutInflater li = LayoutInflater.from(context);
            convertView = vi.inflate(R.layout.notificationparent_item, null);
            TextView appname = (TextView) convertView.findViewById(R.id.appname);
            appname.setText(notificationparentlist.get(position));
//        }
        return convertView;
    }
    
//    static class ViewHolder {
//        TextView appName;
//
//        ImageView appIcon;
//    }

}
