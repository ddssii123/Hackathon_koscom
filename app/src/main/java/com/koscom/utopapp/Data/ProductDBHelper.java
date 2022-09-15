package com.koscom.utopapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import com.koscom.utopapp.R;


public class ProductDBHelper extends SQLiteOpenHelper {

    private static ProductDBHelper dbHelper = null;

    private static final String DATABASE_NAME = "productdb";
    private static final String TABLE_NAME = "product";
    private static final int DB_VERSION = 1;

    public static final String COL_0 = "serialNumber";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "price";
    public static final String COL_4 = "image";
    public static final String COL_5 = "type";

    private Context mContext;

    public static ProductDBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new ProductDBHelper(context.getApplicationContext());
        }

        return dbHelper;
    }

    private ProductDBHelper(Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
        this.mContext = context;

        deleteAllProduct();
        initProduct();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_NAME + " ( "
                + COL_0 + " integer primary key autoincrement, "
                + COL_1 + " integer unique, "
                + COL_2 + " text not null, "
                + COL_3 + " integer, "
                + COL_4 + " blob, "
                + COL_5 + " text not null "
                + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public long insertProduct(ProductBean product){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_1, product.getId());
        values.put(COL_2, product.getName());
        values.put(COL_3, product.getPrice());
        values.put(COL_4, product.getImage());
        values.put(COL_5, product.getType());

        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<ProductBean> getAllProduct() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        ArrayList<ProductBean> result = new ArrayList<>();

        while (cursor.moveToNext()) {
            ProductBean product = new ProductBean();

            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));

            result.add(product);
        }

        return result;
    }

    public ArrayList<ProductBean> getRandomProduct(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME  + " order by id limit 4 ", null );
        ArrayList<ProductBean> result = new ArrayList<>();

        while (cursor.moveToNext()) {
            ProductBean product = new ProductBean();

            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));

            result.add(product);
        }

        return result;
    }

    public ArrayList<ProductBean> getProductbyType(String type){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, "type = ?", new String[] {type.toLowerCase()}, null, null, null);
        ArrayList<ProductBean> result = new ArrayList<>();

        while (cursor.moveToNext()) {
            ProductBean product = new ProductBean();

            product.setSerialNumber(cursor.getInt(cursor.getColumnIndex(COL_0)));
            product.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
            product.setName(cursor.getString(cursor.getColumnIndex(COL_2)));
            product.setPrice(cursor.getInt(cursor.getColumnIndex(COL_3)));
            product.setImage(cursor.getBlob(cursor.getColumnIndex(COL_4)));
            product.setType(cursor.getString(cursor.getColumnIndex(COL_5)));

            result.add(product);
        }

        return result;
    }

    public long deleteAllProduct(){
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME, null, null);
    }

    private void initProduct(){
        init("product", 1, "한복세트", 52000, getByteArrayFromDrawable(R.drawable.set1), "set");
        init("product", 2,"집구석세트", 40000, getByteArrayFromDrawable(R.drawable.set2), "set");
        init("product", 3, "오징어세트", 130000, getByteArrayFromDrawable(R.drawable.pop3), "set");
        init("product", 4, "콘서트세트", 69000, getByteArrayFromDrawable(R.drawable.pop4), "set");
        init("product", 5, "마술사 모자", 12900, getByteArrayFromDrawable(R.drawable.acc1), "acc");
        init("product", 6, "패딩", 25900, getByteArrayFromDrawable(R.drawable.top1), "top");
        init("product", 7, "선글라스", 15000, getByteArrayFromDrawable(R.drawable.acc2), "acc");
        init("product", 8, "체크탑", 28900, getByteArrayFromDrawable(R.drawable.top2), "top");
        init("product", 9, "패딩", 45900, getByteArrayFromDrawable(R.drawable.top3), "top");
        init("product", 10, "멋짐폭발선글라스", 38000, getByteArrayFromDrawable(R.drawable.acc3), "acc");
        init("product", 11, "뾰족선글라스", 152000, getByteArrayFromDrawable(R.drawable.acc4), "acc");
        init("product", 12, "모자", 6900, getByteArrayFromDrawable(R.drawable.acc6), "acc");
        init("product", 13, "FLEX! 목걸이", 2500, getByteArrayFromDrawable(R.drawable.acc5), "acc");
        init("product", 14, "주인공헤어", 25000, getByteArrayFromDrawable(R.drawable.hair1), "hair");
        init("product", 15, "무난한헤어", 30000, getByteArrayFromDrawable(R.drawable.hair2), "hair");
        init("product", 16, "부직포바지", 10000, getByteArrayFromDrawable(R.drawable.bottom1), "bottom");
        init("product", 17, "뉴진스", 15000, getByteArrayFromDrawable(R.drawable.bottom2), "bottom");

    }

    private void init(String tableName, int id, String pName, int pPrice, byte[] pImage, String type){
        ProductBean productBean = new ProductBean();

        productBean.setId(id);
        productBean.setName(pName);
        productBean.setPrice(pPrice);
        productBean.setImage(pImage);
        productBean.setType(type);

        insertProduct(productBean);
    }

    // drawable 이미지를 sqlite에 넣기 위해 byteArray로 변환하는 함수
    private byte[] getByteArrayFromDrawable(int image){
        Drawable drawable = mContext.getDrawable(image);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] dataByte = stream.toByteArray();

        return dataByte;
    }
}
