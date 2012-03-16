package com.wb.libusb;

import android.util.Log;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;

public class LibUSBDroid extends Activity implements OnClickListener{

    private TextView   tvAbout;
    private Button     btnInit;
    private Button     btnClear;
    private EditText   etLogs;

    private static final String TAG = "[LibUSBJava]";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setText(Version());

        btnInit = (Button) findViewById(R.id.btnInit);
        btnInit.setOnClickListener(this);

        etLogs = (EditText) findViewById(R.id.etLogs);

        /*add event handler for clear command button*/
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
    }


    @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnClear:
                    clearLogs();
                    break;
                case R.id.btnInit:
                    Log.v(TAG, "Initializing LibUsb");
                    int ret = Initialize();
                    Log.v(TAG, "** ret: " + ret + " ***");
                    if(ret<0) {
                        addLog("Failed to initialize");
                    }
                    else {
                        addLog("Successfully initialized");
                    }
                    break;
            }

        }


    private void addLog(String msg) {
        String msgs = etLogs.getText().toString() + msg + "\n";
        etLogs.setText(msgs);
    }

    private void clearLogs() { etLogs.setText(""); }

    /*************************************************
     * LOAD NATIVE LIBRARY FOR libusb
     ************************************************/
    static {
        System.loadLibrary("usb");
    }

    /*
     * native function prototypes
     */
    public native String Version();
    public native int    Initialize();

}/*LibUSBDroid*/
