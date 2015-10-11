package io.github.gauthamcity12.careergo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gauthamcity12 on 10/10/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<CompanyViewHolder> {

    private List<CompanyInfo> compList;

    public RecyclerAdapter(List<CompanyInfo> compList){
        this.compList = new ArrayList<CompanyInfo>();
        this.compList.addAll(compList);
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new CompanyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        CompanyInfo ci = compList.get(position);
        holder.companyName.setText(ci.getCompanyName());
        holder.positions.setText(ci.getPositions());
        holder.description.setText(ci.getDescription());
    }


    @Override
    public int getItemCount() {
        return compList.size();
    }
}
