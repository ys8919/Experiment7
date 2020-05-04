package com.example.experiment7_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.experiment7_1.Bean.ClassList;
import com.example.experiment7_1.Bean.Curriculum;
import com.example.experiment7_1.Bean.Node_Time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tab_service {
    private static final String TAG = "tab_service";
    private SqlLite sqllite = null;
    private static SQLiteDatabase db = null;
    private static String sql = null;
    public tab_service(Context context) {
        this.sqllite = new SqlLite(context);//获得数据库操作实例
    }


    public Map<Integer,ClassList> selectClassList(){
        HashMap<Integer,ClassList> ClassList=new HashMap<Integer,ClassList>();
        db=sqllite.getReadableDatabase();
        sql="select * from ClassList ";
        Cursor cur = db.rawQuery(sql, null);
        while (cur.moveToNext()) {
            int id = cur.getInt(cur.getColumnIndex("id"));
            int CLid = cur.getInt(cur.getColumnIndex("CLid"));
            String classname = cur.getString(cur.getColumnIndex("classname"));
            int weekSumStart =  cur.getInt(cur.getColumnIndex("weeksumStart"));
            int weekSumEnd =  cur.getInt(cur.getColumnIndex("weeksumEnd"));
            String classroom = cur.getString(cur.getColumnIndex("classroom"));
            String DayTime = cur.getString(cur.getColumnIndex("DayTime"));
            int NodeStart =  cur.getInt(cur.getColumnIndex("NodeStart"));
            int NodeEnd =  cur.getInt(cur.getColumnIndex("NodeEnd"));
            int weekday = cur.getInt(cur.getColumnIndex("weekday"));
            ClassList stu = new ClassList(id,CLid,classname,weekSumStart,weekSumEnd,classroom,DayTime,NodeStart,NodeEnd,weekday);
            ClassList.put(id,stu);
        }
        cur.close();
        db.close();
        return ClassList;
    }
    public List<ClassList> selectClassID(String clID){
        List<ClassList> classList=new ArrayList<ClassList>();
        db=sqllite.getReadableDatabase();
        sql="select * from ClassList where CLid= ? ";
        Cursor cur = db.rawQuery(sql, new String[]{clID});
        while (cur.moveToNext()) {
            int id = cur.getInt(cur.getColumnIndex("id"));
            int CLid = cur.getInt(cur.getColumnIndex("CLid"));
            String classname = cur.getString(cur.getColumnIndex("classname"));
            int weekSumStart =  cur.getInt(cur.getColumnIndex("weeksumStart"));
            int weekSumEnd =  cur.getInt(cur.getColumnIndex("weeksumEnd"));
            String classroom = cur.getString(cur.getColumnIndex("classroom"));
            String DayTime = cur.getString(cur.getColumnIndex("DayTime"));
            int NodeStart =  cur.getInt(cur.getColumnIndex("NodeStart"));
            int NodeEnd =  cur.getInt(cur.getColumnIndex("NodeEnd"));
            int weekday = cur.getInt(cur.getColumnIndex("weekday"));
            ClassList stu = new ClassList(id,CLid,classname,weekSumStart,weekSumEnd,classroom,DayTime,NodeStart,NodeEnd,weekday);
            classList.add(stu);
        }
        cur.close();
        db.close();
        return classList;
    }
    public Map<Integer,ClassList> selectClassCid(){
        HashMap<Integer,ClassList> ClassList=new HashMap<Integer,ClassList>();
        db=sqllite.getReadableDatabase();
        sql="select * from ClassList ";
        Cursor cur = db.rawQuery(sql, null);
        while (cur.moveToNext()) {
            int id = cur.getInt(cur.getColumnIndex("id"));
            int CLid = cur.getInt(cur.getColumnIndex("CLid"));
            String classname = cur.getString(cur.getColumnIndex("classname"));
            int weekSumStart =  cur.getInt(cur.getColumnIndex("weeksumStart"));
            int weekSumEnd =  cur.getInt(cur.getColumnIndex("weeksumEnd"));
            String classroom = cur.getString(cur.getColumnIndex("classroom"));
            String DayTime = cur.getString(cur.getColumnIndex("DayTime"));
            int NodeStart =  cur.getInt(cur.getColumnIndex("NodeStart"));
            int NodeEnd =  cur.getInt(cur.getColumnIndex("NodeEnd"));
            int weekday = cur.getInt(cur.getColumnIndex("weekday"));
            ClassList stu = new ClassList(id,CLid,classname,weekSumStart,weekSumEnd,classroom,DayTime,NodeStart,NodeEnd,weekday);
            ClassList.put(CLid,stu);
        }
        cur.close();
        db.close();
        return ClassList;
    }
    public void insertClass(ClassList cl){

        db=sqllite.getReadableDatabase();
        Log.d(TAG, "insertClass: cl"+cl);
        sql="insert into  ClassList values (NULL,"
                +cl.getCLid()+","
                +"'"+cl.getClassCame()+"'"+","
                +cl.getWeekSumStart()+","
                +cl.getWeekSumEnd()+","
                +"'"+cl.getClassRoom()+"'"+","
                +"'"+cl.getDayTime()+"'"+","
                +cl.getNodeStart()+","
                +cl.getNodeEnd()+","
                +cl.getWeekday()+");";
        Log.d(TAG, "insertClass:sql :"+sql);
        db.execSQL(sql);


        db.close();
    }

    public void updateCurriculum(Curriculum cl){

        db=sqllite.getReadableDatabase();
        Log.d(TAG, "insertCurriculum: cl"+cl);
        sql= "update Curriculum " +
                "set WeekTime1="+cl.getWeekTime1()+", WeekTime2="+cl.getWeekTime2()+",WeekTime3="+cl.getWeekTime3()+"" +
                ",WeekTime4="+cl.getWeekTime4()+",WeekTime5="+cl.getWeekTime5()+",WeekTime6="+cl.getWeekTime6()+",WeekTime7="+cl.getWeekTime7()+
                " where DTid="+cl.getDTid()+";";

        Log.d(TAG, "insertCurriculum:sql :"+sql);
        db.execSQL(sql);


        db.close();
    }
    public List<Curriculum> selectCurriculumList(){
        List<Curriculum> Curriculum=new ArrayList<Curriculum>();
        db=sqllite.getReadableDatabase();
        sql="select * from Curriculum ";
        Cursor cur = db.rawQuery(sql, new String[]{});
        while (cur.moveToNext()) {
            int DTid = cur.getInt(cur.getColumnIndex("DTid"));
            int WeekTime1 = cur.getInt(cur.getColumnIndex("WeekTime1"));
            int WeekTime2 = cur.getInt(cur.getColumnIndex("WeekTime2"));
            int WeekTime3 = cur.getInt(cur.getColumnIndex("WeekTime3"));
            int WeekTime4 = cur.getInt(cur.getColumnIndex("WeekTime4"));
            int WeekTime5 = cur.getInt(cur.getColumnIndex("WeekTime5"));
            int WeekTime6 = cur.getInt(cur.getColumnIndex("WeekTime6"));
            int WeekTime7 = cur.getInt(cur.getColumnIndex("WeekTime7"));

            Curriculum stu = new Curriculum(DTid,WeekTime1,WeekTime2,WeekTime3,WeekTime4,WeekTime5,WeekTime6,WeekTime7);
            Curriculum.add(stu);
        }
        cur.close();
        db.close();
        return Curriculum;
    }
    public Curriculum selectCurriculum(ClassList cl){
        Curriculum stu = null;
        db=sqllite.getReadableDatabase();
        sql="select * from Curriculum where DTID= ?";
        Cursor cur = db.rawQuery(sql, new String[]{Integer.toString(cl.getWeekday())});
        while (cur.moveToNext()) {
            int DTid = cur.getInt(cur.getColumnIndex("DTid"));
            int WeekTime1 = cur.getInt(cur.getColumnIndex("WeekTime1"));
            int WeekTime2 = cur.getInt(cur.getColumnIndex("WeekTime2"));
            int WeekTime3 = cur.getInt(cur.getColumnIndex("WeekTime3"));
            int WeekTime4 = cur.getInt(cur.getColumnIndex("WeekTime4"));
            int WeekTime5 = cur.getInt(cur.getColumnIndex("WeekTime5"));
            int WeekTime6 = cur.getInt(cur.getColumnIndex("WeekTime6"));
            int WeekTime7 = cur.getInt(cur.getColumnIndex("WeekTime7"));

            stu = new Curriculum(DTid,WeekTime1,WeekTime2,WeekTime3,WeekTime4,WeekTime5,WeekTime6,WeekTime7);
        }
        cur.close();
        db.close();
        return stu;
    }
    public  Map<Integer,Node_Time> selectNode_Time(){
        HashMap<Integer,Node_Time> node_timeList=new HashMap<Integer,Node_Time>();
        db=sqllite.getReadableDatabase();
        sql="select * from Node_Time ";
        Cursor cur = db.rawQuery(sql, new String[]{});
        while (cur.moveToNext()) {
            int Nid = cur.getInt(cur.getColumnIndex("Nid"));
            int Node = cur.getInt(cur.getColumnIndex("Node"));
            String  StartTime= cur.getString(cur.getColumnIndex("StartTime"));
            String  EndTime= cur.getString(cur.getColumnIndex("EndTime"));

            Node_Time stu = new Node_Time(Nid,Node,StartTime,EndTime);
            node_timeList.put(Node,stu);
        }
        cur.close();
        db.close();
        return node_timeList;
    }
}
