package com.example.dayto.sm_adventure;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;






public class MainActivity extends AppCompatActivity {

    private static String TAG = "phptest_MainActivity";

    private EditText mEditTextNickname;
    private EditText mEditTextID;
    private EditText mEditTextPassword;
    private TextView mTextViewResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this,SplashActivity.class));

        mEditTextNickname = (EditText)findViewById(R.id.editText_main_Nickname);
        mEditTextID = (EditText)findViewById(R.id.editText_main_ID);
        mEditTextPassword = (EditText)findViewById(R.id.editText_main_Password);
        mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        Button buttonInsert = (Button)findViewById(R.id.button_main_insert);
        Button buttonLogin = (Button)findViewById(R.id.button_main_login);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nickname = mEditTextNickname.getText().toString();
                String ID = mEditTextID.getText().toString();
                String Password = mEditTextPassword.getText().toString();

                InsertData task = new InsertData();
                task.execute(Nickname, ID, Password);


              //  mEditTextNickname.setText("");
              //  mEditTextID.setText("");
                mEditTextPassword.setText("");

            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Nickname = mEditTextNickname.getText().toString();
                String ID = mEditTextID.getText().toString();
                String Password = mEditTextPassword.getText().toString();

                loginData task = new loginData();
                task.execute(Nickname, ID, Password);


              //  mEditTextNickname.setText("");
              //  mEditTextID.setText("");
                mEditTextPassword.setText("");

            }
        });
    }


    class InsertData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            String text[];
            text = result.split("<br />");
            String f_text = "";
            for(int i = 0; i < text.length; i++)
            {
                f_text += text[i] + "\n";
            }
            mTextViewResult.setText(f_text);
            Log.d(TAG, "POST response  - " + result);

            if ("로그인 성공"==f_text){
                startActivity(new Intent(MainActivity.this, EventActivity.class));

                finish();}
            else {

            }
        }


        @Override
        protected String doInBackground(String... params) {

            String Nickname = (String)params[0];
            String ID = (String)params[1];
            String Password = (String)params[2];
            String serverURL = "http://smadventure.esy.es/push.php";
            String postParameters = "Nickname=" + Nickname + "&ID=" + ID + "&Password=" + Password + "&signUp=";

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                System.out.println("postParameters : " + postParameters);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

    class loginData extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            String text[];
            text = result.split("<br />");
            String f_text = "";
            for(int i = 0; i < text.length; i++)
            {
                f_text += text[i] + "\n";
            }
            mTextViewResult.setText(f_text);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String Nickname = (String)params[0];
            String ID = (String)params[1];
            String Password = (String)params[2];
            String serverURL = "http://smadventure.esy.es/push.php";
            String postParameters = "Nickname=" + Nickname + "&ID=" + ID + "&Password=" + Password + "&login=";

            try {
                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                System.out.println("postParameters : " + postParameters);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }
}


