package com.cph.phstore.generator;


import com.cph.annotations.EntryGenerator;
import com.cph.lib.core.wechat.templates.WXEntryTemplate;

/**
 * Created by CPH on 2019/7/8
 */
@EntryGenerator(packageName = "com.cph.phstore", entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {
}
