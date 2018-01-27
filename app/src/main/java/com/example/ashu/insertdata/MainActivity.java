package com.example.ashu.insertdata;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText e_rollNo,e_name, e_branch, e_shift;
    String name,branch,shift,rollNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        e_rollNo =findViewById(R.id.rollNo);
        e_name=findViewById(R.id.name);
        e_branch =findViewById(R.id.branch);
        e_shift =findViewById(R.id.shift);
    }

    public void insertClicked(View v){

        rollNo=e_rollNo.getText().toString();
        name=e_name.getText().toString();
        shift=e_shift.getText().toString();
        branch=e_branch.getText().toString();

        String method="register";
        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(method,rollNo,name,branch,shift);

        finish();

    }

    public class BackgroundTask extends AsyncTask<String,Void,String>{

        Context ctx;
        BackgroundTask(Context ctx){
            this.ctx=ctx;
        }

        @Override
        protected String doInBackground(String... strings) {


            String regUrl="http://192.100.2.3/fkc/register.php",method=strings[0];
            if(method.equals("register")){

                String rollNo,name,branch,shift;
                rollNo=strings[1];
                name =strings[2];
                branch=strings[3];
                shift=strings[4];

                try {
                    URL url=new URL(regUrl);
                    Log.i("Insert Data" +" ","...."+url);
                    HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String data= URLEncoder.encode("rollNo","UTF-8")+"="+URLEncoder.encode(rollNo,"UTF-8")+"&"+
                                 URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                                 URLEncoder.encode("branch","UTF-8")+"="+URLEncoder.encode(branch,"UTF-8")+"&"+
                                 URLEncoder.encode("shift","UTF-8")+"="+URLEncoder.encode(shift,"UTF-8");

                    Log.i("Insert Data" +" ","...Data..."+data);

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream is=httpURLConnection.getInputStream();
                    is.close();



                } catch (MalformedURLException e) {
                    Log.i("Exception Called",""+e);
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            return "Inserted";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
