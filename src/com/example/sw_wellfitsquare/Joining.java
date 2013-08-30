package com.example.sw_wellfitsquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.R;

public class Joining extends Activity implements ReadData{
	
	Button send_btn,cancel_btn;
	EditText send_id;
	EditText send_pw;
	TextView send_phone;
	HashMap<String, String> main_has_join;
	HashMap<String,String> main_has_select ;
	int type = 0;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.joining);
	    initId();
	    cancel_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		send_btn.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				//find number;
				HashMap<String,String> ab = new HashMap<String,String>();
				ab.put("table", "member");
				ab.put("field","uid,id");
				ab.put("condition", "phone=\""+send_phone.getText().toString()+"\"");
				List<String> a = new ArrayList<String>();
				a.add("uid");
				a.add("id");
				AsyncUseJson c = new AsyncUseJson(Joining.this,ReadData.Select,a);
				type = 0;
				c.execute(ab);
			}
		});
	}
	private void initId() {
		send_btn = (Button)findViewById(R.id.join_add);
		cancel_btn = (Button)findViewById(R.id.join_cancel);
		send_id = (EditText)findViewById(R.id.join_id);
		send_pw= (EditText)findViewById(R.id.join_pw);
		send_phone= (TextView)findViewById(R.id.join_phone);
		TelephonyManager tMgr =(TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
    	String number_phone;
    	number_phone = tMgr.getLine1Number();
    	char[] s = new char[13];
    	s = number_phone.toCharArray();
    	number_phone = new String();
    	s[2] = '0';
    	for(int i=2;i<13;i++){
    		number_phone += String.valueOf(s[i]);
    	}
		send_phone.setText(number_phone);
	}
	@Override
	public void setData(List<HashMap<String, String>> HashList) {
		if(type == 0){
			//통과했으니 핸드폰 번호가 존재함
			String s = HashList.get(0).get("id");
			if(s != null){
				Toast.makeText(getBaseContext(), "이미 가입하셨습니다", Toast.LENGTH_SHORT).show();
			}
			else{
				//회원 update;
			}
			type = 1;
		}
	}
}
