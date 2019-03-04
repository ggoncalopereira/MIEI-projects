package com.example.group4.qosiapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

class StopClient extends AsyncTask<Void, Void, Void> {

    Socket client;
    String serverIP = "192.168.1.81"; //change this!!
    int port = 9998;
    String resP,resT;
    TextView textResponse,textResponse2;

    public StopClient(TextView textResponse) {
        this.textResponse = textResponse;
    }

    public StopClient(TextView textR, TextView textR2) {
        this.textResponse = textR;
        this.textResponse2 = textR2;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            client = new Socket(serverIP,port);
            System.out.println("Ligado ao servidor");
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));

            //Establishing TCP connection with server
            // Send to the server the option wanted
            PrintWriter out = new PrintWriter(client.getOutputStream(),true);
            out.println("Stop");
            out.flush();
            String answer;
            while((answer = in.readLine())!=null){
                if(answer.equals("OK")){
                    continue;
                }
                else{
                    System.out.println(answer);
                    String[] answers = answer.split(";");
                    resP = answers[2];
                    resT = answers[3];
                }
                break;
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPostExecute(final Void result){
        textResponse.setText(resP +"%");
        textResponse2.setText(resT +"ms");
    }
}
