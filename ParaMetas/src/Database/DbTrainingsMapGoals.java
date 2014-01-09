package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbTrainingsMapGoals extends SQLiteOpenHelper{
	public static final String DATABASE_NAME = "parametas3";
	
	public static final String TABLE_NAME = "map_training_goals";
	
	public static final String C_ID = "_id";
	public static final String ID_GOAL = "goal";
	public static final String ID_TRAINING = "training";
	public static final int VERSION = 1;
	
	private final String createDb = "CREATE TABLE if not exists " + TABLE_NAME
			+ " ( " + C_ID + " integer primary key autoincrement, "
			+ ID_TRAINING + " integer, " 
			+ ID_GOAL + " integer) ";

	
	public DbTrainingsMapGoals(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createDb);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE "+ TABLE_NAME);
	}
}
