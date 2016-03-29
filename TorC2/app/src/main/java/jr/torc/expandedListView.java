package jr.torc;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 40114815 on 24/03/2016.
 *
 * help gained from youtube tutorial
 * and http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
 *
 */

public class expandedListView extends BaseExpandableListAdapter {

    private String[] headings;      //list to store the titles
    private String [][] allOrders;      //hashmap of all orders
    private Context context;

    private class ViewHolder {
        TextView text;
    }

    public expandedListView(String [] Headings, String [][] AllOrders, Context Context)
    {
        this.headings = Headings;       //setting up headings
        this.allOrders = AllOrders;     //setting up all orders
        this.context = Context;         //setting up context
    }

    @Override
    public int getGroupCount() {
        return headings.length;     //makes it only as big as needs be
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return allOrders[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headings[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return allOrders[groupPosition] [childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inf.inflate(R.layout.groups, parent, false);


            holder.text = (TextView) convertView.findViewById(R.id.headings);

            convertView.setTag(holder);
        }

        holder.text.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder h = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inf.inflate(R.layout.items, parent, false);

            h.text = (TextView) convertView.findViewById(R.id.child);

            convertView.setTag(h);
        }

        h.text.setText(getChild(groupPosition, childPosition).toString());
        
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
