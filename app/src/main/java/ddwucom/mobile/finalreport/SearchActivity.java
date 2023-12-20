package ddwucom.mobile.finalreport;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<Data> dataList = null;
    ListView listView;
    CustomAdapter adapter;
    DBManager dbManager;

    EditText etName;
    EditText etLocation;
    EditText etParking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dataList = new ArrayList();
        listView = findViewById(R.id.listView);
        adapter = new CustomAdapter(this, R.layout.custom_adapter_view, dataList);
        listView.setAdapter(adapter);
        dbManager = new DBManager(this);

        etName = findViewById(R.id.etName2);
        etLocation = findViewById(R.id.etLocation2);
        etParking = findViewById(R.id.etParking2);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_search_name:
                dataList.clear();
                dataList.addAll(dbManager.getDataByName(etName.getText().toString()));
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_search_location:
                dataList.clear();
                dataList.addAll(dbManager.getDataByLocation(etLocation.getText().toString()));
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_search_parking:
                dataList.clear();
                dataList.addAll(dbManager.getDataByParking(etParking.getText().toString()));
                adapter.notifyDataSetChanged();
                break;
        }
    }

}

