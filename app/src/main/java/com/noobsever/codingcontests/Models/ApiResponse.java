package com.noobsever.codingcontests.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
public class ApiResponse
{
    @SerializedName("built_by")
    @Expose
    private String builtBy;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("objects")
    @Expose
    private List<ContestObject> objects = null;

    public String getBuiltBy() {
        return builtBy;
    }

    public void setBuiltBy(String builtBy) {
        this.builtBy = builtBy;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public List<ContestObject> getObjects() {
        return objects;
    }

    public void setObjects(List<ContestObject> objects) {
        this.objects = objects;
    }

}

