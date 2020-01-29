package com.cph.phstore.generator;

import com.cph.annotations.PayEntryGenerator;
import com.cph.lib.core.wechat.templates.wPayXEntryTemplate;


/**
 * Created by CPH on 2019/7/8
 */
@PayEntryGenerator(packageName = "com.cph.phstore", payEntryTemplate = wPayXEntryTemplate.class)
public interface WeChatPayEntry {
}
