package software_project.user_interfaceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;


public class MainActivity extends AppCompatActivity {

    private SeekBar sbDTemp, sbV1Size, sbV2Size, sbV3Size, sbV4Size, sbV5Size;
    private TextView tvWTemp, tvWLevel, tvDTemp;
    private ToggleButton tbValve1, tbValve2, tbValve3, tbValve4;
    private Switch sValveA, sValveF;
    private WebSocketClient mWebSocketClient;
    private int VAstatus, VB1status, VB2status, VB3status, VB4status, VFstatus;
    private double Burnerc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //visual item instantiations
        sbDTemp = (SeekBar) findViewById(R.id.sbDTemp);
        sbV1Size = (SeekBar) findViewById(R.id.sbV1Size);
        sbV2Size = (SeekBar) findViewById(R.id.sbV2Size);
        sbV3Size = (SeekBar) findViewById(R.id.sbV3Size);
        sbV4Size = (SeekBar) findViewById(R.id.sbV4Size);
        tvWTemp = (TextView) findViewById(R.id.tvWTemp);
        tvWLevel = (TextView) findViewById(R.id.tvWLevel);
        tvDTemp = (TextView) findViewById(R.id.tvDTemp);
        tbValve1 = (ToggleButton) findViewById(R.id.tbValve1);
        tbValve2 = (ToggleButton) findViewById(R.id.tbValve2);
        tbValve3 = (ToggleButton) findViewById(R.id.tbValve3);
        tbValve4 = (ToggleButton) findViewById(R.id.tbValve4);
        sValveA = (Switch) findViewById(R.id.sValveA);
        sValveF = (Switch) findViewById(R.id.sValveF);

        VAstatus = 0;
        VB1status = 0;
        VB2status = 0;
        VB3status = 0;
        VB4status = 0;
        VFstatus = 0;
        Burnerc = 0;


        connectWebsocket();
        setSeekBarListeners();
        setToggleButtonListeners();
        setSwitchListeners();
    }

    public void setExceptionButtonListeners()
    {
        //onclick for each error button
        //sends EXCEPTION:[0,0,0,0]
        
    }

    public void setSeekBarListeners()
    {
        sbDTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String num = sbDTemp.getProgress() + 21 + " C";
                tvDTemp.setText(num);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Burnerc = sbDTemp.getProgress() + 21;
                Burnerc /= 100;
            }
        });


    }

    public void setToggleButtonListeners()
    {
        tbValve1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    VB1status = 1;
                else
                    VB1status = 0;

                switch(sbV1Size.getProgress())
                {
                    case 0:
                        //size 250ml
                        break;
                    case 1:
                        //size 300ml
                        break;
                    case 2:
                        //size 350ml
                        break;
                    case 3:
                        //size 400ml
                        break;
                }

                sendString();
            }
        });

        tbValve2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    VB2status = 1;
                else
                    VB2status = 0;

                switch(sbV2Size.getProgress())
                {
                    case 0:
                        //size 250ml
                        break;
                    case 1:
                        //size 300ml
                        break;
                    case 2:
                        //size 350ml
                        break;
                    case 3:
                        //size 400ml
                        break;
                }

                sendString();
            }
        });

        tbValve3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    VB3status = 1;
                else
                    VB3status = 0;

                switch(sbV3Size.getProgress())
                {
                    case 0:
                        //size 250ml
                        break;
                    case 1:
                        //size 300ml
                        break;
                    case 2:
                        //size 350ml
                        break;
                    case 3:
                        //size 400ml
                        break;
                }

                sendString();
            }
        });

        tbValve4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    VB4status = 1;
                else
                    VB4status = 0;

                switch(sbV4Size.getProgress())
                {
                    case 0:
                        //size 250ml
                        break;
                    case 1:
                        //size 300ml
                        break;
                    case 2:
                        //size 350ml
                        break;
                    case 3:
                        //size 400ml
                        break;
                }

                sendString();
            }
        });
    }

    public void setSwitchListeners()
    {
        sValveA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //set valve A status to isChecked
                if(isChecked)
                    VAstatus = 1;
                else
                    VAstatus = 0;
            }
        });

        sValveF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //set valve F status to isChecked
                if(isChecked)
                    VFstatus = 1;
                else
                    VFstatus = 0;
            }
        });
    }

    public void connectWebsocket()
    {
        URI uri;
        try {
            uri = new URI("ws://Garretts-MBP.attlocal.net:8080/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }


        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {

            }

            @Override
            public void onMessage(String message)
            {
                //STATUS:A=0,B=[1,1,0,0],C=0,D=10.0,E=1.0,F=0
                String A = message.substring(9,10);
                String B1 = message.substring(14,15);
                String B2 = message.substring(16,17);
                String B3 = message.substring(18,19);
                String B4 = message.substring(20,21);
                String C = "";
                String D = "";
                String E = "";
                String F = "";


            }

            @Override
            public void onClose(int code, String reason, boolean remote)
            {

            }

            @Override
            public void onError(Exception ex)
            {

            }
        };

        mWebSocketClient.connect();
    }

    public void sendString()
    {
        String str = String.format("CONTROL:A=%d,B=[%d,%d,%d,%d],C=%1.2f,F=%d",VAstatus, VB1status,
                VB2status, VB3status, VB4status, Burnerc, VFstatus);

        //CONTROL:A=0,B=[0,0,0,0],C=0.85,F=0

        mWebSocketClient.send(str);
    }

}
