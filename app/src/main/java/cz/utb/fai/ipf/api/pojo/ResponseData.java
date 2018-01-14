package cz.utb.fai.ipf.api.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by J on 05.01.2018.
 */

public class ResponseData {

    @SerializedName("status")
    private String status;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("org")
    private String organization;
    @SerializedName("query")
    private String query;

    public String getStatus()
    {
        return status;
    }
    public void setStatus( String Status )
    {
        status = Status;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String City )
    {
        city = City;
    }

    public String getCountry()
    {
        return  country;
    }

    public void setCountry( String Country )
    {
        country = Country;
    }

    public String getOrganization()
    {
        return organization;
    }

    public void setOrganization( String Organization )
    {
        organization = Organization;
    }

    public String getQuery()
    {
        return query;
    }

    public void setQuery(String Query)
    {
        query = Query;
    }
}
