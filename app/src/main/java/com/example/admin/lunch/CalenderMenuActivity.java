package com.example.admin.lunch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.admin.lunch.adapter.DateCalenderMenuAdapter;
import com.example.admin.lunch.adapter.EntreeCalenderMenuAdapter;
import com.example.admin.lunch.adapter.SandwichesCalenderMenuAdapter;
import com.example.admin.lunch.adapter.SingleSelectionAdapter;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalenderMenuActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    RecyclerView recyclerViewCalander, recyclerViewEntree, recyclerViewSandwiches, recyclerViewSalads,
            recyclerViewFruitsAndVeggies, recyclerViewBeverages;

    EntreeCalenderMenuAdapter entreeCalenderMenuAdapter;

    SandwichesCalenderMenuAdapter sandwichesCalenderMenuAdapter;

    ArrayList<String> entreeItemArrayList;
    private SingleSelectionAdapter dateCalenderMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_menu);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getMonths());
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        //recyclerView
        recyclerViewCalander = findViewById(R.id.recyclerViewCalander);
        recyclerViewEntree = findViewById(R.id.recyclerViewEntree);
        recyclerViewSandwiches = findViewById(R.id.recyclerViewSandwiches);
        recyclerViewSalads = findViewById(R.id.recyclerViewSalads);
        recyclerViewFruitsAndVeggies = findViewById(R.id.recyclerViewFruitsAndVeggies);
        recyclerViewBeverages = findViewById(R.id.recyclerViewBeverages);

        entreeItemArrayList = new ArrayList<>();
        entreeItemArrayList.add("1");
        entreeItemArrayList.add("1");

        Date date = getDateByMonth(spinner.getSelectedItemPosition());
        List<NameValue> list = getCal(date);

        //dates adapter set
        dateCalenderMenuAdapter = new SingleSelectionAdapter(CalenderMenuActivity.this, list);
        recyclerViewCalander.setAdapter(dateCalenderMenuAdapter);
        recyclerViewCalander.setNestedScrollingEnabled(false);
        recyclerViewCalander.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //entree adapter set
        entreeCalenderMenuAdapter = new EntreeCalenderMenuAdapter(CalenderMenuActivity.this, entreeItemArrayList);
        recyclerViewEntree.setAdapter(entreeCalenderMenuAdapter);
        recyclerViewEntree.setNestedScrollingEnabled(false);
        recyclerViewEntree.setLayoutManager(new LinearLayoutManager(CalenderMenuActivity.this));

        //recyclerViewSandwiches adapter set
        sandwichesCalenderMenuAdapter = new SandwichesCalenderMenuAdapter(CalenderMenuActivity.this, entreeItemArrayList);
        recyclerViewSandwiches.setAdapter(sandwichesCalenderMenuAdapter);
        recyclerViewSandwiches.setNestedScrollingEnabled(false);
        recyclerViewSandwiches.setLayoutManager(new LinearLayoutManager(CalenderMenuActivity.this));
        //recyclerViewSalads
        recyclerViewSalads.setAdapter(sandwichesCalenderMenuAdapter);
        recyclerViewSalads.setNestedScrollingEnabled(false);
        recyclerViewSalads.setLayoutManager(new LinearLayoutManager(CalenderMenuActivity.this));
        //recyclerViewFruitsAndVeggies
        recyclerViewFruitsAndVeggies.setAdapter(sandwichesCalenderMenuAdapter);
        recyclerViewFruitsAndVeggies.setNestedScrollingEnabled(false);
        recyclerViewFruitsAndVeggies.setLayoutManager(new LinearLayoutManager(CalenderMenuActivity.this));
        //recyclerViewBeverages
        recyclerViewBeverages.setAdapter(sandwichesCalenderMenuAdapter);
        recyclerViewBeverages.setNestedScrollingEnabled(false);
        recyclerViewBeverages.setLayoutManager(new LinearLayoutManager(CalenderMenuActivity.this));

    }

    List<NameValue> getCal(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int myMonth = cal.get(Calendar.MONTH);
        List<NameValue> nameValues = new ArrayList<>();
        while (myMonth == cal.get(Calendar.MONTH)) {
            NameValue nameValue = new NameValue();
            nameValue.dayNum = cal.get(Calendar.DAY_OF_MONTH);
            nameValue.dayString = getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
            nameValues.add(nameValue);
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return nameValues;
    }

    private String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    List<String> getMonths() {
        List<String> monthsList = new ArrayList<>();
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            monthsList.add(months[i]);
        }
        return monthsList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        List<NameValue> nameValues = getCal(getDateByMonth(position));
        dateCalenderMenuAdapter.clearAndSet(nameValues);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public Date getDateByMonth(int month) {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        int year = cal.get(Calendar.YEAR);
        return new Date(year, month, dayOfTheWeek);
    }
}
