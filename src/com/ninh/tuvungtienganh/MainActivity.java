package com.ninh.tuvungtienganh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;

import com.zenapp.tuvung.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity implements
TextToSpeech.OnInitListener {
	private TextToSpeech tts;
	private Spinner spinner1;
	private EditText txtText;
	private ProgressDialog pDialog;
	Cursor c = null;
	ListView lv;
	ArrayList<english> eng = new ArrayList<english>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		tts = new TextToSpeech(this,this);
		txtText = (EditText) findViewById(R.id.subject);
		lv = (ListView) findViewById(R.id.list);
		
		findViewById(R.id.btnhelp).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
								helpscr.class);
						startActivity(intent);
					}
				});
		findViewById(R.id.btnOption).setOnClickListener(
				new View.OnClickListener() {

					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this,
								SearchActivity.class);
						startActivity(intent);
					}
				});
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch(arg2) {
			    case 0:
			    	eng.clear();
			    	new loaddb().execute();
			        break;
			    case 1:
			    	eng.clear();
			    	new loaddb2().execute();
			        break;
			    case 2:
			    	eng.clear();
			    	new loaddb3().execute();
			        break;
			    case 3:
			    	eng.clear();
			    	new loaddb4().execute();
			        break;
			    case 4:
			    	eng.clear();
			    	new loaddb5().execute();
			        break;
			    case 5:
			    	eng.clear();
			    	new loaddb6().execute();
			        break;  
			    default:
			        
			}

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}

	class loaddb extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, null, null, null, null,null);
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
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	class loaddb2 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, "category=?", new String[] {"(v)"}, null, null,null);
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
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	class loaddb3 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, "category=?", new String[] {"(adj)"}, null, null,null);
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
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	class loaddb4 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, "category=?", new String[] {"(n)"}, null, null,null);
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
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	class loaddb5 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, "category=?", new String[] {"(ad v)"}, null, null,null);
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
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			// super.onPostExecute(result);
			pDialog.dismiss();
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("load...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

	}
	
	class loaddb6 extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			DatabaseHelper myDbHelper = new DatabaseHelper(MainActivity.this);
			myDbHelper.createDatabase();
			myDbHelper.openDataBase();
			c = myDbHelper.query("englishcommon", null, "category=?", new String[] {"(adv)"}, null, null,null);
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
			final EnglishAdapter adapter = new EnglishAdapter(MainActivity.this);
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
			pDialog = new ProgressDialog(MainActivity.this);
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
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				// .setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("3000 Từ Vựng")
				.setMessage("Bạn có muốn đóng ứng dụng")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								finish();
							}

						}).setNegativeButton("No", null).show();
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
		
	}
	private void speakOut() {
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

//		String text = txtText.getText().toString();
//
		
	}
}
