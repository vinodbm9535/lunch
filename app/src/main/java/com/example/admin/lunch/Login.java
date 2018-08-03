package com.example.admin.lunch;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.lunch.helper.AESEncryption;
import com.example.admin.lunch.helper.Constant;
import com.example.admin.lunch.helper.MCrypt;
import com.example.admin.lunch.helper.SharedPreferenceHelper;
import com.example.admin.lunch.helper.Utils;
import com.example.admin.lunch.retrofit.ApiClient;
import com.example.admin.lunch.retrofit.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView forgotPassword;

    Button btnLogin;
    WebView webView;
    EditText edtEmail, edtPassword;
    private static String login_url = "http://192.168.43.140:3200/api/authenticate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        webView = (WebView)findViewById(R.id.webview1);

        webView.getSettings().setJavaScriptEnabled(true);

        // Add the custom WebViewClient class
        webView.setWebViewClient(new CustomWebViewClient());

        // Add the javascript interface
        webView.addJavascriptInterface(new JavaScriptInterface(), "interface");

        // Load the example html file to the WebView
        webView.loadUrl("file:///android_asset/index.html");
        //button
        btnLogin = findViewById(R.id.btnLogin);
        //textView
        forgotPassword = findViewById(R.id.forgot_password);
        //editText
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        forgotPassword.setPaintFlags(forgotPassword.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnLogin.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
    }

    private void userLogin() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("EmailAddress", edtEmail.getText().toString().trim());
            jsonObject.put("Password", edtPassword.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //jsCall(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
        jsCall1();



        }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (!Utils.isEmptyEditText(edtEmail, "Please enter mail address")
                        && !Utils.isEmptyEditText(edtPassword, "Please enter password")) {
                    userLogin();
                }

                break;
            case R.id.forgot_password:
                Intent intent = new Intent(Login.this, Transaction.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * CustomWebViewClient is used to add a custom hook to the url loading.
     */
    private class CustomWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // If the url to be loaded starts with the custom protocol, skip
            // loading and do something else
            if (url.startsWith("tanelikorri://")) {

                Toast.makeText(Login.this, "Custom protocol call", Toast.LENGTH_LONG).show();

                return true;
            }

            return false;
        }
    }

    public void jsCall(String email, String pwd) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String tmp = "javascript:encryptData(\'"+email+"\',\'"+pwd+"\');";
            webView.evaluateJavascript(tmp, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                    Log.d("Encrypted", s);

                     Map<String, String> encryptJsonObject = new HashMap<>();
                        encryptJsonObject.put("data", s);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(encryptJsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("encryptJsonObject",encryptJsonObject.toString());

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, login_url, jsonObject, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
               /* try {
                    String success=  response.getString("status");
                    if(success.contains("SUCCESS")) {

                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();

                    }
                }catch (Exception e){

                }*/

                            Log.e("Your Array Response", response.toString());

                        }

                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error is ", "" + error);

                        }
                    }) {

                        //This is for Headers If You Needed
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }


                    };
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(request);
                }
            });
        } else {
            Toast.makeText(Login.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }
    }


    public void jsCall1() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String tmp = "javascript:encryptData();";
            webView.evaluateJavascript(tmp, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                    Log.d("Encrypted", s);

                }
            });
        } else {
            Toast.makeText(Login.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }
    }

    private class JavaScriptInterface {

        @JavascriptInterface
        public void callFromJS() {
            Toast.makeText(Login.this, "JavaScript interface call", Toast.LENGTH_LONG).show();
        }
    }

}
