package com.lkvarela.list;

/**
 * Created by KCV on 05/06/16.
 */
public class Aviso {

    private int mId;
    private String mContent;
    private int mImportant;


    public Aviso(int id, String content, int important) {
        mId = id;
        mContent = content;
        mImportant = important;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getImportant() {
        return mImportant;
    }

    public void setImportant(int important) {
        mImportant = important;
    }
}
