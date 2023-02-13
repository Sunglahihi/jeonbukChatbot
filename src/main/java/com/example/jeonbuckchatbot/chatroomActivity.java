package com.example.jeonbuckchatbot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Date;
import java.text.SimpleDateFormat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;


public class chatroomActivity extends Activity {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");
    EditText edtText;
    ImageButton imgBtn;
    private testMyAdapter adpt;


    private ArrayList<testDataItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);


        initData();

        RecyclerView recyclerv = findViewById(R.id.recyvlerv);
        adpt = new testMyAdapter(dataList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerv.setLayoutManager(manager);
        recyclerv.setAdapter(new testMyAdapter(dataList, this));
        imgBtn = (ImageButton) findViewById(R.id.chatRoomSendBtn);
        edtText = (EditText) findViewById(R.id.editText1);




        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dataList = new ArrayList<>();
                String sendText = edtText.getText().toString();
                dataList.add(new testDataItem(sendText, null, testCode.ViewType.RIGHT_CONTENT, getTime()));
                if (sendText.equals("전주")) {
                    dataList.add(new testDataItem("전주의 문화재를 추천해드릴까요?", null, testCode.ViewType.LEFT_CONTENT, getTime()));
                }
                if(sendText.equals("네")) {
                    dataList.add(new testDataItem("전주지역의 문화재를 추천해렸습니다", null, testCode.ViewType.LEFT_CONTENT_MAP, getTime()));
                    dataList.add(new testDataItem("주변 화장실과 음식점중 무엇을 알려드릴까요?", null, testCode.ViewType.LEFT_CONTENT, getTime()));

                }

            }
        });

    }

    private void initData() {
        dataList = new ArrayList<>();
        dataList.add(new testDataItem("전부기가 입장했습니다.", null, testCode.ViewType.CENTER_CONTENT, getTime()));
        dataList.add(new testDataItem("사용자께서 입장하셨습니다.", null, testCode.ViewType.CENTER_CONTENT, getTime()));
        dataList.add(new testDataItem("안녕하세요!", null, testCode.ViewType.LEFT_CONTENT, getTime()));
        dataList.add(new testDataItem("저는 여러분을 도와줄 전부기라고 합니다\uD83D\uDE09\uD83D\uDE09", null, testCode.ViewType.LEFT_CONTENT, getTime()));
        dataList.add(new testDataItem("채팅을 시작하시겠습니까?", null, testCode.ViewType.LEFT_CONTENT, getTime()));

//        dataList.add(new testDataItem("시작하겠습니다.", null, testCode.ViewType.RIGHT_CONTENT, getTime()));

    }


    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate =  new Date(mNow);
        return mFormat.format(mDate);
    }
}

