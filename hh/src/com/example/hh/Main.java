package com.example.hh;

import java.text.SimpleDateFormat;
import java.util.Date;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class Main extends Activity {
	static final int DIALOG_DATE=0;
	int getflags_twoactivity=1;
	int myDay = 02;//���� �� ���������
	int myMonth = 03;//����� �� ���������
	int myYear = 1993;//��� �� ���������
	int intColor = Color.argb(127, 255, 0, 0);//����
	Date rdata,butdata;//����������� � �������� ����
	String gsex;//����� ����
	Button data,main1;//data-����� ����, main1-2 Activity
	DatePickerDialog  dtp;//������
	Resources res;//-Cancel
	long rdata_prov=0;
	String Cancel,prov_d;//������
	EditText edit1,edit2,edit3,edit4,edit5;//���� ��� �����
	Spinner spinner;//���������� ������
	Context context;//--
	AlertDialog.Builder ad;
	
	
	
	
	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        //�������������
		 res=getResources();
		 spinner=(Spinner) findViewById(R.id.spinner1);
		 edit1=(EditText) findViewById(R.id.editText1);
		 edit1.setOnEditorActionListener(new EditText.OnEditorActionListener() {

			 @SuppressWarnings("deprecation")
			@Override

			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			      if (actionId == EditorInfo.IME_ACTION_NEXT) {
			      if (data.getText()==res.getString(R.string.not_found))
			         {showDialog(DIALOG_DATE);}
			         return false;
			      }
			      return false;
			    }
			});
		 edit2=(EditText) findViewById(R.id.editText2);
		 edit3=(EditText) findViewById(R.id.editText3);
		 edit4=(EditText) findViewById(R.id.editText4);		
		 edit5=(EditText) findViewById(R.id.editText5);	
		dtp= new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
		//����� ��� ������
        Cancel=res.getString(R.string.cancel);
        prov_d=res.getString(R.string.not_found);
//����� ��� ����
		data= (Button) findViewById(R.id.button2);
		data.setBackgroundColor(android.R.color.widget_edittext_dark);	
		data.setTextColor(intColor);
		data.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DIALOG_DATE);//����������� ������� ����
		
			}
		});

		//����� ��� ������� Activity
	     main1= (Button) findViewById(R.id.butsend);
		 main1.setOnClickListener(new OnClickListener() {
					
					@SuppressWarnings("deprecation")
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
					
		 rdata = new Date(myYear-1900,myMonth,myDay);
		 //�������� ������� MyData ����� Intent
		 if (data.getText()==prov_d)
		 {rdata_prov=0;}
		 else
		 {rdata_prov=rdata.getTime();}
		 MyData Data=new MyData(edit1.getText().toString(),rdata_prov, gsex, edit2.getText().toString(),
		 edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString());
					Intent mainintent = new Intent(Main.this,Main1.class);
					mainintent.putExtra("MyData", Data);
					//����� ������� Activity
					startActivityForResult(mainintent,getflags_twoactivity);
					}
		});
		
		
//������� ��� ��������	
		ArrayAdapter<?> adapter= ArrayAdapter.createFromResource(this, R.array.array_sex,
		android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//�������		
spinner.setAdapter(adapter);
//�������� �������
spinner.setSelection(0);
//������� ��������
spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		gsex=spinner.getSelectedItem().toString();
		if (arg2==0)
		{
		    ((TextView) arg0.getChildAt(0)).setTextColor(intColor);
	        ((TextView) arg0.getChildAt(0)).setTextSize(16);
		}
		
	}
	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}
	});			
}//end OnCreate



//Dialog
	protected Dialog onCreateDialog(int id)
	{
	  switch(id)
	    {
	          case DIALOG_DATE:
	     dtp = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
         dtp.setButton(DialogInterface.BUTTON_NEGATIVE,Cancel ,cancelBTN);
         dtp.setOnDismissListener ( dismissListener );
         break;
	}
	return dtp;

	}
	protected DialogInterface.OnDismissListener dismissListener = new OnDismissListener( )
	{		
		@Override
		public void onDismiss ( DialogInterface dialog )
		{	
			// do your thang here			
		}
	};
	//���� ������ ������ ������
	protected DialogInterface.OnClickListener cancelBTN = new DialogInterface.OnClickListener( )
	{	
		public void onClick ( DialogInterface dialog, int which )
		{
			dialog.dismiss ( );
			data.setText(R.string.not_found);
			data.setTextColor(intColor);
		}
	};
	
	//���������� ������� ������ � ������� 
private OnDateSetListener myCallBack=new OnDateSetListener() {
	

	@SuppressWarnings("deprecation")
	@SuppressLint("SimpleDateFormat")
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		//����������� ���� 
		butdata = new Date(myYear-1900,myMonth,myDay);
		String butok=new SimpleDateFormat("dd.MM.yyyy").format(butdata.getTime());
		data.setTextColor(Color.BLACK);
		data.setText(butok);
		myYear=year;
		myMonth=monthOfYear;
		myDay=dayOfMonth;
		if (spinner.getSelectedItemId()==0)
		{
			//����� �� �������� ����
			spinner.performClick();	
		}
		
}
};//endDialog


protected void onActivityResult(int requestCode, int resultCode, Intent data) 
{
	if (data==null){return;}
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setTitle(R.string.twoactivity);
	builder.setMessage(data.getStringExtra("send"));
	builder.setCancelable(true);
	builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // ������ ��
	    @Override
	    public void onClick(DialogInterface dialog, int which) {
	        dialog.dismiss(); // ��������� ���������� ����					
	    }
	});
	AlertDialog dialog = builder.create();
	dialog.show();
	
};
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
