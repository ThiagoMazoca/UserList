package thiago.mazoca.userlist.api;

public class UserAPI extends BaseAPI {

    private IUserAPI mUserAPI;

    /**
     * Retrofit Constructor for API calls
     */
    public UserAPI() {
        mUserAPI = super.getRetrofit().create(IUserAPI.class);
    }

    public IUserAPI getUserAPI() {
        return mUserAPI;
    }
}
