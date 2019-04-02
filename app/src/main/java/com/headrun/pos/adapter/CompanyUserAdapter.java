package com.headrun.pos.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.headrun.pos.R;
import com.headrun.pos.model.ProductModel;
import com.headrun.pos.model.UserModel;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyUserAdapter extends RecyclerView.Adapter<CompanyUserAdapter.ViewHolder> {

    private Context context;
    List<UserModel> list = new ArrayList<>();

    public CompanyUserAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CompanyUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_users, viewGroup, false);
        return new CompanyUserAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyUserAdapter.ViewHolder viewHolder, final int i) {

        final UserModel model = list.get(i);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public List<UserModel> getList() {
        return list;
    }

    public void setList(List<UserModel> list) {
        this.list = list;
    }
}
