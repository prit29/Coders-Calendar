package com.noobsever.codingcontests.Models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.noobsever.codingcontests.Utils.Constants;

@Entity(tableName = Constants.CONTEST_OBJECT_TABLE_NAME)
public class ContestObject {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    private String title,start,end,duration,link,status;

    public ContestObject() {}

    public ContestObject(String title, String start,String end, String duration,String link,String status) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this.link = link;
        this.status = status;
    }

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getDuration() {
        return duration;
    }

    public String getLink() {
        return link;
    }

    public String getStatus() {
        return status;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


