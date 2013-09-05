package com.example.hh;

import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main1 extends Activity {
	TextView ouTfio,ouTdate,ouTsex,ouTposition,
	ouTzp,ouTtelephone,ouTemail;
	MyData newData;//инициализация объекта MyData
	Button btn;//кнопка отправить
	EditText send;// для ввода текста
	String butok;
    /** Called when the activity is first created. */
    @SuppressLint("SimpleDateFormat")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        ouTfio=(TextView) findViewById(R.id.outfio);
        ouTdate=(TextView) findViewById(R.id.outdate);
        ouTsex=(TextView) findViewById(R.id.outsex);
        ouTposition=(TextView) findViewById(R.id.outposition);
        ouTzp=(TextView) findViewById(R.id.outzp);
        ouTtelephone=(TextView) findViewById(R.id.outtelephone);
        ouTemail=(TextView) findViewById(R.id.outemail);
        btn=(Button) findViewById(R.id.twoactbtn);
        send=(EditText) findViewById(R.id.twoactsend);
        //scrollbar на Edittext,кусок взят из интернета
        send.setOnTouchListener(new OnTouchListener() {
              public boolean onTouch(View view, MotionEvent event) {
                   // TODO Auto-generated method stub
                   if (view.getId() ==R.id.twoactsend) {
                       view.getParent().requestDisallowInterceptTouchEvent(true);
                       switch (event.getAction()&MotionEvent.ACTION_MASK){
                       case MotionEvent.ACTION_UP:
                           view.getParent().requestDisallowInterceptTouchEvent(false);
                           break;
                       }
                   }
                   return false;
               }
           });
        //Получить данные от предыдущего Activity
        newData=(MyData)getIntent().getParcelableExtra("MyData");
        if (newData.getdate()==0)
        {butok="не выбрано";}
        else
        {butok=new SimpleDateFormat("dd.MM.yyyy").format(newData.getdate());}
        //вывод данных
        ouTfio.setText(newData.getfio());
        ouTdate.setText(butok);
        ouTsex.setText(newData.getsex());
        ouTposition.setText(newData.getposition());
        ouTzp.setText(newData.getzp());
        ouTtelephone.setText(newData.gettelephone());
        ouTemail.setText(newData.getemail());
        //отправка ответа через результат 
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent().putExtra("send",send.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
    }
}