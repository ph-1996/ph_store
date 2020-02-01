package com.cph.lib.core.ui.recycler;

import java.util.LinkedHashMap;
import java.util.WeakHashMap;

/**
 * Created by CPH on 2020/1/30
 */
public class MultipleItemEntityBuilder {

    private static final LinkedHashMap<Object,Object>  FIELDS = new LinkedHashMap<>();

    public MultipleItemEntityBuilder(){
        //清除之前的数据
        FIELDS.clear();
    }

    public final MultipleItemEntityBuilder setItemType(int itemType){
        FIELDS.put(MultipleFields.ITEM_TYPR,itemType);
        return this;
    }

    public final MultipleItemEntityBuilder setField(Object key,Object value){
        FIELDS.put(key,value);
        return this;
    }
    public final MultipleItemEntityBuilder setFields(LinkedHashMap<Object,Object> map){
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build(){
       return new MultipleItemEntity(FIELDS);
    }



}

