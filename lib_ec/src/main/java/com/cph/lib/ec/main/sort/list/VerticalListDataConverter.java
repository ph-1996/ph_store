package com.cph.lib.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cph.lib.core.ui.recycler.DatatConverter;
import com.cph.lib.core.ui.recycler.ItemType;
import com.cph.lib.core.ui.recycler.MultipleFields;
import com.cph.lib.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * Created by CPH on 2020/2/2
 */
public class VerticalListDataConverter extends DatatConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonDate())
                                    .getJSONObject("data")
                                    .getJSONArray("list");
        final int size = dataArray.size();
        for(int i =0;i<size;i++){
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("Id");
            final String name = data.getString("name");

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPR, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID,id)
                    .setField(MultipleFields.TEXT,name)
                    .setField(MultipleFields.TAG,false)
                    .build();

            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG,true);

        }
        return dataList;
    }
}
