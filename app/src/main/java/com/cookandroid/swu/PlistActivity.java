
package com.cookandroid.swu;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TimePicker;

import com.cookandroid.swu.Fragment.PlistFragment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PlistActivity extends AppCompatActivity implements AdapterPlistAlertTime.ListBtnClickListener{
    public static Integer plListCount = 1;
    ListView plistListTime;
    AdapterPlistAlertTime adapter;
    public static String plName="", plMemo="", plDay="";
    String imagePath="";
    ImageButton plistBtnPicture;
    EditText plistEdtName, plistEdtRealMemo;
    Button btnDay[] = new Button[7];
    Integer btnDayIDs[] = {R.id.plistBtnMon, R.id.plistBtnTue, R.id.plistBtnWed,
            R.id.plistBtnThu, R.id.plistBtnFri, R.id.plistBtnSat, R.id.plistBtnSun};
    Button plistBtnTime, plistBtnSave, plistBtnTimeOk;
    // TimePickerDialog를 띄웠을 때 시간 설정
    int alarmHour = 0, alarmMinute = 0;
    public static String[] plTime = {"", "", "", "", "", ""};
    String[] time = {"", "", "", "", "", ""};
    // 카메라
    final int CAMERA = 100; // 카메라 선택 시 인텐트로 보내는 값
    final int GALLERY = 101; // 갤러리 선택 시 인텐트로 보내는 값
    Dialog dialogCamera;
    Intent intentC;
    public static Bitmap bitmap = null;



    /* 카메라 갤러리 */
    // 파일 생성 메소드
    File createImageFile() throws IOException {
        // 이미지 파일 생성
        SimpleDateFormat imageDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
        // 파일명 중복을 피하기 위한 "yyyyMMdd_HHmmss" 꼴의 timeStamp
        String timeStamp = imageDate.format(new Date());
        String fileName = "IMAGE_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        // 이미지 파일 생성
        File file = File.createTempFile(fileName, ".jpg", storageDir);
        // 파일 절대경로 저장
        imagePath = file.getAbsolutePath();

        return file;
    }
    // 카메라 이미지 세팅
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            // 결과가 있을 경우
            switch (requestCode) {
                case GALLERY:
                    // 갤러리에서 이미지로 선택한 경우
                    Cursor cursor = getContentResolver().query(data.getData(),
                            null, null, null, null);
                    if(cursor != null) {
                        cursor.moveToFirst();
                        int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                        imagePath = cursor.getString(index);
                        bitmap = BitmapFactory.decodeFile(imagePath);
                        cursor.close();
                    }
                    // InputStream으로 이미지 세팅하기
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                // 카메라로 이미지 가져온 경우
                case CAMERA:
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    // 이미지 축소 정도. 원 크기에서 1/inSampleSize로 축소
                    options.inSampleSize = 5;
                    bitmap = BitmapFactory.decodeFile(imagePath, options);

                    // 사진이 왼쪽으로 90도 회전되어 나와서 오른쪽으로 90도 회전
                    Matrix rotateMatrix = new Matrix();
                    rotateMatrix.postRotate(90);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                            bitmap.getWidth(), bitmap.getHeight(), rotateMatrix, false);
                    break;
            }
            plistBtnPicture.setImageBitmap(bitmap);
            dialogCamera.dismiss();
        }
    }
    // 카메라 갤러리 선택 dialog
    public void showDialogCamera() {
        dialogCamera.show();
        ImageButton btnCamera = dialogCamera.findViewById(R.id.btnCamera);
        ImageButton btnGallery = dialogCamera.findViewById(R.id.btnGallery);

        // 카메라 버튼 이벤트
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentC = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intentC.resolveActivity(getPackageManager()) != null) {
                    File imageFile = null;
                    try{
                        imageFile = createImageFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(imageFile != null) {
                        Uri imageUri = FileProvider.getUriForFile(getApplicationContext(),
                                "com.cookandroid.swu.fileprovider", imageFile);
                        intentC.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        startActivityForResult(intentC, CAMERA);
                    }
                }
            }
        });
        // 갤러리 버튼 이벤트
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentC = new Intent(Intent.ACTION_PICK);
                intentC.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intentC.setType("image/*");
                startActivityForResult(intentC, GALLERY);
            }
        });
    }



    /* 컨텍스트 메뉴 */
    // 시간 개수 선택 컨텍스트 메뉴
    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        if (v == plistBtnTime) {
            menuInflater.inflate(R.menu.plist_time, menu);
        }
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemPlist1:
                // 버튼 6개를 xml에 선언하고 setvisible 바꾸는 거
                plistBtnTime.setText("1");
                plListCount = 1;
                return true;
            case R.id.itemPlist2:
                plistBtnTime.setText("2");
                plListCount = 2;
                return true;
            case R.id.itemPlist3:
                plistBtnTime.setText("3");
                plListCount = 3;
                return true;
            case R.id.itemPlist4:
                plistBtnTime.setText("4");
                plListCount = 4;
                return true;
            case R.id.itemPlist5:
                plistBtnTime.setText("5");
                plListCount = 5;
                return true;
            case R.id.itemPlist6:
                plistBtnTime.setText("6");
                plListCount = 6;
                return true;
        }
        return false;
    }



    /* 리스트뷰 */
    // 리스트뷰 아이템
    public boolean loadItems(ArrayList<ListViewItemPlistAlertTime> list) {
        ListViewItemPlistAlertTime item;
        int j = 1;

        if (list == null) {
            list = new ArrayList<ListViewItemPlistAlertTime>();
        }

        // 아이템 생성 : listCount 개수만큼
        for(j=1;j<=plListCount;j++){
            final int k = j;
            item = new ListViewItemPlistAlertTime();
            item.setTitle("시간");
            item.setTitleNum(Integer.toString(j));
            item.setTime(time[k-1]);
            list.add(item);
        }

        return true;
    }
    // 리스트뷰 버튼 클릭하면 시간 선택
    @Override
    public void onListBtnClick(int position) {
        // timePickerdialog로 띄우기
        // android.R.style.Theme_Holo_Light_Dialog : spinner 모드
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(PlistActivity.this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        // time 배열의 첫 번째 값이 비어있지 않으면 모두 초기화
                        String amPm = "";
                        String minTen = "";

                        Calendar dateTime = Calendar.getInstance();
                        dateTime.set(Calendar.HOUR_OF_DAY, hour);
                        dateTime.set(Calendar.MINUTE, min);

                        if(dateTime.get(Calendar.AM_PM) == Calendar.AM) amPm = "AM";
                        else if (dateTime.get(Calendar.AM_PM) == Calendar.PM) amPm = "PM";

                        String strHrsToShow = (dateTime.get(Calendar.HOUR) == 0) ? "12" : dateTime.get(Calendar.HOUR)+"";

                        // min이 10이하면 0을 붙여서 저장
                        if (min < 10) minTen = "0" + Integer.toString(min);
                        else minTen = Integer.toString(min);

                        // listview 속 시간버튼 포지션마다 time 변수로 저장
                        switch (position) {
                            case 0:
                                time[0] = strHrsToShow+" : "+minTen+" "+amPm; break;
                            case 1:
                                time[1] = strHrsToShow+" : "+minTen+" "+amPm; break;
                            case 2:
                                time[2] = strHrsToShow+" : "+minTen+" "+amPm; break;
                            case 3:
                                time[3] = strHrsToShow+" : "+minTen+" "+amPm; break;
                            case 4:
                                time[4] = strHrsToShow+" : "+minTen+" "+amPm; break;
                            case 5:
                                time[5] = strHrsToShow+" : "+minTen+" "+amPm; break;
                        }
                    }
                }, alarmHour, alarmMinute, false);

        timePickerDialog.show();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plist);
        setTitle("복용약리스트 추가");

        // findViewById
        fvbi();

        // 카메라 권한체크
        // 안드로이드 버전이 마시멜로우 버전 이상이어야 가능
        boolean hasCamPerm = checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean hasWritePerm = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        // 권한 없을 시 권한 설정 요청
        if(!hasCamPerm || !hasWritePerm)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);


        // 카메라
        plistBtnPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogCamera = new Dialog(PlistActivity.this);
                // 타이틀 제거
                dialogCamera.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogCamera.setContentView(R.layout.dialogcamera);
                showDialogCamera();
            }
        });

        // 요일 저장
        for(int i=0;i<btnDay.length;i++){
            btnDay[i] = findViewById(btnDayIDs[i]);
        }
        for(int i=0;i<btnDay.length;i++){
            final int index = i;
            int[] count = new int[btnDay.length];
            btnDay[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // count 개수를 세서 한 번 클릭하면 pressed 상태, 두 번 클릭하면 not pressed 상태로 변경
                    count[index] += 1;
                    if(count[index] % 2 == 1) {
                        btnDay[index].setSelected(true);
                        plDay += btnDay[index].getText().toString();
                        System.out.println(plDay);
                    }
                    else if(count[index] % 2 == 0) {
                        btnDay[index].setSelected(false);
                        count[index] = 0;
                        plDay = plDay.replace(btnDay[index].getText().toString(), "");
                        System.out.println(plDay);
                    }
                }
            });
        }

        // 시간 개수 선택 - context menu 클릭만으로 열기
        plistBtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerForContextMenu(view);
                openContextMenu(view);
            }
        });


        // 시간 리스트뷰
        ArrayList<ListViewItemPlistAlertTime> items = new ArrayList<ListViewItemPlistAlertTime>();
        // items 로드
        loadItems(items);
        // adapter 생성
        adapter = new AdapterPlistAlertTime(this, R.layout.listview_plistalerttime, items, this);
        // adapter 달기
        plistListTime.setAdapter(adapter);
        // listView 버튼 클릭 이벤트 핸들러 지정
        plistListTime.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PlistActivity.this);
                dialog.setTitle("삭제");
                dialog.setMessage("삭제하시겠습니까?");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int count, checked;
                        count = adapter.getCount();

                        if(count > 0) {
                            // 현재 선택된 아이템 position 획득
                            checked = plistListTime.getCheckedItemPosition();
                            if(checked > -1 && checked < count) {
                                // 아이템 삭제
                                items.remove(checked);
                                plistListTime.clearChoices();
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();
                // click Listener도 달려면 return false
                return true;
            }
        });
        // 확인 버튼 누르면 리스트뷰에 수 변경 반영
        plistBtnTimeOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.clear();
                loadItems(items);
                adapter.notifyDataSetChanged();
            }
        });


        // 저장 버튼 - 데이터 주기
        plistBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 약 이름, 메모 저장
                plName = plistEdtName.getText().toString();
                plMemo = plistEdtRealMemo.getText().toString();
                Bitmap plBitmap = null;
                if(bitmap == null) plBitmap = bitmap;
                else plBitmap = bitmap;
                for(int i = 0;i < 6; i++){
                    plTime[i] = time[i];
                }

                PlistFragment plistFragment = (PlistFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                plistFragment.addItem(plBitmap, plName, plTime[0], plTime[1], plTime[2], plTime[3], plTime[4], plTime[5]);

                // 11/09
                // fragment에 listview 추가 부분은 대충 기술구현을 했지만
                // bitmap, timebutton 등이 초기화가 되지 않고
                // 이전에 쓰인 값이 그대로 쓰여지는 문제가 발생
                // staic으로 구현한 것이 문제가 되는 것이라 예상
                // add item을 할 때마다 초기화를 해주거나 다른 변수를 선언해야 할 듯

                finish();
            }
        });
    }

    // findViewById
    void fvbi() {
        plistBtnPicture = findViewById(R.id.plistBtnPicture);
        plistEdtName = findViewById(R.id.plistEdtName);
        plistEdtRealMemo = findViewById(R.id.plistEdtRealMemo);
        plistBtnTime = findViewById(R.id.plistBtnTime);
        plistBtnSave = findViewById(R.id.plistBtnSave);
        plistListTime = findViewById(R.id.plistListTime);
        plistBtnTimeOk = findViewById(R.id.plistBtnTimeOk);
    }


}
