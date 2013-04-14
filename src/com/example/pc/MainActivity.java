package com.example.pc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btnSend;
	EditText txt;
	static TextView title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		title = (TextView) findViewById(R.id.title);
		btnSend = (Button) findViewById(R.id.btnSend);
		txt = (EditText) findViewById(R.id.txt);
		//start
		new Thread(new MessageListener()).start();
		
		btnSend.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MessageListener.send(txt.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public static void onMessageReceived(String text){
		title.setText(text);
	}

}
