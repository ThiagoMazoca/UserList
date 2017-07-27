
package thiago.mazoca.userlist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("picture_url")
    @Expose
    public String pictureUrl;
}
