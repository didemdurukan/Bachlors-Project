package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OpeningActivity extends AppCompatActivity {

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        setScreen();

    }

    public void connectProceedBtn(){
        Button connectproceedbtn = (Button) findViewById(R.id.connectButton);
        connectproceedbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                EditText urledittext = (EditText) findViewById(R.id.urlEditText);
                String url = urledittext.getText().toString();

                if (url.startsWith("http://") && !url.isEmpty()){
                    intent.putExtra("url_key",url);
                    startActivity(intent);
                }
                else if(url.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please fill all the fields.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }
                else if(!url.startsWith("http://")){
                    Toast toast = Toast.makeText(getApplicationContext(),"Invalid URL Type.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }

            }
        });
    }

    public void quitBtn(){

        final AlertDialog.Builder alertdialogbuilder = new AlertDialog.Builder(this);
        Button quitbutton = (Button) findViewById(R.id.quitbutton);
        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertdialogbuilder.setMessage("Are you sure?");
                alertdialogbuilder.setCancelable(true);

                alertdialogbuilder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertdialogbuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog quitDialog = alertdialogbuilder.create();
                quitDialog.show();

            }
        });



    }

    public void setScreen(){
          connectProceedBtn();
          quitBtn();
          checkURLBtn();
    }

    public void checkURLBtn(){
        Button checkurlbtn = (Button) findViewById(R.id.checkUrlbtn);
        checkurlbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText urledittext = (EditText) findViewById(R.id.urlEditText);
                String enteredurl = urledittext.getText().toString();
                if(enteredurl.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Enter URL.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
                    toast.show();
                }else {
                    checkUrl(enteredurl);
                }

            }
        });
    }

    public void setAlertDialog(String msg){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(msg);
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setNegativeButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void checkUrl(String url){
        HttpGetRequest getRequest = new HttpGetRequest();
        getRequest.execute(url);
    }

    class HttpGetRequest extends AsyncTask<String, Void, Void> {

        String urlinfo;
        int statuscode;
        String result;
        String inputLine;
        String connection = "true";

        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(OpeningActivity.this);
            pd.setMessage("Checking URL...");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected Void doInBackground(String... strings) {
            URL url = null;
            try {
                urlinfo = strings[0];
                url = new URL(urlinfo);
            } catch (Exception e) {
                if(e instanceof MalformedURLException){
                    e.printStackTrace();
                }
            }
            HttpURLConnection conn = null;
            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestMethod("GET");


                statuscode = conn.getResponseCode();
                if (statuscode == 200) {

                    InputStreamReader streamReader = new
                            InputStreamReader(conn.getInputStream());
                    //Create a new buffered reader and String Builder
                    BufferedReader reader = new BufferedReader(streamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    //Check if the line we are reading is not null
                    while ((inputLine = reader.readLine()) != null) {
                        stringBuilder.append(inputLine);
                    }
                    //Close our InputStream and Buffered reader
                    reader.close();
                    streamReader.close();
                    //Set our result equal to our stringBuilder
                    result = stringBuilder.toString();
                    //customer = new Customer();
                }
                return null;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();  //ProgresDialog u kapatıyoruz.

            if(statuscode == 200 && connection.equals(result)){
                setAlertDialog("Reachable server, good to go!");
            } else {
                setAlertDialog("Cannot reach to server, please check your URL.");
            }

        }
    }

}
