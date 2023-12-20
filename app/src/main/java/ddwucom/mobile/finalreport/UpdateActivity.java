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

public class UpdateActivity extends AppCompatActivity {
    Data data;
    ImageView imgView3;
    EditText etName;
    EditText etLocation;
    EditText etTel;
    EditText etParking;
    DBManager dbManager;

    TextView textView_Date2;
    DatePickerDialog.OnDateSetListener callbackMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        data = (Data) getIntent().getSerializableExtra("data");

        imgView3 = findViewById(R.id.imgView3);
        etName = findViewById(R.id.et_name);
        etLocation = findViewById(R.id.et_location);
        etTel = findViewById(R.id.et_tel);
        etParking = findViewById(R.id.et_parking);
        textView_Date2 = findViewById(R.id.textView_date2);

        imgView3.setImageResource(data.getImage());
        etName.setText(data.getName());
        etLocation.setText(data.getLocation());
        etTel.setText(data.getTel());
        etParking.setText(data.getParking());
        textView_Date2.setText(data.getDayOff());

        dbManager = new DBManager(this);

        this.InitializeView();
        this.InitializeListener();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                String name = etName.getText().toString();
                String location = etLocation.getText().toString();
                String tel = etTel.getText().toString();
                String parking = etParking.getText().toString();
                String dayOff = textView_Date2.getText().toString();

                // 필수 항목 미입력시 안내 토스트 출력
                if (name.length() == 0 || location.length() == 0 || tel.length() == 0 || parking.length() == 0 || dayOff.length() == 0) {
                    Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else { // 항목 모두 입력시
                    data.setName(name);
                    data.setLocation(location);
                    data.setTel(tel);
                    data.setParking(parking);
                    data.setDayOff(dayOff);
                    boolean result = dbManager.modifyData(data);
                    if (result) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("data", data);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                }
                break;
            case R.id.btn_cancel2:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

    public void InitializeView()
    {
        textView_Date2 = (TextView)findViewById(R.id.textView_date2);
    }

    public void InitializeListener()
    {
        callbackMethod = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                textView_Date2.setText(year + "년 " + (monthOfYear+1) + "월 " + dayOfMonth + "일");
            }
        };
    }

    public void OnClickHandler(View view)
    {
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod, 2022, 5, 24);

        dialog.show();
    }


}
