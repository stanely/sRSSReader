package com.example.srssreader;

import java.io.IOException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MainActivity extends Activity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.go);
        
        btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fetchRSSFeed();
            }

            private void fetchRSSFeed() {
                // TODO Auto-generated method stub
                HttpClient hc = new DefaultHttpClient();
                HttpResponse response = null;
                String content = "";
                
                try {
                    //HttpPost httppost = new HttpPost("http://www.wretch.cc/blog/stanely5&rss20=1");
                    HttpGet httpget = new HttpGet("http://www.wretch.cc/blog/stanely5&rss20=1");
                    response = hc.execute(httpget);
                    if(response != null) {
                        Log.d("stanely", "response != null");
                    } else
                        Log.d("stanely", "response == null");
                    
                    content += EntityUtils.toString(response.getEntity());
                }
                
                catch (ClientProtocolException ex) {
                    Log.d("stanely", "Catch ClientProtocolException!!!");
                }
                
                catch (IOException ex) {
                    Log.d("stanely", "Catch IOException!!!");
                }
                
                finally {
                    hc.getConnectionManager().shutdown();
                }
                
            }
            

            
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}

class RetrieveFeedAsync extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
