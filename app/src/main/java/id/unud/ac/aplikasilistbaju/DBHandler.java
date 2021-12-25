package id.unud.ac.aplikasilistbaju;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

public class DBHandler extends SQLiteOpenHelper {
    public static final String db_name = "db_baju";
    public static final String table_baju = "tb_baju";
    public static final int VER = 2;

    public static final String row_id_baju = "id_baju";
    public static final String row_brand = "brand";
    public static final String row_jenis = "jenis";
    public static final String row_ukuran = "ukuran";
    public static final String row_jumlah = "jumlah";

    private SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, db_name, null, VER);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_baju + "("
                + row_id_baju + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_brand + " TEXT,"
                + row_jenis + " TEXT,"
                + row_ukuran + " TEXT,"
                + row_jumlah + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_baju);
        onCreate(db);
    }

    public void insertDataBaju(ContentValues values) {
        db.insert(table_baju, null, values);
    }

    public void updateDataBaju(ContentValues values, long id) {
        db.update(table_baju, values, row_id_baju + "=" + id, null);
    }

    public void deleteDataBaju(long id) {
        db.delete(table_baju, row_id_baju + "=" + id, null);
    }

    public Cursor getAllDataBaju() {
        return db.query(table_baju, null, null, null, null, null, null);
    }

    public Cursor getDataBaju(long id) {
        return db.rawQuery("SELECT*FROM " + table_baju + " WHERE " + row_id_baju + "=" + id, null);
    }
}
