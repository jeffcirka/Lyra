package com.lyra.eartrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.lyra.eartrainer.control.MainController;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.create_nick);
        //TextView text = new TextView(this);
        //text.setText("Hello World!");
        //setContentView(text);
        
        MainController mainControl = new MainController(this);
        mainControl.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
	return true;
    }

}
