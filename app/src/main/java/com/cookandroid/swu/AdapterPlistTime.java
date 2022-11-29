package com.cookandroid.swu;

import static com.cookandroid.swu.Fragment.PlistFragment.check;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AdapterPlistTime extends BaseAdapter{
    String memo, day;
    // 버튼이 체크되었는지 확인



    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItemPlistTime> listViewItemList = new ArrayList<ListViewItemPlistTime>();

    public AdapterPlistTime() {
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        // 현재시간 가져오기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm");
        String getTime = dateFormat.format(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
        String getDay = dayFormat.format(date);

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_plist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        final ImageView plistLvImg = (ImageView) convertView.findViewById(R.id.plistLvImg);
        final TextView plistLvtName = (TextView) convertView.findViewById(R.id.plistLvtName);
        final TextView plistLvtDay = (TextView) convertView.findViewById(R.id.plistLvtDay);
        Button[] btnTime = new Button[6];
        Integer[] btnTimeIds = {R.id.plistLvTime1, R.id.plistLvTime2, R.id.plistLvTime3, R.id.plistLvTime4,
                R.id.plistLvTime5, R.id.plistLvTime6};
        for(int i=0;i<6;i++){
            final int index = i;
            btnTime[index] = convertView.findViewById(btnTimeIds[index]);
            btnTime[index].setTag(position);
        }
        LinearLayout plLinear = convertView.findViewById(R.id.plLinear);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final ListViewItemPlistTime listViewItem = listViewItemList.get(position);


        // 아이템 내 각 위젯에 데이터 반영
        plistLvImg.setImageBitmap(listViewItem.getBitmap());
        plistLvtName.setText(listViewItem.getName());
        memo = listViewItem.getMemo();
        day = listViewItem.getDay();
        Integer btnListCount = listViewItem.getPlListCount();

        // 11/27 (일) 삭제 하나 할 때는 괜찮은데 여러개를 계속 삭제하면 버튼 속의 내용이 바뀌어보임
        // 버튼 속의 내용이 아니라 버튼 pill / pill_fill 부분이 이전 데이터에서 넘어옴
        // 삭제된 부분의 pill / pill_fill 데이터가 삭제되지 않고 남아있는 것 같음

        // 해당 요일이면 시간 버튼을 보여줌
        if (day.contains(getDay)) {
            plistLvtDay.setVisibility(View.GONE);
            // listCount에 따라 버튼 보이도록
            switch (btnListCount) {
                case 1:
                    for(int i=0;i < 6;i++){
                        final int index = i;
                        switch (index){
                            case 0:
                                btnTime[0].setVisibility(View.VISIBLE);
                                btnTime[0].setText(listViewItem.getChangedTime1());
                                if((check.get(position+"_"+0)!=null) && (check.get(position+"_"+0))){
                                    btnTime[0].setBackgroundResource(R.drawable.pill_fill);
                                    Log.d("체크1",(index+check.get(position+"_"+index).toString()));
                                }
                                else if((check.get(position+"_"+0)==null) || (!check.get(position+"_"+0)))
                                    btnTime[0].setBackgroundResource(R.drawable.pill);
                                break;
                            default:
                                btnTime[index].setVisibility(View.GONE); break;
                        }
                    } break;
                case 2:
                    for(int i=0;i < 6;i++){
                        final int index = i;
                        switch (index){
                            case 0:
                                btnTime[0].setVisibility(View.VISIBLE);
                                btnTime[0].setText(listViewItem.getChangedTime1());
                                if((check.get(position+"_"+index)!=null) && (check.get(position+"_"+index)))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if((check.get(position+"_"+index)==null) || (!check.get(position+"_"+index)))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 1:
                                btnTime[1].setVisibility(View.VISIBLE);
                                btnTime[1].setText(listViewItem.getChangedTime2());
                                if((check.get(position+"_"+1)!=null) && (check.get(position+"_"+1))) {
                                    btnTime[1].setBackgroundResource(R.drawable.pill_fill);
                                }
                                else if((check.get(position+"_"+1)==null) || (!check.get(position+"_"+1)))
                                    btnTime[1].setBackgroundResource(R.drawable.pill);
                                break;
                            default:
                                btnTime[index].setVisibility(View.GONE); break;
                        }
                    } break;
                case 3:
                    for(int i=0;i < 6;i++){
                        final int index = i;
                        switch (index){
                            case 0:
                                btnTime[0].setVisibility(View.VISIBLE);
                                btnTime[0].setText(listViewItem.getChangedTime1());
                                if((check.get(position+"_"+index)!=null) && (check.get(position+"_"+index)))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if((check.get(position+"_"+index)==null) || (!check.get(position+"_"+index)))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 1:
                                btnTime[1].setVisibility(View.VISIBLE);
                                btnTime[1].setText(listViewItem.getChangedTime2());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 2:
                                btnTime[2].setVisibility(View.VISIBLE);
                                btnTime[2].setText(listViewItem.getChangedTime3());
                                if((check.get(position+"_"+index)!=null) && (check.get(position+"_"+index))){
//                                    for(int j= 0;j<btnListCount;j++) {
//                                        if(j==index)
                                            btnTime[index].setBackgroundResource(R.drawable.pill_fill);
//                                        else if (check.get(position + "_" + j) != null && (!check.get(position + "_" + j))){
//                                            btnTime[index].setBackgroundResource(R.drawable.pill);
//                                        }
//                                        else if(check.get(position + "_" + j) != null && (check.get(position + "_" + j))){
//                                            btnTime[index].setBackgroundResource(R.drawable.pill_fill);
//                                        }
//                                    }
                                }
                                else if((check.get(position+"_"+index)==null) || (!check.get(position+"_"+index))) {
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                }
                                break;
                            default:
                                btnTime[index].setVisibility(View.GONE); break;
                        }
                    } break;
                case 4:
                    for(int i=0;i < 6;i++){
                        final int index = i;
                        switch (index){
                            case 0:
                                btnTime[0].setVisibility(View.VISIBLE);
                                btnTime[0].setText(listViewItem.getChangedTime1());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 1:
                                btnTime[1].setVisibility(View.VISIBLE);
                                btnTime[1].setText(listViewItem.getChangedTime2());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 2:
                                btnTime[2].setVisibility(View.VISIBLE);
                                btnTime[2].setText(listViewItem.getChangedTime3());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 3:
                                btnTime[3].setVisibility(View.VISIBLE);
                                btnTime[3].setText(listViewItem.getChangedTime4());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            default:
                                btnTime[index].setVisibility(View.GONE); break;
                        }
                    } break;
                case 5:
                    for(int i=0;i < 6;i++){
                        final int index = i;
                        switch (index){
                            case 0:
                                btnTime[0].setVisibility(View.VISIBLE);
                                btnTime[0].setText(listViewItem.getChangedTime1());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 1:
                                btnTime[1].setVisibility(View.VISIBLE);
                                btnTime[1].setText(listViewItem.getChangedTime2());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 2:
                                btnTime[2].setVisibility(View.VISIBLE);
                                btnTime[2].setText(listViewItem.getChangedTime3());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 3:
                                btnTime[3].setVisibility(View.VISIBLE);
                                btnTime[3].setText(listViewItem.getChangedTime4());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            case 4:
                                btnTime[4].setVisibility(View.VISIBLE);
                                btnTime[4].setText(listViewItem.getChangedTime5());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);
                                break;
                            default:
                                btnTime[index].setVisibility(View.GONE); break;
                        }
                    } break;
                case 6:
                    for(int i=0;i < 6;i++) {
                        final int index = i;
                        btnTime[index].setVisibility(View.VISIBLE);
                        switch (index){
                            case 0:
                                btnTime[index].setText(listViewItem.getChangedTime1());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);break;
                            case 1:
                                btnTime[index].setText(listViewItem.getChangedTime2());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill);break;
                            case 2:
                                btnTime[index].setText(listViewItem.getChangedTime3());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill); break;
                            case 3:
                                btnTime[index].setText(listViewItem.getChangedTime4());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill); break;
                            case 4:
                                btnTime[index].setText(listViewItem.getChangedTime5());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                            else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                btnTime[index].setBackgroundResource(R.drawable.pill); break;
                            case 5:
                                btnTime[index].setText(listViewItem.getChangedTime6());
                                if(check.get(position+"_"+index)!=null && check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                                else if(check.get(position+"_"+index)==null || !check.get(position+"_"+index))
                                    btnTime[index].setBackgroundResource(R.drawable.pill); break;
                        }
                    }
            }
        }
        else {
            plistLvtDay.setVisibility(View.VISIBLE);
            for(int i = 0;i<6;i++){
                final int index = i;
                btnTime[index].setVisibility(View.GONE);
            }
        }

        // 약을 먹었냐는 dialog가 뜨고 dialog로 알약 이미지 색칠 및 현재/맞춤 시간으로 변경
        for(int i=0;i<6;i++) {
            final int index = i;
            btnTime[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(context);

                    dialog.setTitle("지금 약을 드셨나요?");
                    dialog.setMessage("현재 시간은 " + getTime + " 입니다");
                    dialog.setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnTime[index].setBackgroundResource(R.drawable.pill_fill);
                            check.put(position + "_" + index, true);
                            if (check.get(position + "_" + index)) {
                                btnTime[index].setText(getTime);
                                switch (index) {
                                    case 0:
                                        listViewItem.setChangedTime1(getTime);break;
                                    case 1:
                                        listViewItem.setChangedTime2(getTime);break;
                                    case 2:
                                        listViewItem.setChangedTime3(getTime);break;
                                    case 3:
                                        listViewItem.setChangedTime4(getTime);break;
                                    case 4:
                                        listViewItem.setChangedTime5(getTime);break;
                                    case 5:
                                        listViewItem.setChangedTime6(getTime);break;
                                }
                            }
                        }
                    });
                    dialog.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            btnTime[index].setBackgroundResource(R.drawable.pill);
                            check.put(position+"_"+index, false);
                            if (!check.get(position+"_"+index)){
                                switch (index) {
                                    case 0:
                                        btnTime[0].setText(listViewItem.getTime1());break;
                                    case 1:
                                        btnTime[1].setText(listViewItem.getTime2());break;
                                    case 2:
                                        btnTime[2].setText(listViewItem.getTime3());break;
                                    case 3:
                                        btnTime[3].setText(listViewItem.getTime4());break;
                                    case 4:
                                        btnTime[4].setText(listViewItem.getTime5());break;
                                    case 5:
                                        btnTime[5].setText(listViewItem.getTime6());break;
                                }
                            }

                        }
                    });
                    dialog.show();
                }
            });
        }

        // 아이템 클릭하면 이름 / 복용요일 / 메모 뜨는 다이얼로그
        plLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog;
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.plistdialoginfo);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // 변수 설정
                TextView plTextName = dialog.findViewById(R.id.plTextName);
                TextView plTextMemo = dialog.findViewById(R.id.plTextMemo);
                TextView plTextDay = dialog.findViewById(R.id.plTextDay);
                Button plBtnSave = dialog.findViewById(R.id.plBtnSave);


                plTextName.setText(listViewItemList.get(pos).getName());
                plTextMemo.setText(listViewItemList.get(pos).getMemo());
                plTextDay.setText(listViewItemList.get(pos).getDay());
                plBtnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        // 아이템 롱 클릭시 삭제
        plLinear.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Dialog dialog;
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialogdel);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                Button plBtnNo = dialog.findViewById(R.id.no);
                Button plBtnYes = dialog.findViewById(R.id.yes);

                // 취소 버튼
                plBtnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                plBtnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 삭제하면 버튼 시간들 바뀌고 이상해짐 약간 밀리는 느낌?
                        int j = position;
                        // 현재 버튼의 check를 이후 버튼의 check로 변경
                        while (j < listViewItemList.size()) {
                            for(int i = 0;i < 6;i++) {
                                if(j == listViewItemList.size()-1)
                                    check.put(j+"_"+i, false);
                                else {
                                    check.put(j + "_" + i, check.get(j + 1 + "_" + i));
                                }
                            }
                            j++;
                        }
                        listViewItemList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return false;
            }
        });
        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(Integer listCount, Bitmap icon, String name, String memo, String day,
                        String time1, String time2, String time3, String time4, String time5, String time6) {
        ListViewItemPlistTime item = new ListViewItemPlistTime();

        item.setListCount(listCount);
        item.setBitmap(icon);
        item.setName(name);
        item.setMemo(memo);
        item.setDay(day);
        item.setTime1(time1);
        item.setTime2(time2);
        item.setTime3(time3);
        item.setTime4(time4);
        item.setTime5(time5);
        item.setTime6(time6);

        listViewItemList.add(item);
    }


}
