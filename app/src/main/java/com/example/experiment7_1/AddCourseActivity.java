package com.example.experiment7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.experiment7_1.Bean.ClassList;
import com.example.experiment7_1.Bean.Curriculum;
import com.example.experiment7_1.Bean.Node_Time;
import com.example.experiment7_1.Factory.ServiceFactory;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddCourseActivity extends AppCompatActivity {
    private static final String TAG ="AddCourseActivity" ;
    private Boolean buttonClick=true;
    private EditText TextName;
    private EditText TextNodeEnd;
    private EditText TextNodeStart;
    private EditText TextWeekEnd;
    private EditText TextWeekStart;
    private EditText TextAddress;
    private EditText TextID;
    private TextView textView12;
    private TextView textView14;
    private TextInputLayout layoutTextID;
    private TextInputLayout layoutTextName;
    private TextInputLayout layoutTextAddress;
    private TextInputLayout layoutTextWeekStart;
    private TextInputLayout layoutTextWeekEnd;
    private TextInputLayout layoutTextNodeStart;
    private TextInputLayout layoutTextNodeEnd;
    private ScrollView scrollContent2;
    private Button button;
    private Spinner spinner1;

    private Handler handler = new Handler();
    private int addClassWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);


       /* List<Node_Time> Node_TimeList= ServiceFactory.Service(getApplicationContext()).selectNode_Time();
        Iterator<Node_Time> iterator1 = Node_TimeList.iterator();
        while(iterator1.hasNext()){
            Node_Time cl=iterator1.next();
           // Log.d(TAG, "onCreate: Node_Time:"+cl.toString());
        }

        */
        button=findViewById(R.id.button);
        textView12=findViewById(R.id.textView12);
        textView14=findViewById(R.id.textView14);
        scrollContent2=findViewById(R.id.scrollContent2);
        spinner1=findViewById(R.id.spinner1);

        layoutTextName=findViewById(R.id.TextName);
        layoutTextID=findViewById(R.id.TextID);
        layoutTextAddress=findViewById(R.id.TextAddress);
        layoutTextWeekStart=findViewById(R.id.TextWeekStart);
        layoutTextWeekEnd=findViewById(R.id.TextWeekEnd);
        layoutTextNodeStart=findViewById(R.id.TextNodeStart);
        layoutTextNodeEnd=findViewById(R.id.TextNodeEnd);

        TextName = layoutTextName.getEditText();
        TextID = layoutTextID.getEditText();
        TextAddress = layoutTextAddress.getEditText();
        TextWeekStart=layoutTextWeekStart.getEditText();
        TextWeekEnd=layoutTextWeekEnd.getEditText();
        TextNodeStart=layoutTextNodeStart.getEditText();
        TextNodeEnd=layoutTextNodeEnd.getEditText();

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                addClassWeek=position+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              /*  if (!RegexUtils.isZh(s)) {
                    //显示错误提示
                    layoutTextName.setError("姓名只能为汉字");
                    layoutTextName.setErrorEnabled(true);
                } else {
                    layoutTextName.setErrorEnabled(false);
                }*/
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        TextID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if(RegexUtils.isREGEX_NOT_NEGATIVE_INTEGER(s.toString())){
                    Map<Integer, ClassList> classList=ServiceFactory.Service(getApplicationContext()).selectClassCid();
                    ClassList cl=classList.get(new Integer(s.toString()));
                    if(cl!=null){
                        textView12.setText("课程存在，添加课时");
                        String classId="已存在的课程信息：";
                        List<ClassList> clDD=ServiceFactory.Service(getApplicationContext()).selectClassID(Integer.toString(cl.getCLid()));
                        Iterator<ClassList> iterator = clDD.iterator();

                        while(iterator.hasNext()){
                            ClassList clID1=iterator.next();
                            classId=classId +"\n" +
                                    "地点："+clID1.getClassRoom()+"  周数："+clID1.getWeekSumStart()+"-"+clID1.getWeekSumEnd()+"周  节数："+clID1.getNodeStart()+"-"+clID1.getNodeEnd()+"节";
                            //Log.d(TAG, "onCreate: ClassList:"+cl.toString());
                        }
                        textView14.setText(classId);
                        //layoutTextID.setError(cl.getClassCame());
                        TextName.setText(cl.getClassCame());
                        TextName.setFocusable(false);//不可编辑
                        TextName.setFocusableInTouchMode(false);
                    }else{
                       // layoutTextID.setError("");
                        textView12.setText("添加课程");
                        textView14.setText("");
                        //layoutTextID.setError(cl.getClassCame());
                        TextName.setText("");
                        TextName.setFocusable(true);//可编辑
                        TextName.setFocusableInTouchMode(true);
                    }
                }else if(s.toString().equals("")){
                    textView12.setText("添加课程");
                    textView14.setText("");
                    TextID.setError(null);
                    TextName.setText("");
                    TextName.setFocusable(true);//可编辑
                    TextName.setFocusableInTouchMode(true);
                }
            }
        });

        //开始周数输入规范
        TextWeekStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // Log.d(TAG, "onTextChanged: s:"+s);
                if(RegexUtils.isREGEX_NOT_NEGATIVE_INTEGER(s.toString())){
                    int week=Integer.parseInt(s.toString());
                    if (week>20) {
                        //显示错误提示
                        buttonClick=false;
                        layoutTextWeekStart.setError("不能超过20周");
                        layoutTextWeekStart.setErrorEnabled(true);
                    } else if(week<1) {
                        buttonClick=false;
                        layoutTextWeekStart.setError("不能小于1周");
                        layoutTextWeekStart.setErrorEnabled(true);
                    }else{
                        buttonClick=true;
                        layoutTextWeekStart.setError(null);
                        layoutTextWeekStart.setErrorEnabled(false);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //结束周数输入规范
        TextWeekEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Log.d(TAG, "onTextChanged: s:"+s);
                if(RegexUtils.isREGEX_NOT_NEGATIVE_INTEGER(s.toString())){
                    int week=Integer.parseInt(s.toString());
                    if (week>20) {
                        //显示错误提示
                        buttonClick=false;
                        layoutTextWeekEnd.setError("不能超过20周");
                        layoutTextWeekEnd.setErrorEnabled(true);
                    } else if(week<1) {
                        buttonClick=false;
                        layoutTextWeekEnd.setError("不能小于1周");
                        layoutTextWeekEnd.setErrorEnabled(true);
                    }else if(!TextWeekStart.getText().toString().equals("")&&week<Integer.parseInt(TextWeekStart.getText().toString())) {
                        buttonClick=false;
                        layoutTextWeekEnd.setError("不能小于开始周");
                        layoutTextWeekEnd.setErrorEnabled(true);
                    }else{
                        buttonClick=true;
                        layoutTextWeekEnd.setError(null);
                        layoutTextWeekEnd.setErrorEnabled(false);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //开始节数输入规范
        TextNodeStart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              //  Log.d(TAG, "onTextChanged: s:"+s);
                if(RegexUtils.isREGEX_NOT_NEGATIVE_INTEGER(s.toString())){
                    int week=Integer.parseInt(s.toString());
                    if (week>11) {
                        //显示错误提示
                        buttonClick=false;
                        layoutTextNodeStart.setError("不能超过11节");
                        layoutTextNodeStart.setErrorEnabled(true);
                    } else if(week<1) {
                        buttonClick=false;
                        layoutTextNodeStart.setError("不能小于1节");
                        layoutTextNodeStart.setErrorEnabled(true);
                    }else{
                        buttonClick=true;
                        layoutTextNodeStart.setError(null);
                        layoutTextNodeStart.setErrorEnabled(false);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //结束节数输入规范
        TextNodeEnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               // Log.d(TAG, "onTextChanged: s:"+s);
                if(RegexUtils.isREGEX_NOT_NEGATIVE_INTEGER(s.toString())){
                    int week=Integer.parseInt(s.toString());
                    if (week>11) {
                        //显示错误提示
                        buttonClick=false;
                        layoutTextNodeEnd.setError("不能超过11节");
                        layoutTextNodeEnd.setErrorEnabled(true);
                    } else if(week<1) {
                        buttonClick=false;
                        layoutTextNodeEnd.setError("不能小于1节");
                        layoutTextNodeEnd.setErrorEnabled(true);
                    }else if(!TextNodeStart.getText().toString().equals("")&&week<Integer.parseInt(TextNodeStart.getText().toString())) {
                        buttonClick=false;
                        layoutTextNodeEnd.setError("不能小于开始节数");
                        layoutTextNodeEnd.setErrorEnabled(true);
                    }else{
                        buttonClick=true;
                        layoutTextNodeEnd.setError(null);
                        layoutTextNodeEnd.setErrorEnabled(false);
                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=TextName.getText().toString();
                String ID=TextID.getText().toString();
                String Address=TextAddress.getText().toString();
                String WeekStart=TextWeekStart.getText().toString();
                String WeekEnd=TextWeekEnd.getText().toString();
                String NodeStart=TextNodeStart.getText().toString();
                String NodeEnd=TextNodeEnd.getText().toString();

                if(Name.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }else if(ID.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }
                else if(Address.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }
                else if(WeekStart.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }
                else if(WeekEnd.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }
                else if(NodeStart.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }
                else if(NodeEnd.equals("")){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }else if(!buttonClick){
                    Toast.makeText(AddCourseActivity.this,"请完善信息",Toast.LENGTH_LONG).show();

                }else{
                    ClassList classList=new ClassList();
                    classList.setCLid(Integer.parseInt(ID));
                    classList.setClassCame(Name);
                    classList.setClassRoom(Address);
                    classList.setWeekSumStart(Integer.parseInt(WeekStart));
                    classList.setWeekSumEnd(Integer.parseInt(WeekEnd));
                    classList.setNodeStart(Integer.parseInt(NodeStart));
                    classList.setNodeEnd(Integer.parseInt(NodeEnd));

                    Map<Integer,Node_Time> node_TimeList=ServiceFactory.Service(getApplicationContext()).selectNode_Time();
                    Node_Time nodeTime1=node_TimeList.get(Integer.parseInt(NodeStart));
                    Node_Time nodeTime2=node_TimeList.get(Integer.parseInt(NodeEnd));

                    classList.setDayTime(nodeTime1.getStartTime()+"-"+nodeTime2.getEndTime());

                    classList.setWeekday(addClassWeek);
                    int weekNode=0;
                    List <Curriculum> Curriculum=ServiceFactory.Service(getApplicationContext()).selectCurriculumList();
                    Iterator<Curriculum> iterator1 = Curriculum.iterator();
                    while(iterator1.hasNext()){
                        Curriculum cl=iterator1.next();
                        if(cl.getDTid()==classList.getNodeStart()){
                            switch (classList.getWeekday()){
                                case 1:
                                    weekNode= cl.getWeekTime1();
                                    break;
                                case 2:
                                    weekNode= cl.getWeekTime2();
                                    break;
                                case 3:
                                    weekNode= cl.getWeekTime3();
                                    break;
                                case 4:
                                    weekNode= cl.getWeekTime4();
                                    break;
                                case 5:
                                    weekNode= cl.getWeekTime5();
                                    break;
                                case 6:
                                    weekNode= cl.getWeekTime6();
                                    break;
                                case 7:
                                    weekNode= cl.getWeekTime7();
                                    break;
                            }
                        }

                    }
                    if(weekNode!=0){
                        Toast.makeText(AddCourseActivity.this,"此时间段已存在课程！",Toast.LENGTH_LONG).show();
                    }else{
                        ServiceFactory.Service(getApplicationContext()).insertClass(classList);
                        List <Curriculum> Curriculum1=ServiceFactory.Service(getApplicationContext()).selectCurriculumList();
                        Iterator<Curriculum> iterator2 = Curriculum1.iterator();
                        while(iterator2.hasNext()){
                            Curriculum cl=iterator2.next();
                            if(cl.getDTid()>=classList.getNodeStart()&&cl.getDTid()<=classList.getNodeEnd()){
                                switch (classList.getWeekday()){
                                    case 1:
                                        cl.setWeekTime1(classList.getCLid());
                                        break;
                                    case 2:
                                        cl.setWeekTime2(classList.getCLid());
                                        break;
                                    case 3:
                                        cl.setWeekTime3(classList.getCLid());
                                        break;
                                    case 4:
                                        cl.setWeekTime4(classList.getCLid());
                                        break;
                                    case 5:
                                        cl.setWeekTime5(classList.getCLid());
                                        break;
                                    case 6:
                                        cl.setWeekTime6(classList.getCLid());
                                        break;
                                    case 7:
                                        cl.setWeekTime7(classList.getCLid());
                                        break;
                                }
                                ServiceFactory.Service(getApplicationContext()).updateCurriculum(cl);
                            }

                        }


                        //Log.d(TAG, "onClick: classList:"+classList);
                        Toast.makeText(AddCourseActivity.this,"添加课程成功！",Toast.LENGTH_LONG).show();


                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //回调
                                Intent intent =getIntent();
                                // Log.d(TAG, "onClick: Button3 ");
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        }, 2000);


                    }





                }

            }
        });
    }
}
