package com.project.goodday2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class DetailInfoActivity extends Activity {

    private TextView title;
    private TextView msg;
    private TextView time;
    private ToDoItem selectedItem = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail_info);

        title = findViewById(R.id.dt_txt_title);
        msg = findViewById(R.id.dt_txt_info);
        time = findViewById(R.id.dt_txt_time);

        selectedItem = (ToDoItem)getIntent().getSerializableExtra("seletedItem");
        title.setText("제목 : "+selectedItem.getTitle());
        msg.setText("세부내용 : "+selectedItem.getMsg());
        time.setText("날짜 : "+selectedItem.getTime()+selectedItem.getDay());

    }

    public void OnCLose(View v){
        finish();
    }

    public void OnDeleteItem(View v){
        if(selectedItem != null) {
            Log.d("onDElete---------" ,"asdasd");
            MainActivity.crudMethod.deleteItem(selectedItem);
            MainActivity.updateRVItem(MainActivity.selectedYear+MainActivity.selectedMonth);
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       // return super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
         return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
