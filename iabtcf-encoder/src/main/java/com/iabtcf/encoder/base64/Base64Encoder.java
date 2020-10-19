package com.iabtcf.encoder.base64;

import java.util.Base64;

public interface Base64Encoder {
    default String encode(byte[] input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input);
    }
}
