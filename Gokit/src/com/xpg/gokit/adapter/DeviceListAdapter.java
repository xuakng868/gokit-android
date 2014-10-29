package com.xpg.gokit.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xpg.gokit.bean.ControlDevice;
import com.xpg.gokit.R;

public class DeviceListAdapter extends BaseAdapter {
	private List<ControlDevice> devicelist;
	private Context context;
	public DeviceListAdapter(Context c,List<ControlDevice>list){
		this.devicelist = list;
		this.context = c;
		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return devicelist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(context).inflate(R.layout.item_device, null);
		RelativeLayout rl_device = (RelativeLayout)v.findViewById(R.id.rl_device);
		RelativeLayout rl_title = (RelativeLayout)v.findViewById(R.id.rl_title);
		TextView tv_name = (TextView)v.findViewById(R.id.tv_device_name);
		TextView tv_info  = (TextView)v.findViewById(R.id.tv_info);
		TextView tv_tips = (TextView)v.findViewById(R.id.tv_tips);
		TextView tv_title = (TextView)v.findViewById(R.id.tv_title);
		ControlDevice cdevice = devicelist.get(position);
		if(!cdevice.isTitle()){
			
			if(cdevice.isNew()){
				tv_info.setText(cdevice.getMac());
				tv_name.setText((cdevice.getName().equals("")? "未知设备":cdevice.getName()));
				tv_tips.setText("未绑定");
				
				
			}else{
				if(cdevice.isOnline()){
					tv_info.setText(cdevice.getMac());
					tv_name.setText((cdevice.getName().equals("")? "未知设备":cdevice.getName()));
					if(cdevice.getIp()!=null&&!cdevice.getIp().equals("")){
						tv_tips.setText("局域网在线");
					}else{
						tv_tips.setText("远程在线");
					}
				}else{
					tv_info.setText(cdevice.getMac());
					tv_info.setTextColor(context.getResources().getColor(R.color.gray));
					tv_name.setText((cdevice.getName().equals("")? "未知设备":cdevice.getName()));
					tv_name.setTextColor(context.getResources().getColor(R.color.gray));
					tv_tips.setText("离线");
					tv_tips.setTextColor(context.getResources().getColor(R.color.gray));
					
				}
			}
		}else{
			rl_device.setVisibility(View.GONE);
			rl_title.setVisibility(View.VISIBLE);
			tv_title.setText(cdevice.getTitleName());
		}
		return v;
	}

}