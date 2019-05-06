package com.lc.demo.rsa;

public interface Encryption {
    /**
     * 解密实体
     * @author Li-C 2019年5月5日
     * @return
     */
    Decryptor pubDecryptor();

    /**
     * 加密实体
     * @author Li-C 2019年5月5日
     * @return
     */
    Encryptor pubEncryptor();
    /**
     * 解密实体
     * @author Li-C 2019年5月5日
     * @return
     */
    Decryptor priDecryptor();

    /**
     * 加密实体
     * @author Li-C 2019年5月5日
     * @return
     */
    Encryptor priEncryptor();

}
