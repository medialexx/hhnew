package com.example.hh;

import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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
        //Получить данные от предыдущего Activity
        newData=(MyData)getIntent().getParcelableExtra("MyData");
        if (newData.getdate()==0)
        {butok="не выбрано";}
        else
        {butok=new SimpleDateFormat("dd.MM.yyyy").format(newData.getdate());}
        //Добавление данных через Intent в ArrayList 
        ouTfio.setText(newData.getfio());
        ouTdate.setText(butok);
        ouTsex.setText(newData.getsex());
        ouTposition.setText(newData.getposition());
        ouTzp.setText(newData.getzp());
        ouTtelephone.setText(newData.gettelephone());
        ouTemail.setText(newData.getemail());
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