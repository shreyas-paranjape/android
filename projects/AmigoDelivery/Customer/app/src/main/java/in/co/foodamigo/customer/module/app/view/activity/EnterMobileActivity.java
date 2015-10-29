package in.co.foodamigo.customer.module.app.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


import com.matesnetwork.Cognalys.VerifyMobile;

import in.co.foodamigo.customer.R;

public class EnterMobileActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_mobile);
        final Button test = (Button) findViewById(R.id.test);
        final EditText mobilenum = (EditText) findViewById(R.id.editText1);
        final EditText countrycode = (EditText) findViewById(R.id.editText2);
        countrycode.setText(VerifyMobile.getCountryCode(getApplicationContext()));
        test.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyMobile(countrycode.getText().toString() + mobilenum.getText().toString());
            }
        });
    }

    private void verifyMobile(String mobileNumber) {
        Intent intent = new Intent(this, VerifyMobile.class);
        intent.putExtra("app_id", "f0124d30880a4313825e3d9");
        intent.putExtra("access_token", "86c4af9c0c3ff31039273d77a71389ede96ae587");
        intent.putExtra("mobile", mobileNumber);
        startActivityForResult(intent, VerifyMobile.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VerifyMobile.REQUEST_CODE) {
            String message = data.getStringExtra("message");
            int result = data.getIntExtra("result", 0);
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
        }
    }
}
