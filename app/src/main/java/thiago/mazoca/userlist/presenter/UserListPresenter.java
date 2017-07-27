package thiago.mazoca.userlist.presenter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import thiago.mazoca.userlist.api.UserAPI;
import thiago.mazoca.userlist.model.UserResponse;
import thiago.mazoca.userlist.presenter.listener.UserListListener;

public class UserListPresenter {

    private UserAPI mUserAPI;

    /**
     * Class constructor
     */
    public UserListPresenter() {
        mUserAPI = new UserAPI();
    }

    /**
     * Load User List
     *
     * @param listener - UserListListener to handle Callbacks
     */
    public void loadUserList(final UserListListener listener) {
        mUserAPI.getUserAPI()
                .getUserList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserResponse>() {
                     @Override
                    public void onSubscribe(@NonNull Disposable d) {
                         listener.onRequestStarted();
                    }

                    @Override
                    public void onNext(@NonNull UserResponse userResponse) {
                        if (userResponse != null && userResponse.users != null) {
                            listener.onUserListLoaded(userResponse.users);
                        } else {
                            listener.onUserListFailed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onRequestFinished();
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onRequestFinished();
                    }
                });
    }
}
