package com.iabtcf.decoder.base64;

import java.util.Base64;

public interface Base64Decoder {
    default byte[] decode(String encoded) {
        return Base64.getUrlDecoder().decode(encoded);
    }
}
