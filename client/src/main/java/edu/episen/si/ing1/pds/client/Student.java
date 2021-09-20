package edu.episen.si.ing1.pds.client;

import java.util.Map;

public class Student {
    private Map<String,String> insert;
    private Map<String,String> delete;
    private Map<String,String> update;
    private boolean select;
    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "insert=" + insert +
                ", delete=" + delete +
                ", update=" + update +
                ", select=" + select +
                '}';
    }
    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Map<String, String> getInsert() {
        return insert;
    }

    public void setInsert(Map<String, String> insert) {
        this.insert = insert;
    }

    public Map<String, String> getDelete() {
        return delete;
    }

    public void setDelete(Map<String, String> delete) {
        this.delete = delete;
    }

    public Map<String, String> getUpdate() {
        return update;
    }

    public void setUpdate(Map<String, String> update) {
        this.update = update;
    }

}

