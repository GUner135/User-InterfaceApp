package software_project.user_interfaceapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private SeekBar sbDTemp, sbV1Size, sbV2Size, sbV3Size, sbV4Size, sbV5Size;
    private TextView tvWTemp, tvWLevel, tvDTemp;
    private ToggleButton tbValve1, tbValve2, tbValve3, tbValve4, tbValve5;

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
        sbV5Size = (SeekBar) findViewById(R.id.sbV5Size);
        tvWTemp = (TextView) findViewById(R.id.tvWTemp);
        tvWLevel = (TextView) findViewById(R.id.tvWLevel);
        tvDTemp = (TextView) findViewById(R.id.tvDTemp);
        tbValve1 = (ToggleButton) findViewById(R.id.tbValve1);
        tbValve2 = (ToggleButton) findViewById(R.id.tbValve2);
        tbValve3 = (ToggleButton) findViewById(R.id.tbValve3);
        tbValve4 = (ToggleButton) findViewById(R.id.tbValve4);
        tbValve5 = (ToggleButton) findViewById(R.id.tbValve5);

        setSeekBarListeners();
        setToggleButtonListeners();


    }

    public void setSeekBarListeners()
    {
        sbDTemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String num = sbDTemp.getProgress()+21+" C";
                tvDTemp.setText(num);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //send command to websockets
            }
        });


    }

    public void setToggleButtonListeners()
    {
        tbValve1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(sbV1Size.getProgress())
                {
                    case 0:
                        //small
                        break;
                    case 1:
                        //medium
                        break;
                    case 2:
                        //large
                        break;
                }
            }
        });

        tbValve2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(sbV2Size.getProgress())
                {
                    case 0:
                        //small
                        break;
                    case 1:
                        //medium
                        break;
                    case 2:
                        //large
                        break;
                }
            }
        });

        tbValve3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(sbV3Size.getProgress())
                {
                    case 0:
                        //small
                        break;
                    case 1:
                        //medium
                        break;
                    case 2:
                        //large
                        break;
                }
            }
        });

        tbValve4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (sbV4Size.getProgress()) {
                    case 0:
                        //small
                        break;
                    case 1:
                        //medium
                        break;
                    case 2:
                        //large
                        break;
                }
            }
        });

        tbValve5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (sbV5Size.getProgress()) {
                    case 0:
                        //small
                        break;
                    case 1:
                        //medium
                        break;
                    case 2:
                        //large
                        break;
                }
            }
        });
    }


}
