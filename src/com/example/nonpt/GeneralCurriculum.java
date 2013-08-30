package com.example.nonpt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.R;
import com.example.sw_wellfitsquare.ReadData;

public class GeneralCurriculum extends Activity implements OnItemClickListener, ReadData{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_curriculum);
		
		//(����) �ｺ�ⱸ �̸�, � �̸�   - (����) ��Ƽ�̵�� �ڷ�,  �ｺ�ⱸ ����, � ����
		ArrayList<Member> arMemberList = new ArrayList<Member>();

	    arMemberList.add(new Member(R.drawable.sample_general,"������"," �ö���","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.youtube.com/watceefdd&334234idj"));
	    arMemberList.add(new Member(R.drawable.sample_general,"ȭ����"," ������","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.daum.net"));
	    arMemberList.add(new Member(R.drawable.sample_general,"ȭ����"," �ö���","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.daum.net"));
	    arMemberList.add(new Member(R.drawable.sample_general,"������"," �ö���","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.daum.net"));
	    arMemberList.add(new Member(R.drawable.sample_general,"�����"," �ö���","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.daum.net"));
	    arMemberList.add(new Member(R.drawable.sample_general,"�ݿ���"," �ö���","��ü� �ⱸ","������� ���� ������ ��� ���","http://www.daum.net"));
	    
		
	    MemberAdapter myMemberAdapter = new MemberAdapter(this, arMemberList);
	    ListView paperlistview = (ListView) findViewById(R.id.general_list);
	    paperlistview.setAdapter(myMemberAdapter);
	    
	    paperlistview.setOnItemClickListener(this);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK){
			return this.getParent().onKeyDown(keyCode, event);
		}
		return false;
	}
	
	
	class Member{
		int image;
		String tools_name;
		String sports_name;
		String tools_way;
		String sports_content;
		String sports_data;

		public Member(int _image, String _name)
		{
			tools_name = _name;
			image = _image;
		}
		
		public Member(int _image, String tools_name, String sports_name, String tools_way, String sports_content, String sports_data)
		{
			this.image = _image;
			this.tools_name = tools_name;
			this.sports_name = sports_name;
			this.tools_way = tools_way;
			this.sports_content = sports_content;
			this.sports_data = sports_data;
		}
	}
	
	int flag;
	class MemberAdapter extends BaseAdapter{

		LayoutInflater mInflater;
		ArrayList<Member> arSrc;
		Context _context;
		
		public MemberAdapter(Context context, ArrayList<Member> personListItem)	{
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arSrc = personListItem;
			_context = context;
		}
		
		public int getCount() {
			return arSrc.size();
		}

		public Object getItem(int position) {
			return arSrc.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			
			
			
			int res = R.layout.tools_instructions_list;
			convertView = mInflater.inflate(res, parent,false);
			
			ImageView image = (ImageView)convertView.findViewById(R.id.image);
			image.setImageResource(arSrc.get(position).image);
			
			TextView tools_name = (TextView)convertView.findViewById(R.id.tools_name);
			tools_name.setText(arSrc.get(position).tools_name);
			
			TextView sports_name = (TextView)convertView.findViewById(R.id.sports_name);
			sports_name.setText(arSrc.get(position).sports_name);
			
			TextView tools_way = (TextView)convertView.findViewById(R.id.tools_way);
			tools_way.setText(arSrc.get(position).tools_way);
			
			TextView sports_content = (TextView)convertView.findViewById(R.id.sports_content);
			sports_content.setText(arSrc.get(position).sports_content);
			
			TextView sports_data = (TextView)convertView.findViewById(R.id.sports_data);
			sports_data.setText(arSrc.get(position).sports_data);
			
			final String uri = arSrc.get(position).sports_data;
			
			sports_data.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(Intent.ACTION_VIEW,
					          Uri.parse(uri));
					 startActivity(intent);
				}
			});
			


			final LinearLayout linear_content = (LinearLayout)convertView.findViewById(R.id.linear_content); 
			
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(linear_content.getVisibility()==View.GONE)
					{
						flag=0;
					}else
					{
						flag=1;
					}
					
					if(flag==0)
					{
						linear_content.setVisibility(View.VISIBLE);
					}else
					{
						linear_content.setVisibility(View.GONE);
					}
				}
			});
			return convertView;
		}
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}
	@Override
	public void setData(List<HashMap<String, String>> HashList) {
	}
}