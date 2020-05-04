package com.example.experiment7_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.experiment7_1.Bean.ClassList;
import com.example.experiment7_1.Bean.Curriculum;
import com.example.experiment7_1.Factory.ServiceFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Iterator;
import java.util.Map;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity" ;
    private static int gridHeight,gridWidth;
    private static int infoHeight,infoWidth;
    private RelativeLayout layout;
    private ConstraintLayout infoLayout;
    private ConstraintLayout MainLayout;
    private RelativeLayout tmpLayout;
    private Button infoButton;
    private Spinner spinner;
    private FloatingActionButton FloatingActionButton;

    //private static boolean isFirst = true;
    private ClassList classInfo;

    public static final int PASS_0 = 0;
    public static final int PASS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tmpLayout = (RelativeLayout) findViewById(R.id.Monday);
        spinner=findViewById(R.id.spinner1);
        FloatingActionButton=findViewById(R.id.floatingActionButton);
        //点击下拉框事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int week=position+1;
                String text= spinner.getItemAtPosition(position).toString();
                //Toast.makeText(MainActivity.this,text,Toast.LENGTH_SHORT).show();
                //删除旧组件
                delView();
                Map <Integer,ClassList> classList=ServiceFactory.Service(getApplicationContext()).selectClassList();
                for(Map.Entry<Integer,ClassList> entry : classList.entrySet()){
                    Integer mapKey = entry.getKey();
                    ClassList mapValue = entry.getValue();
                    if(week>=mapValue.getWeekSumStart()&&week<=mapValue.getWeekSumEnd()){
                        classInfo=mapValue;
                        addView(mapValue.getCLid(),week,mapValue.getWeekday(),mapValue.getNodeStart(),mapValue.getNodeEnd(),mapValue.getClassCame()+"\n @ "+mapValue.getClassRoom());
                    }

                    //Log.d(TAG, "onCreate: Map ClassList:"+mapValue.toString());
                }
                /*List <Curriculum> Curriculum=ServiceFactory.Service(getApplicationContext()).selectCurriculum();
                Iterator<Curriculum> iterator1 = Curriculum.iterator();
                while(iterator1.hasNext()){
                    Curriculum cl=iterator1.next();
                    addView(1,1,2,text);
                    Log.d(TAG, "onCreate: ClassList:"+cl.toString());
                }
                */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //增加课程事件
        FloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddCourseActivity.class);
                //intent.putExtra("test1", test1);

                startActivityForResult(intent, PASS_0);
            }
        });

    }



    //屏幕加载完执行
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
       // Log.d(TAG, "onWindowFocusChanged: 调用");
       // if(isFirst) {
           // Log.d(TAG, "onWindowFocusChanged: ");
           // isFirst = false;
            gridWidth = tmpLayout.getWidth();
            gridHeight = tmpLayout.getHeight()/11;
            //Log.d(TAG, "onWindowFocusChanged: tmpLayout.getWidth "+gridWidth);
       // }

    }
    private TextView createTv(int start,int end,String text){
        TextView tv = new TextView(this);
        /*
         指定高度和宽度
         */
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridWidth,gridHeight*(end-start+1));
        /*
        指定位置
         */
        //Log.d(TAG, "createTv: text"+text);
        tv.setY(gridHeight*(start-1));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);
        return tv;
    }
    //添加组件
    private void addView(int className,int week,int i,int start,int end,String text){
        TextView tv;
       // Log.d(TAG, "addView: text"+text);
        switch (i){
            case 1:
                layout = (RelativeLayout) findViewById(R.id.Monday);
                break;
            case 2:
                layout = (RelativeLayout) findViewById(R.id.Tuesday);
                break;
            case 3:
                layout = (RelativeLayout) findViewById(R.id.Wednesday);
                break;
            case 4:
                layout = (RelativeLayout) findViewById(R.id.Thursday);
                break;
            case 5:
                layout = (RelativeLayout) findViewById(R.id.Friday);
                break;
            case 6:
                layout = (RelativeLayout) findViewById(R.id.Saturday);
                break;
            case 7:
                layout = (RelativeLayout) findViewById(R.id.Sunday);
                break;
            default:
                break;
        }
        tv= createTv(start,end,text);
        int ran=(week+className);
        while(true){
            if(ran<=9){
                break;
            }
            ran=ran%9;
        }
        tv.setBackground(colorSelect(ran));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setLineSpacing(1, (float) 1.5);
        tv.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: ");
                informationView(classInfo);
            }
        });
        //tv.setBackgroundColor(Color.argb(50,start*10,(start+end)*20,end*5));

        layout.addView(tv);
    }
    //删除组件
    private void delView(){
     for(int i=1;i<=7;i++){
         switch (i){
             case 1:
                 layout = (RelativeLayout) findViewById(R.id.Monday);
                 break;
             case 2:
                 layout = (RelativeLayout) findViewById(R.id.Tuesday);
                 break;
             case 3:
                 layout = (RelativeLayout) findViewById(R.id.Wednesday);
                 break;
             case 4:
                 layout = (RelativeLayout) findViewById(R.id.Thursday);
                 break;
             case 5:
                 layout = (RelativeLayout) findViewById(R.id.Friday);
                 break;
             case 6:
                 layout = (RelativeLayout) findViewById(R.id.Saturday);
                 break;
             case 7:
                 layout = (RelativeLayout) findViewById(R.id.Sunday);
                 break;
         }
         View view = null;
         int index = layout.getChildCount();
         for(;index > 0;index --){
             view = layout.getChildAt(index);
             layout.removeView(view);
         }

        /* int count = layout.getChildCount();

        if(count>1){
            layout.removeViews(0,count-1);
        }*/
     }

    }

    //颜色选择
    private Drawable colorSelect(int i){
        Drawable drawable=null;
        switch (i){
            case 1:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback);
                break;
            case 2:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback1);
                break;
            case 3:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback2);
                break;
            case 4:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback3);
                break;
            case 5:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback4);
                break;
            case 6:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback5);
                break;
            case 7:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback6);
                break;
            case 8:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback7);
                break;
            case 9:
                drawable= ContextCompat.getDrawable(this, R.drawable.textviewback8);
                break;
        }
        return drawable;
    }

    private void informationView(ClassList classList){
        //隐藏按钮
        FloatingActionButton.hide();
        MainLayout=findViewById(R.id.ConstraintLayout1);
        infoHeight= (int) (MainLayout.getHeight());
        infoWidth= (int) (MainLayout.getWidth());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(infoWidth,infoHeight);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        infoLayout=(ConstraintLayout) inflater.inflate(R.layout.layout_course_information, null).findViewById(R.id.ConstraintLayout2);
        infoLayout.setLayoutParams(params);
        MainLayout.addView(infoLayout);

        TextView textView2=findViewById(R.id.textView2);
        TextView textView4=findViewById(R.id.textView4);
        TextView textView6=findViewById(R.id.textView6);
        TextView textView8=findViewById(R.id.textView8);
        TextView textView10=findViewById(R.id.textView10);

        textView2.setText(classList.getClassCame());
        textView4.setText(classList.getClassRoom());
        textView6.setText(classList.getWeekSumStart()+"-"+classList.getWeekSumEnd()+"周");
        textView8.setText(classList.getNodeStart()+"-"+classList.getNodeEnd()+"节");
        textView10.setText(classList.getDayTime());
        infoButton=findViewById(R.id.button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainLayout.removeView(infoLayout);
                //显示按钮
                FloatingActionButton.show();
            }
        });
    }


    //回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        Log.d(TAG, "onActivityResult: 返回");
        if(requestCode==PASS_0 && resultCode==RESULT_OK){

            Log.d(TAG, "onActivityResult: PASS_0："+PASS_0);
            delView();
            int week=1;
            Map <Integer,ClassList> classList=ServiceFactory.Service(getApplicationContext()).selectClassList();
            for(Map.Entry<Integer,ClassList> entry : classList.entrySet()){
                Integer mapKey = entry.getKey();
                ClassList mapValue = entry.getValue();
                if(week>=mapValue.getWeekSumStart()&&week<=mapValue.getWeekSumEnd()){
                    classInfo=mapValue;
                    addView(mapValue.getCLid(),week,mapValue.getWeekday(),mapValue.getNodeStart(),mapValue.getNodeEnd(),mapValue.getClassCame()+"\n @ "+mapValue.getClassRoom());
                }

                //Log.d(TAG, "onCreate: Map ClassList:"+mapValue.toString());
            }

        }

        if(requestCode==PASS_1 && resultCode==RESULT_OK){

        }
    }
}