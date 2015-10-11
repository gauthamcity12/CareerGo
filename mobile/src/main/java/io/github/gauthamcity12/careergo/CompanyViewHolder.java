package io.github.gauthamcity12.careergo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;


/**
 * Created by gauthamcity12 on 10/10/15.
 */
public class CompanyViewHolder extends RecyclerView.ViewHolder {

    protected TextView companyName;
    protected TextView positions;
    protected TextView description;
    protected CardView card;

    public CompanyViewHolder(View itemView) {
        super(itemView);
        companyName = (TextView) itemView.findViewById(R.id.company);
        positions = (TextView) itemView.findViewById(R.id.position);
        description = (TextView) itemView.findViewById(R.id.description);
        card = (CardView)itemView;
    }
}
