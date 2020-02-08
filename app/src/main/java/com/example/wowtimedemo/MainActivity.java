package com.example.wowtimedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wowtimedemo.Adapter.ItemsAdapter;
import com.example.wowtimedemo.Model.ItemsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    String TAG=">>>>";

    RecyclerView recyclerView;
    ItemsAdapter itemsAdapter;

    ProgressBar progressBar;

    ArrayList<ItemsModel> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init views
        recyclerView=findViewById(R.id.items_recycler_view);
        itemsAdapter=new ItemsAdapter(arrayList);
        progressBar=findViewById(R.id.progress_bar);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.googleapis.com/youtube/v3/search?part=snippet,id&order=date&maxResults=50&type=video&key=AIzaSyCsP86MhVPfDaOeHgi9LQA1t1cT3csEpJU&channelId=UC_aEa8K-EOJ3D6gOs7HcyNg&pageToken=";

        //request response in json format
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //processing json response
                        try {
                            JSONArray jsonArray=response.getJSONArray("items");
                            // Loop through the array elements
                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject item = jsonArray.getJSONObject(i);
                                    JSONObject id=item.getJSONObject("id");
                                        String videoId=id.getString("videoId");

                                    JSONObject snippet=item.getJSONObject("snippet");
                                        String publishedAt=snippet.getString("publishedAt");
                                        String title=snippet.getString("title");
                                        String description=snippet.getString("description");
                                        JSONObject thumbnails=snippet.getJSONObject("thumbnails");
                                            JSONObject defaultThumbnail=thumbnails.getJSONObject("default");
                                            String url=defaultThumbnail.getString("url");
                                        String channelTitle=snippet.getString("channelTitle");


                                //formatting api publishedAt to readable format
                                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                                Date date = simpleDateFormat.parse(publishedAt);

                                PrettyTime prettyTime=new PrettyTime();
                                String prettyDate=prettyTime.format(date);

                                arrayList.add(new ItemsModel(title,channelTitle,prettyDate,url,videoId, description));

                            }
                            progressBar.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onResponse: "+e.getMessage());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //log output error message
                        Log.e(TAG, "onErrorResponse: "+error.getMessage());
                    }
                }
        );

        //add JsonObjectRequest to request queue
        queue.add(jsonObjectRequest);

        //setting recycler view
        itemsAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemsAdapter);

        //items onClickListener
        itemsAdapter.setOnItemClickListener(new ItemsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(MainActivity.this, PlayerActivity.class);
                intent.putExtra("videoId",arrayList.get(position).getVideoId());
                intent.putExtra("title",arrayList.get(position).getTitle());
                intent.putExtra("date",arrayList.get(position).getDateTime());
                intent.putExtra("channelName",arrayList.get(position).getChannelName());
                intent.putExtra("description",arrayList.get(position).getDescription());
                startActivity(intent);
            }
        });

    }


}
