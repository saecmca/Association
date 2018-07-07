package com.association.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.association.R;
import com.association.registration.LangModel;

import java.util.List;


public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyHolder> {

  private Context mcontent;
  private List<LangModel> arrayList;
  private int selectedIndex;

  public LanguageAdapter(Context context, List<LangModel> datelist) {
    arrayList = datelist;
    this.mcontent = context;

  }

  public void setSelectedIndex(int ind) {
    selectedIndex = ind;
    notifyDataSetChanged();
  }

  @Override
  public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_cards, parent, false);

    return new MyHolder(view);
  }

  @Override
  public void onBindViewHolder(MyHolder holder, int position) {

    try {

      holder.tvDate.setText(arrayList.get(position).getLanguage_Name());
      if(arrayList.get(position).getLanguage_Name().contains("English")){
        holder.ivFlag.setImageResource(R.drawable.engl);
      }else {
        holder.ivFlag.setImageResource(R.drawable.malay);
      }
        if (selectedIndex != -1 && position == selectedIndex) {
          holder.ivCheck.setImageResource(R.drawable.lan_check);
        } else {
          holder.ivCheck.setImageResource(R.drawable.uncheck);
        }


    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public int getItemCount() {
    return arrayList.size();
  }

  public class MyHolder extends RecyclerView.ViewHolder {
    TextView tvDay, tvDate;
     ImageView ivFlag,ivCheck;

    public MyHolder(View itemView) {
      super(itemView);
      tvDate = (TextView) itemView.findViewById(R.id.city_list_text);
      ivFlag=itemView.findViewById(R.id.ivflag);
      ivCheck=itemView.findViewById(R.id.cards_btn);

    }
  }
}
