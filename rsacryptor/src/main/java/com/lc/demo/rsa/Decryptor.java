package com.lc.demo.rsa;

public interface Decryptor {
    /**
     * 解密字节数组
     * @author Li-C 2019年4月29日
     * @param cipherText
     * @return
     */
    byte[] decrypt(byte[] cipherText);
}
