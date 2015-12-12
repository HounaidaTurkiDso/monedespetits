package com.example.hounayda.lemondedespetits;


        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String KEY_CODE = "code";
    public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM= "prénom";


    public static final String KEY_AGE = "age";
    public static final String KEY_SEXE = "sexe";
    private static final String DATABASE_NAME = "Monde";
    private static final String SQLITE_TABLE = "Petits";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE if not exists " + SQLITE_TABLE + " (" +
                        KEY_CODE + " integer PRIMARY KEY autoincrement," +

                        KEY_NOM + "," +
                        KEY_PRENOM + "," +
                        KEY_AGE + "," +
                        KEY_SEXE + "," +

                        " UNIQUE (" + KEY_CODE + "));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+SQLITE_TABLE);
        onCreate(db);
    }

    public boolean insertData(String nom,String prénom,String age ,String sexe  ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NOM,nom);
        contentValues.put(KEY_PRENOM,prénom);
        contentValues.put(KEY_AGE,age);
        contentValues.put(KEY_SEXE, sexe);

        long result = db.insert(SQLITE_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ SQLITE_TABLE,null);
return  res ;

    }
}
