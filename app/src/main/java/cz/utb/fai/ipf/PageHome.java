package cz.utb.fai.ipf;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

import cz.utb.fai.ipf.api.ApiClient;
import cz.utb.fai.ipf.api.ApiInterface;
import cz.utb.fai.ipf.api.pojo.ResponseData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by J on 05.01.2018.
 */

public class PageHome implements View.OnClickListener {
    private Activity mActivity;
    private TextView mResult;
    private Button mFindIp;
    private Button mFindMyIp;
    private EditText mEditText;

    public PageHome(Activity activity ) {
        mActivity = activity;
        mFindIp = (Button) activity.findViewById(R.id.btnFindIp);
        mFindIp.setOnClickListener(this);
        mFindMyIp = (Button) activity.findViewById(R.id.btnFindMyIp);
        mFindMyIp.setOnClickListener(this);

        mResult = (TextView) activity.findViewById(R.id.tvResult);
        mEditText = (EditText) activity.findViewById(R.id.etIpAddr);

    }

    @Override
    public void onClick(View view) {

        String IpLocate = mEditText.getText().toString();

        switch (view.getId() )
        {
            case R.id.btnFindIp:
                if( !IpLocate.isEmpty())
                {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<ResponseData> call = apiService.getIpLocation(IpLocate);
                    call.enqueue(new MyCall(IpLocate));
                }
                break;
            case R.id.btnFindMyIp:
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ResponseData> call = apiService.getMyLocation();
                call.enqueue(new MyCall(IpLocate));
                break;
        }
    }


    private class MyCall implements Callback<ResponseData> {
        private String mIp;

        private MyCall(String Ip) {
            mIp = Ip;
        }

        @Override
        public void onResponse(Call<ResponseData> call,
                               Response<ResponseData> response) {

            if (mResult != null)
            {
                ResponseData responseData= response.body();
                if( responseData != null )
                {
                    String responseText = "";
                    if( responseData.getStatus().compareTo("success") == 0 )
                    {
                        String responseStatus = response.body().getStatus();
                        String city = response.body().getCity();
                        responseText = "IP address " + responseData.getQuery();
                        responseText += "\n"+ ("Location: " + responseData.getCountry() + ", " + responseData.getCity() );
                        responseText += "\n" + "Organization: " + responseData.getOrganization();

                        ((MainActivity)mActivity).addToHistory( responseText );
                    }
                    else
                    {
                        responseText = "Invalid query: " + responseData.getQuery();
                    }

                    mResult.setText(responseText);
                    mResult.setVisibility(View.VISIBLE);
                }
                else
                {
                    mResult.setText("Error!");
                    mResult.setVisibility(View.VISIBLE);
                }

            }
        }

        @Override
        public void onFailure(Call<ResponseData> call, Throwable t) {
            System.out.println("onFailure");
            t.printStackTrace();
        }
    }

}

