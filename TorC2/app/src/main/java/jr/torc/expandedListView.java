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

    private class ViewHolder{
        TextView text;
    }

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

        ViewHolder vH = new ViewHolder();

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inf.inflate(R.layout.groups, parent, false);

            vH.text = (TextView) convertView.findViewById(R.id.headings);

            convertView.setTag(vH);

        } else {
            vH = (ViewHolder) convertView.getTag();
        }

        vH.text.setText(getGroup(groupPosition).toString());

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ViewHolder vH = new ViewHolder();

        if (convertView == null) {

            LayoutInflater inf = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            convertView = inf.inflate(R.layout.items, parent, false);

            vH.text = (TextView) convertView.findViewById(R.id.list_item);

            convertView.setTag(vH);
        } else {
            vH = (ViewHolder) convertView.getTag();
        }

        vH.text.setText(getChild(groupPosition, childPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
