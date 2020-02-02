package com.cph.lib.ec.main;

import android.graphics.Color;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.cph.lib.core.delegates.bottom.BaseBottomDelegate;
import com.cph.lib.core.delegates.bottom.BottomItemDelegate;
import com.cph.lib.core.delegates.bottom.BottomTabBean;
import com.cph.lib.core.delegates.bottom.ItemBuilder;
import com.cph.lib.ec.main.index.IndexDelegate;
import com.cph.lib.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * Created by CPH on 2020/1/29
 */
public class EcBottomDelegate extends BaseBottomDelegate {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> item = new LinkedHashMap<>();
        item.put(new BottomTabBean("{fa-home}","主页"),new IndexDelegate());
        item.put(new BottomTabBean("{fa-sort}","分类"),new SortDelegate());
        item.put(new BottomTabBean("{fa-compass}","发现"),new IndexDelegate());
        item.put(new BottomTabBean("{fa-shopping-cart}","购物车"),new SortDelegate());
        item.put(new BottomTabBean("{fa-user}","我的"),new SortDelegate());

        return builder.addItems(item).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#DC143C");
    }
}
