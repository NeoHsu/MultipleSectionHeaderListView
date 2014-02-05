package tw.neohsu.android.adapters;

import java.util.ArrayList;

import tw.neohsu.android.R;
import tw.neohsu.android.model.RowItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author NeoHsuDroid
 * @date 2014/2/5
 */
public class CustomBaseAdapter extends BaseAdapter {
	
	Context context;
	private ArrayList<RowItem> mData = new ArrayList<RowItem>();
	private ArrayList<Integer> mItemLayout = new ArrayList<Integer>();
	private LayoutInflater mInflater;
	
	private static final int ITEM_LAYOUT_MAX_COUNT = 3;
	private static final int ITEM_LAYOUT_HEADER = 0;
	private static final int ITEM_LAYOUT_ONE = 1;
	private static final int ITEM_LAYOUT_TWO = 2;
	
	public CustomBaseAdapter(Context mCtx) {
		this.context = mCtx;
		mInflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void addItem(RowItem item, int itemLayout) {
		mData.add(item);
		mItemLayout.add(itemLayout);
		notifyDataSetChanged();
	}
	
	public void addHeader(RowItem item) {
		mData.add(item);
		mItemLayout.add(ITEM_LAYOUT_HEADER);
		notifyDataSetChanged();
	}
	
	public int getItemViewType(int position) {
		int itemLayout = 0;
		switch(mItemLayout.get(position)){
			case ITEM_LAYOUT_HEADER :
				itemLayout = ITEM_LAYOUT_HEADER;
				break;
			case ITEM_LAYOUT_ONE :
				itemLayout = ITEM_LAYOUT_ONE;
				break;
			case ITEM_LAYOUT_TWO :
				itemLayout = ITEM_LAYOUT_TWO;
				break;
		}
		return itemLayout;
	}
	
	public int getViewTypeCount() {
		return ITEM_LAYOUT_MAX_COUNT;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		int itemLayout = getItemViewType(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			switch(itemLayout){
				case ITEM_LAYOUT_HEADER :
					convertView = mInflater.inflate(R.layout.section_header, null);
					holder.txtTitle = (TextView) convertView.findViewById(R.id.textHeader);
					holder.itemLayout = ITEM_LAYOUT_HEADER;
					break;
				case ITEM_LAYOUT_ONE :
					convertView = mInflater.inflate(R.layout.section_item_one, null);
					holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
					holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
					holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
					holder.itemLayout = ITEM_LAYOUT_ONE;
					break;
				case ITEM_LAYOUT_TWO :
					convertView = mInflater.inflate(R.layout.section_item_two, null);
					holder.txtDesc = (TextView) convertView.findViewById(R.id.desc);
					holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
					holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
					holder.itemLayout = ITEM_LAYOUT_TWO;
					break;
			}
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		RowItem rowItem = (RowItem) getItem(position);
		switch(holder.itemLayout){
			case ITEM_LAYOUT_HEADER :
				holder.txtTitle.setText(rowItem.getTitle());
				break;
			case ITEM_LAYOUT_ONE :
			case ITEM_LAYOUT_TWO :
				holder.txtDesc.setText(rowItem.getDesc());
				holder.txtTitle.setText(rowItem.getTitle());
				holder.imageView.setImageResource(rowItem.getImageId());
				break;
		}
		
		return convertView;
	}
	
	/* private view holder class */
	private class ViewHolder {
		ImageView imageView;
		TextView txtTitle;
		TextView txtDesc;
		int itemLayout;
	}
	
}
