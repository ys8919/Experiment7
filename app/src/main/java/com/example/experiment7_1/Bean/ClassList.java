package com.example.experiment7_1.Bean;

public class ClassList {
    private int id;
    private int CLid;
    private String ClassCame;
    private int weekSumStart;
    private int weekSumEnd;
    private String ClassRoom;
    private String DayTime;
    private int NodeStart;
    private int NodeEnd;
    private int weekday;

    public ClassList() {
    }

    public ClassList(int id, int CLid, String classCame, int weekSumStart, int weekSumEnd, String classRoom, String dayTime, int nodeStart, int nodeEnd, int weekday) {
        this.id = id;
        this.CLid = CLid;
        ClassCame = classCame;
        this.weekSumStart = weekSumStart;
        this.weekSumEnd = weekSumEnd;
        ClassRoom = classRoom;
        DayTime = dayTime;
        NodeStart = nodeStart;
        NodeEnd = nodeEnd;
        this.weekday = weekday;
    }

    @Override
    public String toString() {
        return "ClassList{" +
                "id=" + id +
                ", CLid=" + CLid +
                ", ClassCame='" + ClassCame + '\'' +
                ", weekSumStart=" + weekSumStart +
                ", weekSumEnd=" + weekSumEnd +
                ", ClassRoom='" + ClassRoom + '\'' +
                ", DayTime='" + DayTime + '\'' +
                ", NodeStart=" + NodeStart +
                ", NodeEnd=" + NodeEnd +
                ", weekday=" + weekday +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCLid() {
        return CLid;
    }

    public void setCLid(int CLid) {
        this.CLid = CLid;
    }

    public String getClassCame() {
        return ClassCame;
    }

    public void setClassCame(String classCame) {
        ClassCame = classCame;
    }

    public int getWeekSumStart() {
        return weekSumStart;
    }

    public void setWeekSumStart(int weekSumStart) {
        this.weekSumStart = weekSumStart;
    }

    public int getWeekSumEnd() {
        return weekSumEnd;
    }

    public void setWeekSumEnd(int weekSumEnd) {
        this.weekSumEnd = weekSumEnd;
    }

    public String getClassRoom() {
        return ClassRoom;
    }

    public void setClassRoom(String classRoom) {
        ClassRoom = classRoom;
    }

    public String getDayTime() {
        return DayTime;
    }

    public void setDayTime(String dayTime) {
        DayTime = dayTime;
    }

    public int getNodeStart() {
        return NodeStart;
    }

    public void setNodeStart(int nodeStart) {
        NodeStart = nodeStart;
    }

    public int getNodeEnd() {
        return NodeEnd;
    }

    public void setNodeEnd(int nodeEnd) {
        NodeEnd = nodeEnd;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }
}
