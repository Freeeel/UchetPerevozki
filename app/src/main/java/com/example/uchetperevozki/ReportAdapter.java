package com.example.uchetperevozki;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uchetperevozki.Model.Report;
import com.example.uchetperevozki.ModelRequest.ReportResponse;

import org.w3c.dom.Text;

import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {

    private List<ReportResponse> reportList;

    public ReportAdapter(List<ReportResponse> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card, parent, false);
        return new ReportViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        ReportResponse report = reportList.get(position);

        holder.textNumCard.setText(String.valueOf(position + 1));
//      holder.textNumCard.setText(String.valueOf(report.getId()))
        holder.textFromCard.setText(report.getPointDeparture() != null ? report.getPointDeparture() : "N/A");
        holder.textWhereCard.setText(report.getPointDestination() != null ? report.getPointDestination() : "N/A");
        holder.textDateCard.setText(report.getReportDateTime() != null ? report.getReportDateTime() : "N/A");
        holder.textViewCard.setText(report.getViewWood() != null ? report.getViewWood() : "N/A");
        holder.textVolumeCard.setText(String.valueOf(report.getVolumeWood() +" " + "куб.м."));
        holder.textAssortmentCard.setText(report.getAssortmentWoodType() != null ? report.getAssortmentWoodType() : "N/A");
        holder.textVarietyCard.setText(report.getVarietyWoodType() != null ? report.getVarietyWoodType() : "N/A");
        int lengthWood = report.getLengthWood();
        String lengthUnit = getLengthUnit(lengthWood);
        holder.textLongCard.setText(lengthWood + " " + lengthUnit);
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }



    static class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView textNumCard;
        TextView textDateCard;
        TextView textFromCard;
        TextView textWhereCard;
        TextView textViewCard;
        TextView textLongCard;
        TextView textVolumeCard;
        TextView textAssortmentCard;
        TextView textVarietyCard;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            textNumCard = itemView.findViewById(R.id.numReportCard);
            textDateCard = itemView.findViewById(R.id.dateReportCard);
            textFromCard = itemView.findViewById(R.id.fromReportCard);
            textWhereCard = itemView.findViewById(R.id.whereReportCard);
            textViewCard = itemView.findViewById(R.id.viewReportCard);
            textLongCard = itemView.findViewById(R.id.longReportCard);
            textVolumeCard = itemView.findViewById(R.id.volumeReportCard);
            textAssortmentCard = itemView.findViewById(R.id.assortmentReportCard);
            textVarietyCard = itemView.findViewById(R.id.varietyReportCard);
        }
    }

    private String getLengthUnit(int lengthWood) {
        if (lengthWood == 1) {
            return "метр";
        } else if (lengthWood >= 2 && lengthWood <= 4) {
            return "метра";
        } else {
            return "метров";
        }
    }

}