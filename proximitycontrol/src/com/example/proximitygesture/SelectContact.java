package com.example.proximitygesture;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SelectContact extends ListActivity {
	  
	 @Override
	 public long getSelectedItemId() {
	  // TODO Auto-generated method stub
	  return super.getSelectedItemId();
	 }
	  
	 @Override
	 public int getSelectedItemPosition() {
	  // TODO Auto-generated method stub
	  return super.getSelectedItemPosition();
	 }
	 ListView lv;
	 Cursor Cursor1;
	  
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      
	        //create a cursor to query the Contacts on the device to start populating a listview
	     Cursor1 = getContentResolver().query(
	       ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
	       null,
	          null,
	          null,
	          null);
	     startManagingCursor(Cursor1);
	         
	        String[] from = {
	          ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
	          ContactsContract.CommonDataKinds.Phone.NUMBER,
	          ContactsContract.CommonDataKinds.Phone._ID}; // get the list items for the listadapter could be TITLE or URI
	   
	  int[] to = {android.R.id.text1}; //sets the items from above string to listview
	   
	  //new listadapter, created to use android checked template
	  SimpleCursorAdapter listadapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, Cursor1, from, to );
	  setListAdapter(listadapter);
	   
	  //adds listview so I can get data from it
	  lv = getListView();  
	  lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Cursor cursor = (Cursor) lv.getItemAtPosition(position);
			String displayName = cursor.getString(cursor.getColumnIndexOrThrow( ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String phoneNum = cursor.getString(cursor.getColumnIndexOrThrow( ContactsContract.CommonDataKinds.Phone.NUMBER));
			Log.e("",""+displayName);
			Log.e("",""+phoneNum);
			Intent intent = new Intent();
	        intent.putExtra("PhoneNum",phoneNum );
	        setResult(1, intent);
	        finish();
			
		}
	});

	    }
	    
	}