package com.nyoseintlay.mobiledatausage.splash;
/**
 * Created by NyoSeint Lay on 16/04/19.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nyoseintlay.mobiledatausage.main.MainActivity;
public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToMainActivity();
    }
    private void goToMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
