package com.crowdsensing.sensordatacollector.view.myprojects;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.crowdsensing.sensordatacollector.R;
import com.crowdsensing.sensordatacollector.data.Project;
import com.crowdsensing.sensordatacollector.data.Sensor;
import com.crowdsensing.sensordatacollector.data.remote.SendData;
import com.crowdsensing.sensordatacollector.utils.Util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateProjectActivity extends AppCompatActivity implements
        SensorListAdapter.OnCheckedListener, View.OnClickListener, DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener
{

    public static final String ACTION_DISPLAY_SENSORS = "ACTION_DISPLAY_SENSORS";

    private SensorManager mSensorManager;
    private List<Sensor> selectedSensorList;
    List<Sensor> sensorList;
    private int datePickerInput = 0;

    private Toolbar toolbar;
    private SendData sendData;

    private EditText projectNameEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private EditText startTimeEditText;
    private EditText endTimeEditText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT_WATCH)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setTitle("Create Project");
        setSupportActionBar(toolbar);

        projectNameEditText = findViewById(R.id.projectNameEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        endDateEditText = findViewById(R.id.endDateEditText);
        startTimeEditText = findViewById(R.id.startTimeEditText);
        endTimeEditText = findViewById(R.id.endTimeEditText);

        init();

        sendData = new SendData(this);
        sensorList = Util.getAllSensors(this);
        selectedSensorList = new ArrayList<>();

        ListView listView = findViewById(R.id.listView);
        // set the adapter to fill the data in ListView
        SensorListAdapter customAdapter = new SensorListAdapter(getApplicationContext(), sensorList);
        customAdapter.setOnCheckedListener(this);
        listView.setAdapter(customAdapter);

        //CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.checkedTextView);
    }

    private void init(){
        startDateEditText.setOnClickListener(this);
        endDateEditText.setOnClickListener(this);
        startTimeEditText.setOnClickListener(this);
        endTimeEditText.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startDateEditText.setShowSoftInputOnFocus(false);
            endDateEditText.setShowSoftInputOnFocus(false);
            startTimeEditText.setShowSoftInputOnFocus(false);
            endTimeEditText.setShowSoftInputOnFocus(false);
        } else {
            try {
                final Method method = EditText.class.getMethod(
                        "setShowSoftInputOnFocus"
                        , new Class[]{boolean.class});
                method.setAccessible(true);
                method.invoke(startDateEditText,endDateEditText, startTimeEditText, endTimeEditText, false);
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private void showDatePicker(){
        final Calendar myCalendar = Calendar.getInstance();
        new DatePickerDialog(this, this, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void showTimePicker(){
        final Calendar myCalendar = Calendar.getInstance();
        new TimePickerDialog(this, this, myCalendar.get(Calendar.HOUR_OF_DAY),
                myCalendar.get(Calendar.MINUTE), true).show();
    }

    private void sendProject(){

        Project project = new Project();
        project.name = projectNameEditText.getText().toString();
        project.sensorList = selectedSensorList.toString();
        sendData.sendProject(project);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemChecked(int position, boolean isChecked) {
        if(isChecked){
            selectedSensorList.add(sensorList.get(position));
        }else {
            selectedSensorList.remove(sensorList.get(position));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.actionDone:
                sendProject();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        datePickerInput = v.getId();
        switch (v.getId()){
            case R.id.startDateEditText:
                showDatePicker();
                break;
            case R.id.endDateEditText:
                showDatePicker();
                break;
            case R.id.startTimeEditText:
                showTimePicker();
                break;
            case R.id.endTimeEditText:
                showTimePicker();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        switch (datePickerInput){
            case R.id.startDateEditText:
                startDateEditText.setText(dayOfMonth + "/" + month + 1 + "/" +  year);
                break;
            case R.id.endDateEditText:
                endDateEditText.setText(dayOfMonth + "/" + month + 1+ "/" +  year);
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        switch (datePickerInput){
            case R.id.startTimeEditText:
                startTimeEditText.setText(hourOfDay + " : "  + minute);
                break;
            case R.id.endTimeEditText:
                endTimeEditText.setText(hourOfDay + " : "  + minute);
                break;
        }
    }
}
