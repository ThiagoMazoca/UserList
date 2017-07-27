package thiago.mazoca.userlist.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import thiago.mazoca.userlist.model.UserResponse;

public interface IUserAPI {

    @GET("597932941300008d08c10181")
    Observable<UserResponse> getUserList();
}
