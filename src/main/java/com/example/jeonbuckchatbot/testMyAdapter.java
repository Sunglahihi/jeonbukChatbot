package com.example.jeonbuckchatbot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Date;
import java.text.SimpleDateFormat;



import java.util.ArrayList;

public class testMyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("HH:MM");
    private ArrayList<testDataItem> myDataList = null;

    private Context mContext; // 추가

    public testMyAdapter(ArrayList<testDataItem> dataList, Context context) {
        myDataList = dataList;
        mContext = context;
    }

    public testMyAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewType== testCode.ViewType.CENTER_CONTENT) {
            view = inflater.inflate(R.layout.chat_room_center_item, parent, false);
            return new CenterViewHolder(view);
        } else if(viewType == testCode.ViewType.LEFT_CONTENT) {
            view = inflater.inflate(R.layout.chat_left_item, parent, false);
            return new LeftViewHolder(view);
        } else if(viewType == testCode.ViewType.LEFT_CONTENT_MAP) {
            view = inflater.inflate(R.layout.chat_left_item_btn, parent, false);
            return new LeftViewHolderM(view);
        }
        else {
            view = inflater.inflate(R.layout.chat_room_right_item, parent, false);
            return new RightViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CenterViewHolder) {
            ((CenterViewHolder)viewHolder).textv.setText(myDataList.get(position).getContent());
        } else if(viewHolder instanceof LeftViewHolder) {
            ((LeftViewHolder) viewHolder).left_textv_msg.setText(myDataList.get(position).getContent());
            ((LeftViewHolder) viewHolder).left_textv_time.setText(myDataList.get(position).getTime());
        } else if(viewHolder instanceof LeftViewHolderM) {
            ((LeftViewHolderM) viewHolder).left_textv_timeM.setText(myDataList.get(position).getTime());
            ((LeftViewHolderM) viewHolder).btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, viewMap.class);
                    mContext.startActivity(intent);
                }
            });
        }
        else {
            ((RightViewHolder)viewHolder).right_textv_msg.setText(myDataList.get(position).getContent());
            ((RightViewHolder)viewHolder).right_textv_time.setText(myDataList.get(position).getTime());
        }
    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

    // 채팅 추가
    public void addChat(testDataItem data){
        myDataList.add(data);
    }

    @Override
    public int getItemViewType(int position) { return myDataList.get(position).getViewType();}

    public class CenterViewHolder extends RecyclerView.ViewHolder {
        TextView textv;

        public CenterViewHolder(@NonNull View itemView) {
            super(itemView);
            textv = (TextView)itemView.findViewById(R.id.textv);
        }
    }

    public class LeftViewHolderM extends RecyclerView.ViewHolder {
        Button btn;
        TextView left_textv_timeM;

        public LeftViewHolderM(@NonNull View itemView) {
            super(itemView);
            btn = (Button) itemView.findViewById(R.id.mapBtn);
            left_textv_timeM = (TextView)itemView.findViewById(R.id.left_textv_timeM);

        }
    }

    public class LeftViewHolder extends RecyclerView.ViewHolder {
        TextView left_textv_msg;
        TextView left_textv_time;

        public LeftViewHolder(@NonNull View itemView) {
            super(itemView);
            left_textv_msg = (TextView)itemView.findViewById(R.id.left_textv_msg);
            left_textv_time = (TextView)itemView.findViewById(R.id.left_textv_time);


        }
    }

    public class RightViewHolder extends RecyclerView.ViewHolder {
        TextView right_textv_msg;
        TextView right_textv_time;

        public RightViewHolder(@NonNull View itemView) {
            super(itemView);
            right_textv_msg = (TextView)itemView.findViewById(R.id.right_textv_msg);
            right_textv_time = (TextView)itemView.findViewById(R.id.right_textv_time);
        }
    }
}
