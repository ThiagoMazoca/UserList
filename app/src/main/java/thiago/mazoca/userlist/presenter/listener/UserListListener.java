package thiago.mazoca.userlist.presenter.listener;

import java.util.List;

import thiago.mazoca.userlist.model.User;

public interface UserListListener extends BaseListener {

    void onUserListLoaded(List<User> userList);

    void onUserListFailed();
}
