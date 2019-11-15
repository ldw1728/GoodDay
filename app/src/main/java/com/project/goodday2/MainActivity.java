package com.project.goodday2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static boolean showandhidefab = true;

    static CalController calController = new CalController();
    static CRUD_Method crudMethod;
    static ArrayList<ToDoItem> items = new ArrayList<>();
    static String selectedYear = calController.getSelectedYear(), selectedMonth = calController.getSelectedMonth();

    private static RVAdapter_itemList rvAdapter;

    private TextView txt_selectedDate;
    static  FloatingActionButton fab;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initRV();


    }

    public static void updateRVItem(String selectedDate){
        ArrayList<ToDoItem> items = crudMethod.loadSelectedData(selectedDate);
        rvAdapter.addItem(items);
        rvAdapter.notifyDataSetChanged();
    }

    public void init(){
        crudMethod = new CRUD_Method(getFilesDir());
        getComponent();
        setEventListner();
        txt_selectedDate.setText(selectedYear+"년 "+selectedMonth+"월");
    }

    public void initRV(){
        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        rvAdapter = new RVAdapter_itemList();
        recyclerView.setAdapter(rvAdapter);
        updateRVItem(selectedYear+selectedMonth);

    }

    public void getComponent(){
        txt_selectedDate = findViewById(R.id.txt_selectedDate);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerV);
    }

    public void setEventListner(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment addToDoList = new F_AddToDoList();
                String tag = addToDoList.getClass().getSimpleName();

                getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                fragmentTransaction.add(R.id.frame_F_AddToDoList, addToDoList);
                fragmentTransaction.addToBackStack(tag);
                fragmentTransaction.commit();
                fab.hide();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
