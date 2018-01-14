package cz.utb.fai.ipf.api.pojo;
import com.google.gson.annotations.SerializedName;

/**
 * Created by J on 05.01.2018.
 */

public class ResponseIpf {

   // @SerializedName("responseData")
    private ResponseData responseData;

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
