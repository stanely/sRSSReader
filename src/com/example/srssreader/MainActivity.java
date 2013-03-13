package com.example.srssreader;

import java.io.IOException;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.rssfeeditem.*;

public class MainActivity extends Activity {
    Button btn;
    TextView text;
    
    //String xmlURL = "http://www.wretch.cc/blog/stanely5&rss20=1";
    String xmlURL = "http://rss.cnn.com/rss/edition_asia.rss";
    public ArrayList<RSSItemField> item;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.go);
        text = (TextView) findViewById(R.id.text);
        
        btn.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fetchRSSFeed();
            }
        });
        
        // display on ListView
    }         

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


	private void fetchRSSFeed() {
		new RetrieveFeedAsync().execute(xmlURL);
	}


    class RetrieveFeedAsync extends AsyncTask<String, Integer, String> {
    
        @Override
        protected String doInBackground(String... params) {
            HttpClient hc = new DefaultHttpClient();
            HttpResponse response = null;
            String content = "";
            
            try {
                //HttpPost httppost = new HttpPost("http://www.wretch.cc/blog/stanely5&rss20=1");
                //HttpGet httpget = new HttpGet(xmlURL);
                HttpGet httpget = new HttpGet(params[0]);  // get the given URL from caller
                response = hc.execute(httpget);
                
                if(response != null) {
                    Log.d("stanely", "response != null");
                } else
                    Log.d("stanely", "response == null");
                
                content += EntityUtils.toString(response.getEntity());  // get xml file now
                
                // parsing
                XmlParser p = new XmlParser();
                item = p.parseXmlResponse(content);
                
                Log.d("stanely", "Ending!!!" + "size = " + item.size());
            }
            
            catch (ClientProtocolException ex) {
                Log.d("stanely", "Catch ClientProtocolException!!!");
                return null;
            }
            
            catch (IOException ex) {
                Log.d("stanely", "Catch IOException!!!");
                return null;
            }
            
            finally {
                hc.getConnectionManager().shutdown();
                
                //text.setText(new String("Count:" + item.size()));  // TODO: Will crash, check later.
                
                for(int i = 0; i < item.size(); i++) {
                    Log.d("stanely", item.get(i).title);
                }
                
            }
          
    		return "SUCCESS";
    	}
    
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);    
        }
    
        @Override
        protected void onProgressUpdate(Integer... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }
    }

}