
package com.notificationbox.application.NotificationMonitor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.notificationbox.application.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {

    // NotificationInfo n = new NotificationInfo();
    private Context context;
    private ArrayList<String> notificationparentlist;
    private ArrayList<NotificationInfo> notificationchildlist;
    private static int PARENT_ITEM = 1;
    private static int CHILD_ITEM = 2;
//    private NotificationMonitor mNotificationMonitor = null;
    
    public NotificationAdapter(Context context,ArrayList<String> appname,ArrayList<NotificationInfo> notificationInfos){
        this.context = context;
        notificationparentlist = appname;
        notificationchildlist = notificationInfos;
    }
    @Override
    public int getCount() {
        return notificationchildlist.size();
    }
    @Override
    public Object getItem(int position) {
        return notificationchildlist.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        ViewHolderFather viewHolderFather = null;
        ViewHolderChild viewHolderChild = null;
        if(convertView == null){
            if(type == PARENT_ITEM){
                viewHolderFather = new ViewHolderFather();
                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.notificationparent_item,null);
                viewHolderFather.appName = (TextView) convertView.findViewById(R.id.appname);
                convertView.setTag(viewHolderFather);
            }else if(type == CHILD_ITEM){
                viewHolderChild = new ViewHolderChild();
                LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = vi.inflate(R.layout.notificationchildren_item,null);
                viewHolderChild.title = (TextView) convertView.findViewById(R.id.notificationTitle);
                viewHolderChild.text = (TextView) convertView.findViewById(R.id.notificationText);
                viewHolderChild.subtext = (TextView) convertView.findViewById(R.id.notificationSubText);
                viewHolderChild.time = (TextView) convertView.findViewById(R.id.time);
                convertView.setTag(viewHolderChild);
            }
        }else {
            if(type == PARENT_ITEM){
                viewHolderFather = (ViewHolderFather) convertView.getTag();
            }else if (type == CHILD_ITEM){
                viewHolderChild = (ViewHolderChild) convertView.getTag();
            }
        }
        if(type == PARENT_ITEM){
            if(viewHolderFather.appName != null){
                viewHolderFather.appName.setText(notificationparentlist.get(position));
            }
        }else if(type == CHILD_ITEM){
            viewHolderChild.title.setText(notificationchildlist.get(position).getTitle());
            viewHolderChild.text.setText(notificationchildlist.get(position).getText());
            viewHolderChild.subtext.setText(notificationchildlist.get(position).getSubtext());
            viewHolderChild.time.setText(notificationchildlist.get(position).getTime());
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 2;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    static class ViewHolderFather {
        TextView appName;

        ImageView appIcon;
    }
    static class ViewHolderChild {

        TextView title;

        TextView subtext;

        TextView text;

        TextView time;
    }

}
