package com.example.admin.lunch;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.lunch.beans.Allergy;
import com.example.admin.lunch.beans.Element;
import com.example.admin.lunch.constants.AppConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddChildActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private TextView etRegId;
    private TextView etSchName;
    private TextView childFirstName;
    private TextView childLastName;
    private WebView wvAddChild;
    private Button btnSave;
    private Spinner spinner_grade;
    private Spinner spinner_elements;
    private Spinner spinner_allergies;
    private EditText etFav;
    private EditText etSplIntsrn;
    private String mSchoolCode = "";
    private String mGrade = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        setAddChildScript();
        findAndSetViews();
        wvAddChild.getSettings().setJavaScriptEnabled(true);

    }

    private void findAndSetViews() {
        //Find Views
        etRegId = findViewById(R.id.etRegId);
        etSchName = findViewById(R.id.etSchName);
        childFirstName = findViewById(R.id.childFirstName);
        childLastName = findViewById(R.id.childLastName);
        spinner_grade = findViewById(R.id.spinner_grade);
        spinner_elements = findViewById(R.id.spinner_elements);
        spinner_allergies = findViewById(R.id.spinner_allergies);
        etFav = findViewById(R.id.etFav);
        etSplIntsrn = findViewById(R.id.etSplIntsrn);
        wvAddChild = findViewById(R.id.wvAddChild);
        btnSave = findViewById(R.id.btnSave);

        //Set Views
        btnSave.setOnClickListener(this);

        String[] grades = {"A", "B", "C", "D", "E"};
        ArrayAdapter<String> gradeAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, grades);
        spinner_grade.setAdapter(gradeAdapter);
        mGrade = (String) spinner_grade.getSelectedItem();
        getSchoolCode();
//        TODO disable elements untill SchoolCode success
        setElements();
        setAllergies();
    }

    private void getSchoolCode() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String encryptDataScript = "javascript:encryptSchoolCodeData(\'" + mSchoolCode + "\'," +
//                    "\'" + mGrade + "\'," +
                    ");";

            wvAddChild.evaluateJavascript(encryptDataScript, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String encryptData) {
                    Log.d(TAG, "encrypted data for get School Code: " + encryptData);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConstants.URL_SCHOOL_CODE, null, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, "SchoolCode Response : " + response.toString());
                            String encryptData = "";
                            try {
                                if (response.getBoolean("success")){
                                    encryptData = response.getString("transcation");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                String decryptDataScript = "javascript:decryptData(\'" + encryptData + "\'" +
                                        ");";

                                wvAddChild.evaluateJavascript(decryptDataScript, new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String decryptData) {
                                        Log.d(TAG, "decrypted data for Elements: " + decryptData);
//                TODO parse values & set mShoolCode

                                    }
                                });
                            } else {
                                Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
                            }

                        }

                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "ELEMENTS Error Response : " + error);
                            Toast.makeText(AddChildActivity.this, "Error saving child", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        //This is for Headers If You Needed
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            headers.put("x-access-token", AppConstants.ACCESS_TOKEN);
                            return headers;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(AddChildActivity.this);
                    queue.add(request);
                }
            });
        } else {
            Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }
    }

    private void setElements() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String encryptDataScript = "javascript:encryptElementsData(\'" + mSchoolCode + "\'," +
                    "\'" + mGrade + "\'," +
                    ");";

            wvAddChild.evaluateJavascript(encryptDataScript, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String encryptData) {
                    Log.d(TAG, "encrypted data for get Elements: " + encryptData);

                    JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConstants.URL_ELEMENTS, null, new com.android.volley.Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d(TAG, "ELEMENTS Response : " + response.toString());
                            String encryptData = "";
                            try {
                                if (response.getBoolean("success")){
                                    encryptData = response.getString("transcation");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                String decryptDataScript = "javascript:decryptData(\'" + encryptData + "\'" +
                                        ");";

                                wvAddChild.evaluateJavascript(decryptDataScript, new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String decryptData) {
                                        Log.d(TAG, "decrypted data for Elements: " + decryptData);
//                TODO parse values to Elements.java
                                        List<Element> elements = new ArrayList<>();
                                        ArrayAdapter<Element> elementsAdapter = new ArrayAdapter<>(AddChildActivity.this,
                                                android.R.layout.simple_spinner_item, elements);
                                        spinner_elements.setAdapter(elementsAdapter);
                                    }
                                });
                            } else {
                                Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
                            }

                        }

                    }, new com.android.volley.Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "ELEMENTS Error Response : " + error);
                            Toast.makeText(AddChildActivity.this, "Error saving child", Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        //This is for Headers If You Needed
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            headers.put("x-access-token", AppConstants.ACCESS_TOKEN);
                            return headers;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(AddChildActivity.this);
                    queue.add(request);
                }
            });
        } else {
            Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }

    }

    private void setAllergies() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, AppConstants.URL_ALLERGIES, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Allergies Response : " + response.toString());
                String encryptData = "";
                try {
                    if (response.getBoolean("success")){
                        encryptData = response.getString("transcation");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    String decryptDataScript = "javascript:decryptData(\'" + encryptData + "\'" +
                            ");";

                    wvAddChild.evaluateJavascript(decryptDataScript, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String decryptData) {
                            Log.d(TAG, "decrypted data for Allergies: " + decryptData);
//                TODO parse values to Allergy.java
                            List<Element> elements = new ArrayList<>();
                            ArrayAdapter<Element> elementsAdapter = new ArrayAdapter<>(AddChildActivity.this,
                                    android.R.layout.simple_spinner_item, elements);
                            spinner_elements.setAdapter(elementsAdapter);
                        }
                    });
                } else {
                    Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
                }
                List<Allergy> allergies = new ArrayList<>();
                ArrayAdapter<Allergy> allergiesAdapter = new ArrayAdapter<>(AddChildActivity.this,
                        android.R.layout.simple_spinner_item, allergies);
                spinner_allergies.setAdapter(allergiesAdapter);
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Allergies Error Response : " + error);
                Toast.makeText(AddChildActivity.this, "Error saving child", Toast.LENGTH_SHORT).show();
            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-access-token", AppConstants.ACCESS_TOKEN);
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(AddChildActivity.this);
        queue.add(request);
    }

    private void setAddChildScript() {
        String data = "<!DOCTYPE html><html><body>" +
                "<script src=\"rollups/aes.js\"></script>\n" +
                "<script src=\"components/aes.js\"></script>\n" +
                "<script src=\"components/enc-base64-min.js\"></script>\n" +
                "<script src=\"js/add-child.js\"></script>\n" +
                "<script src=\"js/elements-encrypt.js\"></script>\n" +
                "<script src=\"js/decrypt.js\"></script>\n" +
                "</body></html>";
        wvAddChild.loadDataWithBaseURL("file:///android_asset/", data, "text/html", "charset=UTF-8", null);
    }

    @Override
    public void onClick(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            String regId = etRegId.getText().toString();
            String schName = etSchName.getText().toString();
            String firstName = childFirstName.getText().toString();
            String lastName = childLastName.getText().toString();
            String elements = (String) spinner_elements.getSelectedItem();
            String allergies = (String) spinner_allergies.getSelectedItem();
            String fav = etFav.getText().toString();
            String splIntsrn = etSplIntsrn.getText().toString();
            String decryptData = "javascript:encryptChildData(\'" + null + "\'," +
                    "\'" + null + "\'," +
                    "\'" + firstName + "\'," +
                    "\'" + lastName + "\'," +
                    "\'" + regId + "\'," +
                    "\'" + fav + "\'," +
                    "\'" + splIntsrn + "\'," +
                    "\'" + m + "\'," +
                    "\'" + elements + "\'," +
                    "\'" + 1 + "\'," +
                    ");";

            wvAddChild.evaluateJavascript(decryptData, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String encryptData) {
                    Log.d(TAG, "encrypted data for Add Chile : " + encryptData);
                    saveChild(encryptData);
                }
            });
        } else {
            Toast.makeText(AddChildActivity.this, "evaluateJavascript call require api level 19 (KitKat)", Toast.LENGTH_LONG).show();
        }
    }

    private void saveChild(String encryptData) {
        Map<String, String> encryptMap = new HashMap<>();
        encryptMap.put("data", encryptData);
        JSONObject encryptJson = null;
        try {
            encryptJson = new JSONObject(encryptMap.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, AppConstants.URL_CHILD_SAVE, encryptJson, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "Save Child Response : " + response.toString());
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Save Child Error Response : " + error);
                Toast.makeText(AddChildActivity.this, "Error saving child", Toast.LENGTH_SHORT).show();
            }
        }) {
            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("x-access-token", AppConstants.ACCESS_TOKEN);
                return headers;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(AddChildActivity.this);
        queue.add(request);
    }
}
