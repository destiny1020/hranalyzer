package com.destiny1020.hranalyzer.util;

import org.apache.commons.lang3.StringUtils;

public class StandardizeString {

    private static final String UNICODE_SPACE = "[\u00A0\u1680​\u180e\u2000-\u2009\u200a​\u200b​\u202f\u205f​\u3000]";

    public static final String standardize(String content) {
        return standardize(content, false);
    }

    public static final String standardize(String content, boolean strict) {
        if (StringUtils.isNotEmpty(content)) {
            content = content.trim().replaceAll(UNICODE_SPACE, " ")
                    .replaceAll(" +", " ").replaceAll("-", "");
        }

        if (strict) {
            content = content.replaceAll("－", "");
        }

        return StringUtils.isNotEmpty(content) ? content : "";
    }
}
