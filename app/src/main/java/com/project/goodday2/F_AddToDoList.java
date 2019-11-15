package com.project.goodday2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class F_AddToDoList extends Fragment {

    private EditText etxt_title;
    private EditText etxt_detail;
    private Spinner sp_day;
    private RadioGroup RG_cycle;


    public F_AddToDoList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_f__add_to_do_list, container, false);
        Button btn_submit = v.findViewById(R.id.btn_submit);
        etxt_title = v.findViewById(R.id.etxt_title);
        etxt_detail = v.findViewById(R.id.etxt_detailInfo);
        sp_day = v.findViewById(R.id.sp_selectday);
        RG_cycle = v.findViewById(R.id.rg_cycle);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, setDaysOfMonth());
        sp_day.setAdapter(arrayAdapter);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDoItem i = new ToDoItem();

                i.setTitle(etxt_title.getText().toString());
                i.setMsg(etxt_detail.getText().toString());
                i.setTime(MainActivity.calController.getCurrentTime("yyyyMM"));
                i.setDay(String.valueOf(sp_day.getSelectedItemPosition()+1));
                i.setCycle(RG_cycle.getCheckedRadioButtonId());

                MainActivity.crudMethod.addItem(i);
                MainActivity.updateRVItem(MainActivity.selectedYear+MainActivity.selectedMonth);
                onDestroy();

            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroy();

    }

    public void destroy(){
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().remove(F_AddToDoList.this).commit();
        manager.popBackStack();
        MainActivity.fab.show();
    }

    public ArrayList<Integer> setDaysOfMonth(){
        ArrayList<Integer> days = new ArrayList<>();
        int max = MainActivity.calController.getDaysOfMonth();
        for(int i  = 1 ; i <= max; i++){
            days.add(i);
        }
        return days;
    }
}
