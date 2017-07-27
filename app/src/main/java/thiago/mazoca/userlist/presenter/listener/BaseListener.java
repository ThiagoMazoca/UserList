package thiago.mazoca.userlist.presenter.listener;

public interface BaseListener {

    void onRequestStarted();

    void onRequestFinished();

    void onError(Throwable t);
}
