package com.example.hh;




import android.os.Parcel;
import android.os.Parcelable;
//класс для корректной отправки данных с 1 на 2 Activity
public class MyData implements Parcelable{
private static String fio;
private static long date;
private static String sex;
private static String position;
private static String zp;
private static String telephone;
private static String email;

public MyData (String fio,long date,String sex,String position,String zp,String telephone,String email)
{
MyData.fio=fio;
MyData.date=date;
MyData.sex=sex;
MyData.position=position;
MyData.zp=zp;
MyData.telephone=telephone;
MyData.email=email;
}
public String getfio() {return fio;}
public long getdate() {return date;}
public String getsex(){return sex;}
public String getposition(){return position;}
public String getzp(){return zp;}
public String gettelephone(){return  telephone;}
public String getemail(){return email;}
@Override
public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
}
@Override
public void writeToParcel(Parcel dest, int flags) {
	// TODO Auto-generated method stub
	dest.writeString(fio);
	dest.writeLong(date);
	dest.writeString(sex);
	dest.writeString(position);
	dest.writeString(zp);
	dest.writeString(telephone);
	dest.writeString(email);
}

public static final Parcelable.Creator<MyData> CREATOR=new Creator<MyData>() {
	
	@Override
	public MyData[] newArray(int size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MyData createFromParcel(Parcel source) {
		// TODO Auto-generated method stub
		return new MyData(fio, date, sex, position, zp, telephone, email);
	}
};

}