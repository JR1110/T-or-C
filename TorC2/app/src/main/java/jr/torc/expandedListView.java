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

    private List<String> headings;      //list to store the titles
    private HashMap<String, List<String>> allOrders;      //hashmap of all orders
    private Context context;

    public expandedListView(List<String> Headings, HashMap<String, List<String>> AllOrders, Context Context)
    {
        this.headings = Headings;       //setting up headings
        this.allOrders = AllOrders;     //setting up all orders
        this.context = Context;         //setting up context
    }

    @Override
    public int getGroupCount() {
        return headings.size();     //makes it only as big as needs be
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.allOrders.get(this.headings.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return headings.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return allOrders.get(headings.get(groupPosition)).get(groupPosition);
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
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headingTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.groups, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.headings);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headingTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = infalInflater.inflate(R.layout.items, null);
        }

        TextView txtChild = (TextView) convertView.findViewById(R.id.child);
        txtChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
