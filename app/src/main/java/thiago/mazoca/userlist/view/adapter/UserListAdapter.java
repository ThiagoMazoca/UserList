package thiago.mazoca.userlist.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import thiago.mazoca.userlist.R;
import thiago.mazoca.userlist.model.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListAdapterViewHolder> {

    private List<User> mUserList;
    private Context mContext;

    public UserListAdapter(Context context, List<User> userList) {
        this.mUserList = userList;
        this.mContext = context;
    }

    @Override
    public UserListAdapter.UserListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_row, parent, false);

        return new UserListAdapterViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(UserListAdapter.UserListAdapterViewHolder holder, int position) {
        User user = mUserList.get(position);

        holder.tvId.setText(String.format("ID: %d", user.id));
        String[] nameArray = user.name.split(" ");
        holder.tvName.setText(String.format("%s %s.", nameArray[0], nameArray[1].substring(0, 1)));
        Picasso.with(mContext).load(user.pictureUrl).into(holder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return this.mUserList.size();
    }

    public void setList(List<User> newUserList) {
        mUserList.clear();
        mUserList.addAll(newUserList);
        notifyDataSetChanged();
    }

    public static class UserListAdapterViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivPicture;
        public TextView tvId;
        public TextView tvName;

        public UserListAdapterViewHolder(View itemView) {
            super(itemView);

            this.tvId = (TextView) itemView.findViewById(R.id.tvId);
            this.tvName = (TextView) itemView.findViewById(R.id.tvName);
            this.ivPicture = (ImageView) itemView.findViewById(R.id.ivPicture);
        }
    }
}
