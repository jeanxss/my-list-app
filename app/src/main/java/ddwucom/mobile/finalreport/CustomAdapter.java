package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Data> dataList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, int layout, ArrayList<Data> dataList) {
        this.context = context;
        this.layout = layout;
        this.dataList = dataList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dataList.get(i).get_id();
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewGroup) {
        final int position = pos;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(layout, viewGroup, false);
            viewHolder = new ViewHolder();
            //viewHolder.textNo = convertView.findViewById(R.id.tvNo);
            viewHolder.imageView = convertView.findViewById(R.id.imgView);
            viewHolder.textName = convertView.findViewById(R.id.tvName);
            viewHolder.textLocation = convertView.findViewById(R.id.tvLocation);
            viewHolder.textTel = convertView.findViewById(R.id.tvTel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //viewHolder.textNo.setText( String.valueOf( dataList.get(position).get_id()) );
        viewHolder.imageView.setImageResource( dataList.get(position).getImage() );
        viewHolder.textName.setText( dataList.get(position).getName() );
        viewHolder.textLocation.setText( dataList.get(position).getLocation() );
        viewHolder.textTel.setText( dataList.get(position).getTel() );

        return convertView;
    }

    static class ViewHolder {
        //TextView textNo;
        ImageView imageView;
        TextView textName;
        TextView textLocation;
        TextView textTel;
    }

}
