package com.cph.lib.core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by CPH on 2020/1/29
 */
public final class ItemBuilder {
    private static final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    static ItemBuilder builder(){
        return new ItemBuilder();
    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);
        return this;
    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }
    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }
 }
