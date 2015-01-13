package com.ninh.tuvungtienganh;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;

import com.zenapp.tuvung.R;
import com.ninh.tuvungtienganh.MainActivity.loaddb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends Activity implements
TextToSpeech.OnInitListener{
	private TextToSpeech tts;
	private EditText txtText;
	private ProgressDialog pDialog;
	Cursor c = null;
	ListView lv;
	ArrayList<english> eng = new ArrayList<english>();
	SearchAdapter adapter;
	EditText inputSearch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		setContentView(R.layout.layout_search);
		tts = new TextToSpeech(this,this);
		txtText = (EditText) findViewById(R.id.subject);
		lv = (ListView) findViewById(R.id.list_search);
//		new loaddb().execute();
		findViewById(R.id.btnOption).setOnClickListener(
				new View.OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});
		inputSearch = (EditText) findViewById(R.id.edit_text);
		inputSearch.requestFocus();
		inputSearch.postDelayed(new Runnable() {
			public void run() {
				InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				keyboard.showSoftInput(inputSearch, 0);
			}
		}, 200);

//		inputSearch.addTextChangedListener(new TextWatcher() {
//
//			// @Override
//			public void onTextChanged(CharSequence cs, int arg1, int arg2,
//					int arg3) {
//				// When user changed the Text
//				SearchActivity.this.adapter.getFilter().filter(cs);
//			}
//
//			// @Override
//			public void beforeTextChanged(CharSequence arg0, int arg1,
//					int arg2, int arg3) {
//				// TODO Auto-generated method stub
//
//			}
//
//			// @Override
//			public void afterTextChanged(Editable arg0) {
//			}
//		});

		inputSearch.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_DPAD_CENTER:
					case KeyEvent.KEYCODE_ENTER:
						eng.clear();
						new loaddb().execute();
						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(inputSearch.getWindowToken(),
								0);
						return true;
					default:
						break;
					}
				}
				return false;
			}
		});
	}

	class loaddb extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			DatabaseHelper myDbHelper = new DatabaseHelper(SearchActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			final EditText edit1 = (EditText) findViewById(R.id.edit_text);
			c = myDbHelper.query("englishcommon", null, "name LIKE '"+ edit1.getText().toString() + "%'", null, null, null,
					null); // "name LIKE '"+ edit1.getText().toString() + "%'"
			if (c.moveToFirst()) {
				do {

					String id = c.getString(0);
					String name = c.getString(1);
					String category = c.getString(2);
					String read = c.getString(3);
					String viconten = c.getString(4);
					String alpha = c.getString(5);
					String isread = c.getString(6);
					String isure = c.getString(7);
					String isnext = c.getString(8);
					eng.add(new english(id, name, category, read, viconten,
							alpha, isread, isure, isnext));

				} while (c.moveToNext());
				// c.close();
			}
			c.close();
			myDbHelper.close();
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			adapter = new SearchAdapter(SearchActivity.this);
			for (int i = 0; i < eng.size(); i++) {
				adapter.add(eng.get(i));
				// Log.e("data",eng.get(i).getName());
			}
			runOnUiThread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					lv.setAdapter(adapter);
				}
			});
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(SearchActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	public void onInit(int status) {
		try{
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			// tts.setPitch(5); // set pitch level

			// tts.setSpeechRate(2); // set speech speed rate

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "Language is not supported");
			} else {
//				btnSpeak.setEnabled(true);
				speakOut();
			}

		} else {
			Log.e("TTS", "Initilization Failed");
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void speakOut() {
		try{
		lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
//			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int arg2,
					long arg3) {
				String name = ((TextView) view.findViewById(R.id.subject)).getText().toString();
//				Toast.makeText(MainActivity.this, ""+name, Toast.LENGTH_LONG).show();
//				speakOut();
				tts.speak(name, TextToSpeech.QUEUE_FLUSH, null);
			}
		});
		}catch(Exception e)
		{
			e.printStackTrace();
		}
//		String text = txtText.getText().toString();
//
		
	}
}
