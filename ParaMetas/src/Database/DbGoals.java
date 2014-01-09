package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbGoals extends SQLiteOpenHelper{

	public static final String DATABASE_NAME = "parametas";
	public static final String TABLE_NAME = "goals";
	
	public static final String C_ID = "_id";
	public static final String TITLE = "title";
	public static final String DESCRIPTION = "description";
	public static final String MILESTONE = "milestone";
	public static final String START_TIME = "start_time";
	public static final String END_TIME = "end_time";
	public static final String UNIT = "unit";
	public static final String COLOR = "color";
	public static final String REACHED = "reached";

	
	public static final int VERSION = 1;
	
	public static final String[] allColumns = {DbGoals.C_ID, DbGoals.TITLE, DbGoals.DESCRIPTION, DbGoals.MILESTONE, DbGoals.START_TIME, DbGoals.END_TIME, DbGoals.UNIT, DbGoals.COLOR, DbGoals.REACHED};
	
	
	private final String createTableGoals = "CREATE TABLE if not exists "+ TABLE_NAME + " ( "
			+ C_ID + "  integer primary key autoincrement, "
			+ TITLE + " text, "
			+ DESCRIPTION + " text, "
			+ MILESTONE + " text, "
			+ START_TIME + " text, "
			+ END_TIME + " text, "
			+ UNIT + " text, "
			+ COLOR + " text, "
			+ REACHED + " text) ";
	
	public DbGoals(Context context) {
		super(context, DATABASE_NAME, null, VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createTableGoals);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP DATABASE "+ DATABASE_NAME);
//		onCreate(db);
	}

}
