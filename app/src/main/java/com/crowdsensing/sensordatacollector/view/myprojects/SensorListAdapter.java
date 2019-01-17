package com.crowdsensing.sensordatacollector.view.myprojects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Sensor;

import java.util.List;

public class SensorListAdapter extends BaseAdapter
{

    List<Sensor> sensorList;
    Context context;
    LayoutInflater inflter;
    String value;
    OnCheckedListener onCheckedListener;

    public void setOnCheckedListener(OnCheckedListener onCheckedListener){
        this.onCheckedListener = onCheckedListener;
    }

    public SensorListAdapter(Context context, List<Sensor> names)
    {
        this.context = context;
        this.sensorList = names;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount()
    {
        return sensorList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        convertView = inflter.inflate(R.layout.item_sensor, null);
        final CheckedTextView simpleCheckedTextView = convertView.findViewById(R.id.simpleCheckedTextView);
        simpleCheckedTextView.setText(sensorList.get(position).name);

        // perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (simpleCheckedTextView.isChecked())
                {
                    // set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(0);
                    simpleCheckedTextView.setChecked(false);
                }
                else
                {
                    // set cheek mark drawable and set checked property to true
                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.checked);
                    simpleCheckedTextView.setChecked(true);
                }
                onCheckedListener.onItemChecked(position, simpleCheckedTextView.isChecked());
            }
        });
        return convertView;
    }

    public interface OnCheckedListener{
        void onItemChecked(int position, boolean isChecked);
    }
}
