package cz.utb.fai.ipf.api;

import cz.utb.fai.ipf.api.pojo.ResponseData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/**
 * Created by J on 05.01.2018.
 */

public interface ApiInterface {
    /**
     *
     * @param searchedString Searched string
     * @param langPair e.g. cs|en
     * @return
     */
   /* @GET("get")
    Call<ResponseIpf> getTranslation(
            @Query("q") String searchedString,
            @Query("langpair") String langPair
    );*/

    @GET("json")
    Call<ResponseData> getMyLocation();

    @GET ("json/{ip}")
    Call<ResponseData> getIpLocation(
            @Path("ip") String Ip
    );

}
