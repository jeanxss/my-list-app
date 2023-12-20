package ddwucom.mobile.finalreport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBManager {
    DBHelper dbHelper = null;
    Cursor cursor = null;
    ArrayList<Data> dataList;       // DataList 를 DBManager 가 직접 관리

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
        //dataList = new ArrayList<Data>();
    }

//      DBManager 가 관리하는 dataList 확인
//    public ArrayList<Data> getDataList() {
//        return dataList;
//    }

//    DB의 모든 data 를 반환
    public ArrayList<Data> getAllData() {
        dataList = new ArrayList();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_NAME, null);
        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_IMAGE));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME));
            String location = cursor.getString(cursor.getColumnIndex(DBHelper.COL_LOCATION));
            String tel = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TEL));
            String parking = cursor.getString(cursor.getColumnIndex(DBHelper.COL_PARKING));
            String dayOff = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAYOFF));
            dataList.add ( new Data(id, image, name, location, tel, parking, dayOff) );
        }
        cursor.close();
        dbHelper.close();

        return dataList;
    }

//    DB 에 새로운 food 추가
    public boolean addData(Data data) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues value = new ContentValues();
        //value.put(DBHelper.COL_ID, data.get_id());
        value.put(DBHelper.COL_IMAGE, data.getImage());
        value.put(DBHelper.COL_NAME, data.getName());
        value.put(DBHelper.COL_LOCATION, data.getLocation());
        value.put(DBHelper.COL_TEL, data.getTel());
        value.put(DBHelper.COL_PARKING, data.getParking());
        value.put(DBHelper.COL_DAYOFF, data.getDayOff());
//      insert 메소드를 사용할 경우 데이터 삽입이 정상적으로 이루어질 경우 1 이상, 이상이 있을 경우 0 반환 확인 가능
        long result = db.insert(DBHelper.TABLE_NAME, null, value);
        dbHelper.close();
        if (result > 0) return true;
        return false;
    }

//    _id 를 기준으로 data 의 정보 변경
    public boolean modifyData(Data data) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues row = new ContentValues();
        //row.put(DBHelper.COL_ID, data.get_id());
        //row.put(DBHelper.COL_IMAGE, data.getImage());
        row.put(DBHelper.COL_NAME, data.getName());
        row.put(DBHelper.COL_LOCATION, data.getLocation());
        row.put(DBHelper.COL_TEL, data.getTel());
        row.put(DBHelper.COL_PARKING, data.getParking());
        row.put(DBHelper.COL_DAYOFF, data.getDayOff());
        String whereClause = DBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(data.get_id()) };
        int result = sqLiteDatabase.update(DBHelper.TABLE_NAME, row, whereClause, whereArgs);
        dbHelper.close();
        if (result > 0) return true;
        return false;
    }

//    _id 를 기준으로 DB 에서 data 삭제
    public boolean removeData(long id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        String whereClause = DBHelper.COL_ID + "=?";
        String[] whereArgs = new String[] { String.valueOf(id) };
        int result = sqLiteDatabase.delete(DBHelper.TABLE_NAME, whereClause,whereArgs);
        dbHelper.close();
        if (result > 0) return true;
        return false;
    }

//    장소명으로 DB 검색
    public ArrayList<Data> getDataByName(String dataName) {
        dataList = new ArrayList();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String[] columns = {null};
        String selection = DBHelper.COL_NAME + "=?";
        String[] selectArgs = new String[] {dataName};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, selection, selectArgs,
                                    null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_IMAGE));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME));
            String location = cursor.getString(cursor.getColumnIndex(DBHelper.COL_LOCATION));
            String tel = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TEL));
            String parking = cursor.getString(cursor.getColumnIndex(DBHelper.COL_PARKING));
            String dayOff = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAYOFF));
            dataList.add ( new Data(id, image, name, location, tel, parking, dayOff) );
        }
        cursor.close();
        dbHelper.close();
        
        return dataList;
    }

//    위치로 DB 검색
    public ArrayList<Data> getDataByLocation(String dataLocation) {
        dataList = new ArrayList();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String[] columns = {null};
        String selection = DBHelper.COL_LOCATION + "=?";
        String[] selectArgs = new String[] {dataLocation};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, selection, selectArgs,
                null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_IMAGE));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME));
            String location = cursor.getString(cursor.getColumnIndex(DBHelper.COL_LOCATION));
            String tel = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TEL));
            String parking = cursor.getString(cursor.getColumnIndex(DBHelper.COL_PARKING));
            String dayOff = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAYOFF));
            dataList.add ( new Data(id, image, name, location, tel, parking, dayOff) );
        }
        cursor.close();
        dbHelper.close();

        return dataList;
    }
    
//    주차장 존재 여부로 DB 검색
    public ArrayList<Data> getDataByParking(String dataParking) {
        dataList = new ArrayList();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String[] columns = {null};
        String selection = DBHelper.COL_PARKING + "=?";
        String[] selectArgs = new String[] {dataParking};
        Cursor cursor = db.query(DBHelper.TABLE_NAME, null, selection, selectArgs,
                null, null, null, null);
        while(cursor.moveToNext()) {
            long id = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_ID));
            int image = cursor.getInt(cursor.getColumnIndex(DBHelper.COL_IMAGE));
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.COL_NAME));
            String location = cursor.getString(cursor.getColumnIndex(DBHelper.COL_LOCATION));
            String tel = cursor.getString(cursor.getColumnIndex(DBHelper.COL_TEL));
            String parking = cursor.getString(cursor.getColumnIndex(DBHelper.COL_PARKING));
            String dayOff = cursor.getString(cursor.getColumnIndex(DBHelper.COL_DAYOFF));
            dataList.add ( new Data(id, image, name, location, tel, parking, dayOff) );
        }
        cursor.close();
        dbHelper.close();

        return dataList;
    }


}
