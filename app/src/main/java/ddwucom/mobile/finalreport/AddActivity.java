package ddwucom.mobile.finalreport;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {
    ImageView imgView2;
    EditText etName;
    EditText etLocation;
    EditText etTel;
    EditText etParking;
    DBHelper dbHelper;
    DBManager dbManager;

    TextView textView_Date;
    //String tvDate;
    DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        imgView2 = findViewById(R.id.imgView2);
        etName = findViewById(R.id.et_name);
        etLocation = findViewById(R.id.et_location);
        etTel = findViewById(R.id.et_tel);
        etParking = findViewById(R.id.et_parking);

        dbHelper = new DBHelper(this);
        dbManager = new DBManager(this);

        this.InitializeView();
        this.InitializeListener();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String tel = etTel.getText().toString();
                String parking = etParking.getText().toString();
                String dayOff = textView_Date.getText().toString();
                // 필수 항목 미입력시 안내 토스트 출력
                if (name.length() == 0 || location.length() == 0 || tel.length() == 0 || parking.length() == 0 || dayOff.length() == 0) {
                    Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = dbManager.addData(new Data(R.mipmap.ic_launcher, name, location, tel, parking, dayOff));
                    if (result) {    // 정상수행에 따른 처리
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("placeName", etName.getText().toString());
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {        // 이상에 따른 처리
                        Toast.makeText(this, "새로운 장소 추가 실패!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn_cancel2:   // 취소에 따른 처리
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    public void InitializeView()
    {
        textView_Date = (TextView)findViewById(R.id.textView_date);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2022, 5, 24);

        dialog.show();
    }


}
