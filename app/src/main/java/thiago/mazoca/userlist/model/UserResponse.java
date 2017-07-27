
package thiago.mazoca.userlist.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResponse {

    @SerializedName("users")
    @Expose
    public List<User> users = null;
}
