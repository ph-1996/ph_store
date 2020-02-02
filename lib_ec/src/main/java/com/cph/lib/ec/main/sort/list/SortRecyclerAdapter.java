package com.cph.lib.ec.main.sort.list;

import android.graphics.Color;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.cph.lib.core.delegates.CoreDelegate;
import com.cph.lib.core.ui.recycler.ItemType;
import com.cph.lib.core.ui.recycler.MultipleFields;
import com.cph.lib.core.ui.recycler.MultipleItemEntity;
import com.cph.lib.core.ui.recycler.MultipleRecyclerAdapter;
import com.cph.lib.core.ui.recycler.MultipleViewHolder;
import com.cph.lib.ec.R;
import com.cph.lib.ec.main.sort.SortDelegate;
import com.cph.lib.ec.main.sort.content.ContentDelegate;

import java.io.FileInputStream;
import java.util.List;

/**
 * Created by CPH on 2020/2/2
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortDelegate DELEGATE;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        DELEGATE = delegate;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()){
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final TextView name = holder.getView(R.id.tv_vertical_list_name);
                final View line = holder.getView(R.id.view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int currentPosition = holder.getAdapterPosition();
                        if(mPrePosition != currentPosition){
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG,false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的item
                            entity.setField(MultipleFields.TAG,true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;

                            final int contenId= getData().get(currentPosition).getField(MultipleFields.ID);
                            //根据点击事件来改变右侧内容界面
                            showContent(contenId);
                        }
                    }
                });
                if(!isClicked){
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));

                }else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext,R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                holder.setText(R.id.tv_vertical_list_name,text);
                break;
            default:
                break;
        }
    }
    private void showContent(int contentId){
        final ContentDelegate delegate = ContentDelegate.newInstance(contentId);
        switchContent(delegate);
    }
    private void switchContent(ContentDelegate delegate){
        final CoreDelegate contentDelegate = DELEGATE.findChildFragment(ContentDelegate.class);
        if (contentDelegate != null){
            contentDelegate.replaceFragment(delegate,false);
        }
    }
}
