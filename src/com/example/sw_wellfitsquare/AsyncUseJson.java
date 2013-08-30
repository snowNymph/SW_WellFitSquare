package com.example.sw_wellfitsquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncUseJson extends AsyncTask<HashMap<String,String>, Void , Void>{
	ReadData listener;
	Context mContext;
	List<HashMap<String,String>> HashList = new ArrayList<HashMap<String,String>>();
	int what;
	List<String> wantGap = new ArrayList<String>();
	
	public AsyncUseJson(Context listener,int what,List<String> wantGap){
		this.what = what;
		this.mContext = listener;
		this.listener = (ReadData)listener;
		this.wantGap = wantGap;
	}
	
	@Override
	protected Void doInBackground(HashMap<String, String>... params) {
		String C = null;
		switch(what){
		case ReadData.Insert:
			C = "insert";
			break;
		case ReadData.Select:
			C = "select";
			break;
		case ReadData.Update:
			C = "update";
			break;
		}
		
		HashMap<String, String> send = new HashMap<String,String>();
		send = params[0];
		JsonRequestPost client = new JsonRequestPost(send,"http://172.16.101.227:10001/"+C+".php");
		client.requestPost();
		HashList = client.jsonParse(wantGap);
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		if(what == ReadData.Select){
			if(HashList.get(0).get("error") != null){
				Toast.makeText(mContext, "������ ������ϴ�. �ٽ� �õ��� �ּ���", Toast.LENGTH_SHORT).show();
			}
			else if(HashList.get(0).get("null")!= null){ // ��Ʈ�� �� ���� 
				Toast.makeText(mContext, "�����Ͱ� �����ϴ�.", Toast.LENGTH_SHORT).show();
			}else{
				listener.setData(HashList);
			}
		}
		else{
			
			if(HashList.size()!=0)
			{
				if(HashList.get(0).get("error") != null){
					Toast.makeText(mContext, "������ ������ϴ�. �ٽ� �õ��� �ּ���", Toast.LENGTH_SHORT).show();
				}
				else{
					listener.setData(HashList);
				}
			}
		}
	}
}
