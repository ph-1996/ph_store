package com.cph.lib.core.ui.recycler;

import java.util.ArrayList;

/**
 * Created by CPH on 2020/1/30
 */
public abstract class DatatConverter {
    protected final ArrayList<MultipleItemEntity> ENTITIES = new ArrayList<>();
    private String mJsonData = null;
    public abstract ArrayList<MultipleItemEntity> convert();
    public DatatConverter setJsonDate(String date){
        this.mJsonData = date;
        return this;
    }
    public String getJsonDate(){
        if(mJsonData == null || mJsonData.isEmpty()){
            throw new NullPointerException("DATA IS NULL");
        }
        return mJsonData;
    }
}
