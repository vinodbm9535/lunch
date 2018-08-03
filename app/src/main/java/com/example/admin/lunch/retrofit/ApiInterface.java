package com.example.admin.lunch.retrofit;

import com.example.admin.lunch.LoginResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/*public interface ApiInterface {
    @FormUrlEncoded
    @Headers({"Accept: application/json"})
    @POST("authenticate")
    Call<ResponseBody> userLogin(@Field("EmailAddress") String EmailAddress, @Field("Password") String Password);
}*/

public interface ApiInterface {

    @FormUrlEncoded // annotation used in POST type requests
    @Headers({"Content-Type:application/json"})
//    @Headers({"authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhZG1pbiIsImVtYWlsIjoiYWRtaW4iLCJqdGkiOiIwM2ViNzgyYS05ZDIyLTQ2M2QtODJhOC05MWYwNDhlMTFjYTAiLCJpYXQiOjE1MzExMTE1OTQsIm5iZiI6MTUzMTExMTU5NCwiZXhwIjoxNTMxMTI5NTk0LCJpc3MiOiJBR1NST1NFUlZJQ0UiLCJhdWQiOiJBR1NST1NFUlZJQ0VBVURJRU5DRSJ9.OXw_TquSoJmRdf0LtXO03MDEVKTgpGhSThGampiFk0E"})
    @POST("authenticate")
        // API's endpoints
    Call<ResponseBody> userLogin(
         @Field("data") String data);


}

