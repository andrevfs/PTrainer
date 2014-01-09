package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbTraining extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "parametas2";
	
	public static final String TABLE_NAME = "training";
	
	public static final String C_ID = "_id";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final int VERSION = 1;
	
	public static final String[] allColumns = {DbTraining.C_ID,DbTraining.TITLE,DbTraining.DESCRIPTION};
	
	private final String createTableTraining = "CREATE TABLE if not exists "+ TABLE_NAME + " ( "
			+ C_ID + "  integer primary key autoincrement, "
			+ TITLE + " text, "
			+ DESCRIPTION + " text) ";
	
	public DbTraining(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTableTraining);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP DATABASE "+ DATABASE_NAME);
//		onCreate(db);
	}
	
}