package com.nttdata.quarkus.management.api.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Base64;

public class QueryHelpers {

    private QueryHelpers() {}

    public static BigInteger getId(String base64Cursor, String key) {
        if(StringUtils.isBlank(base64Cursor)) return BigInteger.ZERO;

        String fromCursorDecoded = new String(Base64.getDecoder().decode(base64Cursor));

        String keySequence = MessageFormat.format("{0}:", key);

        if(!StringUtils.contains(fromCursorDecoded, keySequence)) return BigInteger.ZERO;

        String candidate = StringUtils.remove(fromCursorDecoded, keySequence);

        if(!NumberUtils.isDigits(candidate)) return BigInteger.ZERO;

        return new BigInteger(candidate);
    }


}
