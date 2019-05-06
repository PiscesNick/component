package com.lc.demo.rsa;

public interface Encryptor {
    /**
     * 加密字节数组
     * @author Li-C 2019年4月29日
     * @param clearText
     * @return
     */
    String encrypt(byte[] clearText);
}
