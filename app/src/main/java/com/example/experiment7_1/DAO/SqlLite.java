package com.example.experiment7_1.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class SqlLite extends SQLiteOpenHelper {
    private static final String db_name = "SQLite_db1.db";//数据文件的名字
    private static final String TAG ="SqlLite" ;
    private static int NUMBER = 1;//当前数据库版本，用于升级
    private static final String table_name = "";//表名
    private static String sql = null;//sql语句

    public SqlLite(@Nullable Context context) {
        super(context, db_name, null, NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        sql = "CREATE TABLE ClassList ( --课程名--\n" +
                "id  INTEGER primary key AUTOINCREMENT,"+
                "CLid   INTEGER    NOT NULL ,\n" +
                "classname  VARCHAR(50) NOT NULL ,---名字\n" +
                "weeksumStart  INTEGER NOT NULL ,-----周数开始\n" +
                "weeksumEnd  INTEGER NOT NULL ,-----周数结束\n" +
                "classroom VARCHAR(50) NOT NULL ,-----教室\n" +
                "DayTime VARCHAR(50) NOT NULL,------时间\n" +
                "NodeStart INTEGER NOT NULL,------节开始\n"+
                "NodeEnd INTEGER NOT NULL,------节结束\n"+
                "weekday INTEGER NOT NULL------星期几\n"+
                ");";
        db.execSQL(sql);

        sql =   "CREATE TABLE Curriculum ( \n" +
                "DTid   INTEGER    PRIMARY KEY ,\n" +
                "WeekTime1 INT,\n" +
                "WeekTime2 INT,\n" +
                "WeekTime3 INT,\n" +
                "WeekTime4 INT,\n" +
                "WeekTime5 INT,\n" +
                "WeekTime6 INT,\n" +
                "WeekTime7 INT\n" +
                ");";
        db.execSQL(sql);

        sql =   "CREATE TABLE Node_Time ( \n" +
                "Nid   INTEGER    primary key AUTOINCREMENT,\n" +
                "Node INTEGER,\n" +
                "StartTime VARCHAR(20),\n" +
                "EndTime VARCHAR(20)\n" +
                ");";
        db.execSQL(sql);

        sql =   "insert into Node_Time\n" +
                "values(NULL,1,'8:00','8:45'),\n"+
                "(NULL,2,'8:55','9:40'),\n"+
                "(NULL,3,'10:00','10:45'),\n"+
                "(NULL,4,'10:55','11:40'),\n"+
                "(NULL,5,'14:30','15:15'),\n"+
                "(NULL,6,'15:20','16:05'),\n"+
                "(NULL,7,'16:25','17:10'),\n"+
                "(NULL,8,'17:15','18:00'),\n"+
                "(NULL,9,'19:40','20:25'),\n"+
                "(NULL,10,'20:35','21:10'),\n"+
                "(NULL,11,'21:20','22:00')\n";
        db.execSQL(sql);

        sql =   "insert into ClassList\n" +
                "values(NULL,1,'专业英语',1,12,'学友楼107','8:00-9:40',1,2,2),\n" +
                "(NULL,1,'专业英语',1,12,'学友楼104','10:00-11:40',3,4,3),\n" +
                "(NULL,2,'计算机组成原理',1,13,'校友楼401','10:00-11:40',3,4,1),\n" +
                "(NULL,2,'计算机组成原理',1,16,'逸夫楼605','16:25-18:00',7,8,1),\n" +
                "(NULL,2,'计算机组成原理',1,13,'校友楼401','14:30-16:05',5,6,3),\n" +
                "(NULL,2,'计算机组成原理',1,16,'逸夫楼605','16:25-18:00',7,8,3),\n" +
                "(NULL,3,'程序设计进阶（C#）',1,16,'逸夫楼604','10:00-11:40',3,4,2),\n" +
                "(NULL,3,'程序设计进阶（C#）',1,16,'逸夫楼604','14:30-16:05',5,6,5),\n" +
                "(NULL,4,'移动软件开发',1,15,'逸夫楼604','14:30-16:05',5,6,1),\n" +
                "(NULL,4,'移动软件开发',1,15,'逸夫楼604','8:00-9:40',1,2,3),\n" +
                "(NULL,5,'UNIX/LINUX',1,12,'文综楼406','19:40-21:20',9,10,2),\n" +
                "(NULL,5,'UNIX/LINUX',1,12,'文综楼406','19:40-21:20',9,10,4),\n" +
                "(NULL,6,'软件设计模式',1,15,'逸夫楼604','8:00-9:40',1,2,4),\n" +
                "(NULL,6,'软件设计模式',1,15,'逸夫楼604','10:00-11:40',3,4,4),\n" +
                "(NULL,7,'计算机操作系统',1,15,'逸夫楼604','8:00-9:40',1,2,5),\n" +
                "(NULL,7,'计算机操作系统',1,15,'逸夫楼604','10:00-11:40',3,4,5);\n";
        db.execSQL(sql);

        sql =   "insert into Curriculum(DTid,WeekTime1,WeekTime2,WeekTime3,WeekTime4,WeekTime5,WeekTime6,WeekTime7)\n" +
                "values(1,0,1,10,13,15,0,0),\n" +
                "(2,0,1,10,13,15,0,0),\n" +
                "(3,3,7,2,14,16,0,0),\n" +
                "(4,3,7,2,14,16,0,0),\n" +
                "(5,9,0,5,0,8,0,0),\n" +
                "(6,9,0,5,0,8,0,0),\n" +
                "(7,4,0,6,0,0,0,0),\n" +
                "(8,4,0,6,0,0,0,0),\n" +
                "(9,0,11,0,12,0,0,0),\n" +
                "(10,0,11,0,12,0,0,0),\n" +
                "(11,0,0,0,0,0,0,0);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
