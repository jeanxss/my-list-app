// 과제명: 서울 명소 정보 앱
// 분반: 01분반
// 학번: 20181801 성명: 김효진
// 제출일: 2022년 6월 24일
package ddwucom.mobile.finalreport;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "MainActivity";
    final int ADD_CODE = 100;
    final int UPDATE_CODE = 200;

    ArrayList<Data> dataList = null;
    ListView listView;
    CustomAdapter adapter;
    DBHelper dbHelper;
    DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList = new ArrayList();
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, R.layout.custom_adapter_view, dataList);
        listView.setAdapter(adapter);
        dbManager = new DBManager(this);

        // 리스트뷰 항목 클릭시 수정 액티비티로 이동
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭한 항목을 수정 액티비티에 전달
                Data data = dataList.get(position);
                Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                intent.putExtra("data", data);
                startActivityForResult(intent, UPDATE_CODE);
            }
        });

        // 리스트뷰 항목 롱클릭시 삭제
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                final int pos = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("장소 삭제")
                        .setMessage(dataList.get(pos).getName() + " 장소를 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (dbManager.removeData(dataList.get(pos).get_id())) {
                                    Toast.makeText(MainActivity.this, "삭제 완료", Toast.LENGTH_SHORT).show();
                                    // dataList 갱신
                                    dataList.clear();
                                    dataList.addAll(dbManager.getAllData());
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(MainActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataList.clear();
        dataList.addAll(dbManager.getAllData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            // 장소 추가 메뉴 클릭시 추가 액티비티로 이동
            case R.id.item01:
                Intent intent = new Intent(this, AddActivity.class);
                startActivityForResult(intent, ADD_CODE);
                break;
            case R.id.item02:
                Intent intent2 = new Intent(this, SearchActivity.class);
                startActivity(intent2);
                break;
            case R.id.item03:
                Intent intent3 = new Intent(this, DeveloperActivity.class);
                startActivity(intent3);
                break;
            case R.id.item04:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("앱 종료")
                        .setMessage("앱을 종료하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.dialog_cancel, null)
                        .setCancelable(false)
                        .show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // AddActivity 호출 후 결과 확인
        if (requestCode == ADD_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String placeName = data.getStringExtra("placeName");
                    Toast.makeText(this, placeName + " 추가 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "장소 추가 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        } // UpdateActivity 호출 후 결과 확인
        else if (requestCode == UPDATE_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    String placeName = data.getStringExtra("data");
                    Toast.makeText(this, "장소 수정 완료", Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(this, "장소 수정 취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}