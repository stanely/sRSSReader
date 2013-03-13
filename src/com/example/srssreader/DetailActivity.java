package com.example.srssreader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class DetailActivity extends Activity {
    private TextView title;
    private TextView description;
    String tmp_title;
    String tmp_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); 
        
        Bundle b = getIntent().getExtras();
        tmp_title = b.getString("title");
        tmp_description = b.getString("description");
        
        title = (TextView) findViewById(R.id.detail_title);
        description = (TextView) findViewById(R.id.detail_description);
        
        showDetailPage();
        
    }
    
    private Void showDetailPage() {
        title.setText(tmp_title);
        description.setText(tmp_description);
        return null;
    }

}
