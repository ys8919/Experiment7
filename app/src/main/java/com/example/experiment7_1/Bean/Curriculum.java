package com.example.experiment7_1.Bean;

public class Curriculum {
    private int DTid;
    private int WeekTime1;
    private int WeekTime2;
    private int WeekTime3;
    private int WeekTime4;
    private int WeekTime5;
    private int WeekTime6;
    private int WeekTime7;

    public Curriculum() {
    }

    public Curriculum(int DTid, int weekTime1, int weekTime2, int weekTime3, int weekTime4, int weekTime5, int weekTime6, int weekTime7) {
        this.DTid = DTid;
        WeekTime1 = weekTime1;
        WeekTime2 = weekTime2;
        WeekTime3 = weekTime3;
        WeekTime4 = weekTime4;
        WeekTime5 = weekTime5;
        WeekTime6 = weekTime6;
        WeekTime7 = weekTime7;
    }

    public int getDTid() {
        return DTid;
    }

    public void setDTid(int DTid) {
        this.DTid = DTid;
    }

    public int getWeekTime1() {
        return WeekTime1;
    }

    public void setWeekTime1(int weekTime1) {
        WeekTime1 = weekTime1;
    }

    public int getWeekTime2() {
        return WeekTime2;
    }

    public void setWeekTime2(int weekTime2) {
        WeekTime2 = weekTime2;
    }

    public int getWeekTime3() {
        return WeekTime3;
    }

    public void setWeekTime3(int weekTime3) {
        WeekTime3 = weekTime3;
    }

    public int getWeekTime4() {
        return WeekTime4;
    }

    public void setWeekTime4(int weekTime4) {
        WeekTime4 = weekTime4;
    }

    public int getWeekTime5() {
        return WeekTime5;
    }

    public void setWeekTime5(int weekTime5) {
        WeekTime5 = weekTime5;
    }

    public int getWeekTime6() {
        return WeekTime6;
    }

    public void setWeekTime6(int weekTime6) {
        WeekTime6 = weekTime6;
    }

    public int getWeekTime7() {
        return WeekTime7;
    }

    public void setWeekTime7(int weekTime7) {
        WeekTime7 = weekTime7;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "DTid=" + DTid +
                ", WeekTime1=" + WeekTime1 +
                ", WeekTime2=" + WeekTime2 +
                ", WeekTime3=" + WeekTime3 +
                ", WeekTime4=" + WeekTime4 +
                ", WeekTime5=" + WeekTime5 +
                ", WeekTime6=" + WeekTime6 +
                ", WeekTime7=" + WeekTime7 +
                '}';
    }
}
