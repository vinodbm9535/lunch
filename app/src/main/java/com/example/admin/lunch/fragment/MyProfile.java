package com.example.admin.lunch.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.lunch.Login;
import com.example.admin.lunch.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyProfile extends AppCompatActivity {

    ImageView addChild, transaction, orderNow, viewOrCancelOrder, myProfile, changePassword;
    private WebView wvUsrProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);
        getSupportActionBar().hide();
        wvUsrProfile = findViewById(R.id.wvUsrProfile);
        wvUsrProfile.getSettings().setJavaScriptEnabled(true);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<!DOCTYPE html><html><body>");
        stringBuilder.append("<script src=\"rollups/aes.js\"></script>\n" +
                "<script src=\"components/aes.js\"></script>\n" +
                "<script src=\"components/enc-base64-min.js\"></script>\n" +
                "<script src=\"js/user-profile.js\"></script>\n");
        stringBuilder.append("</body></html>");
        wvUsrProfile.loadData(stringBuilder.toString(), "text/html; charset=UTF-8", null);


        // TODO call decryptData(decryptData) after user/profile Volley call success
    }

    public void decryptData(String decryptData) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String script = "javascript:decryptData("+decryptData+");";
            wvUsrProfile.evaluateJavascript(script, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Toast.makeText(MyProfile.this, s, Toast.LENGTH_LONG).show();
                    Log.d("Encrypted", s);
                }
            });
        } else {
            Toast.makeText(MyProfile.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }
    }
}
