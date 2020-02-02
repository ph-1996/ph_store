package com.cph.lib.ec.main.sort.content;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cph.lib.ec.R;

import java.util.List;

/**
 * Created by CPH on 2020/2/2
 */
public class SectionAdapter extends BaseSectionQuickAdapter<SectionBean, BaseViewHolder> {
    private static final RequestOptions OPTIONS =new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .dontAnimate();
    public SectionAdapter(int layoutResId, int sectionHeadResId, List<SectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionBean item) {
       helper.setText(R.id.tv_header,item.header);
       helper.setVisible(R.id.tv_more,item.isMore());
       helper.addOnClickListener(R.id.tv_more);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionBean item) {
        final String thumb = item.t.getmGoodsThumb();
        final String name = item.t.getmGoodsName();
        final int id = item.t.getmGoodsId();
        final SectionContentItemEntity entity = item.t;
        helper.setText(R.id.tv_content_text,name);
        final ImageView imageView = helper.getView(R.id.iv_content_image);
        Glide.with(mContext)
                .load(thumb)
                .into(imageView);

    }
}
