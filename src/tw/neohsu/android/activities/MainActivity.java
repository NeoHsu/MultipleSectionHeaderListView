package tw.neohsu.android.activities;

import tw.neohsu.android.R;
import tw.neohsu.android.adapters.CustomBaseAdapter;
import tw.neohsu.android.model.RowItem;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.app.Activity;

public class MainActivity extends Activity {
	public static final String[] titles = new String[] { "BlackBerry Z10", "BlackBerry Q10", "BlackBerry Q5",
	        "BlackBerry Z30" };
	public static final String[] descriptions = new String[] { "OS : BlackBerry 10", "OS : BlackBerry 10",
	        "OS : BlackBerry 10", "OS : BlackBerry 10" };
	public static final Integer[] images = { R.drawable.z10, R.drawable.q10, R.drawable.q5, R.drawable.z30 };
	
	ListView listView;
	CustomBaseAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		adapter = new CustomBaseAdapter(this);
		int count = 0;
		while(count < 10){
			if(count % 3 == 0){
				RowItem item = new RowItem("Header Title " + count);
				adapter.addHeader(item);
			}
			if(count % 5 == 0){
				for(int i = 0;i < titles.length;i++){
					RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
					adapter.addItem(item, 2);
				}
			}else{
				for(int i = 0;i < titles.length;i++){
					RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
					adapter.addItem(item, 1);
				}
			}
			count++;
		}
	}
	
	protected void onResume() {
		super.onResume();
		if(listView == null){
			listView = (ListView) findViewById(R.id.list);
			listView.setEmptyView(findViewById(android.R.id.empty));
		}
		if(adapter != null){
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					Toast toast = Toast.makeText(getApplicationContext(),
					        "Item " + (position + 1) + ": " + adapter.getItemId(position), Toast.LENGTH_SHORT);
					toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
					toast.show();
				}
			});
		}
		
	}
}
