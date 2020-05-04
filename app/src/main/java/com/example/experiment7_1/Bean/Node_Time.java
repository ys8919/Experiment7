package com.example.experiment7_1.Bean;

public class Node_Time {
    private int Nid;
    private int Node;
    private String  StartTime;
    private String EndTime;

    public Node_Time() {
    }

    public Node_Time(int nid, int node, String startTime, String endTime) {
        Nid = nid;
        Node = node;
        StartTime = startTime;
        EndTime = endTime;
    }

    @Override
    public String toString() {
        return "Node_Time{" +
                "Nid=" + Nid +
                ", Node=" + Node +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                '}';
    }

    public int getNid() {
        return Nid;
    }

    public void setNid(int nid) {
        Nid = nid;
    }

    public int getNode() {
        return Node;
    }

    public void setNode(int node) {
        Node = node;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
}
