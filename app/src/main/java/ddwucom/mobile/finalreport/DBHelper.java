package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "DBHelper";
    final static String DB_NAME = "place.db";
    public final static String TABLE_NAME = "place_table";
    public final static String COL_ID = "_id";
    public final static String COL_IMAGE = "image";
    public final static String COL_NAME = "name";
    public final static String COL_LOCATION = "location";
    public final static String COL_TEL = "tel";
    public final static String COL_PARKING = "parking";
    public final static String COL_DAYOFF = "dayOff";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " integer primary key autoincrement, " + COL_IMAGE + " integer, " +
                COL_NAME + " TEXT, " + COL_LOCATION + " TEXT, " + COL_TEL + " TEXT, " + COL_PARKING + " TEXT, " + COL_DAYOFF + " TEXT)";
        Log.d(TAG, sql);
        db.execSQL(sql);
        // 샘플 추가
        insertSample(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    private void insertSample(SQLiteDatabase db) {
        ContentValues row = new ContentValues();

        //row.put(COL_ID, 1);
        row.put(COL_IMAGE, R.mipmap.goong);
        row.put(COL_NAME, "경복궁");
        row.put(COL_LOCATION, "종로구");
        row.put(COL_TEL, "02-3700-3900");
        row.put(COL_PARKING, "y");
        row.put(COL_DAYOFF, "월요일");
        db.insert(TABLE_NAME, null, row);

        //row.put(COL_ID, 2);
        row.put(COL_IMAGE, R.mipmap.tower);
        row.put(COL_NAME, "남산타워");
        row.put(COL_LOCATION, "용산구");
        row.put(COL_TEL, "02-3455-9277");
        row.put(COL_PARKING, "y");
        row.put(COL_DAYOFF, "화요일");
        db.insert(TABLE_NAME, null, row);

        //row.put(COL_ID, 3);
        row.put(COL_IMAGE, R.mipmap.hanok);
        row.put(COL_NAME, "북촌한옥마을");
        row.put(COL_LOCATION, "종로구");
        row.put(COL_TEL, "02-2133-1372");
        row.put(COL_PARKING, "n");
        row.put(COL_DAYOFF, "수요일");
        db.insert(TABLE_NAME, null, row);

        //row.put(COL_ID, 4);
        row.put(COL_IMAGE, R.mipmap.ddp);
        row.put(COL_NAME, "동대문디자인플라자");
        row.put(COL_LOCATION, "중구");
        row.put(COL_TEL, "02-2153-0000");
        row.put(COL_PARKING, "y");
        row.put(COL_DAYOFF, "목요일");
        db.insert(TABLE_NAME, null, row);

        //row.put(COL_ID, 5);
        row.put(COL_IMAGE, R.mipmap.lotte);
        row.put(COL_NAME, "롯데월드");
        row.put(COL_LOCATION, "송파구");
        row.put(COL_TEL, "02-1661-2000");
        row.put(COL_PARKING, "y");
        row.put(COL_DAYOFF, "금요일");
        db.insert(TABLE_NAME, null, row);
    }


}


