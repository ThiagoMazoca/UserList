package thiago.mazoca.userlist.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import thiago.mazoca.userlist.R;
import thiago.mazoca.userlist.model.User;
import thiago.mazoca.userlist.presenter.UserListPresenter;
import thiago.mazoca.userlist.presenter.listener.UserListListener;
import thiago.mazoca.userlist.view.adapter.UserListAdapter;

public class UserListActivity extends AppCompatActivity {

    @BindView(R.id.rvUserList) RecyclerView rvUserList;
    @BindView(R.id.ibNext) ImageButton ibNext;
    @BindView(R.id.ibPrevious) ImageButton ibPrevious;

    private UserListPresenter mUserListPresenter;
    private UserListAdapter mUserListAdapter;
    private Map<Integer, List<User>> mUserListMap;
    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        ButterKnife.bind(this);

        startList();

        mUserListPresenter = new UserListPresenter();
        mUserListPresenter.loadUserList(new UserListListener() {
            @Override
            public void onUserListLoaded(List<User> userList) {
                generateUserListMap(userList);
            }

            @Override
            public void onUserListFailed() {
                Toast.makeText(UserListActivity.this, R.string.api_load_error, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRequestStarted() {
                // Mostrar loading
            }

            @Override
            public void onRequestFinished() {
                // Esconder loading
            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(UserListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void startList() {
        mUserListAdapter = new UserListAdapter(this, new ArrayList<User>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(linearLayoutManager);
        rvUserList.setAdapter(mUserListAdapter);
    }

    private void generateUserListMap(List<User> userList) {
        mUserListMap = new HashMap<>();
        int totalPages = (int) Math.ceil(userList.size() / 3);

        for (int i = 0; i < totalPages; i++) {
            int startIndex = i * 3;
            List<User> pageList = new ArrayList<>();
            pageList.add(userList.get(startIndex));
            pageList.add(userList.get(startIndex + 1));
            pageList.add(userList.get(startIndex + 2));
            mUserListMap.put(i, pageList);
        }

        setPage();
    }

    private void setPage() {
        mUserListAdapter.setList(mUserListMap.get(mCurrentPage));

        if (mCurrentPage > 0) {
            ibPrevious.setVisibility(View.VISIBLE);
        } else {
            ibPrevious.setVisibility(View.INVISIBLE);
        }

        if (mCurrentPage < (mUserListMap.size() - 1)) {
            ibNext.setVisibility(View.VISIBLE);
        } else {
            ibNext.setVisibility(View.INVISIBLE);
        }
    }

    @OnClick(R.id.ibNext)
    public void onNextClick() {
        mCurrentPage += 1;
        setPage();
    }

    @OnClick(R.id.ibPrevious)
    public void onPreviousClick() {
        if (mCurrentPage > 0) {
            mCurrentPage -= 1;
        }
        setPage();
    }
}
