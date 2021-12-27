package com.bam;

import java.time.LocalDateTime;


public class LeaveInfo {
    private String name;
    private LocalDateTime leaveDate;
    private String type;

    public LeaveInfo(String name, LocalDateTime leaveDate, String type) {
        this.name = name;
        this.leaveDate = leaveDate;
        this.type = type;
    }

    public LocalDateTime getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LeaveInfo{" +
                "name='" + name + '\'' +
                ", leaveDate=" + leaveDate +
                ", type='" + type + '\'' +
                '}';
    }
}
