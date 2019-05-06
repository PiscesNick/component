package com.lc.demo.rsa;

import com.lc.demo.rsa.RSAEncryption;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class TestRSA {

    @Test
    public void test1(){
        String str="this is test";
        RSAEncryption encryption=new RSAEncryption("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALQyoZWkYGHYVALvQwZMhphonKA+6k3H5YNGxoHJzfH1UoTVf60DPV0n24uPGjiPQefBNXV9p5PWgIvZb0YxEl6EvhWcdOQA856q6A9PrxH1T31OzpXlpWw7xABlKXUKcKWwoBiqyy17cKxADehuAa/4uHqLLlg+HJxvjVK/0IqxAgMBAAECgYBjBcfB4diNLEHOcwjT902nhyU68hfzsjuvjj8bp3gi1HsKQACvZK5d+L8uy+7O0fqXjpb//5MNLLzrHQuG+QRsPAlzvymh+x7iaF1CLdkpSkiSsfaLZt3UYYkLPnNN94l1wCqelwNEeTpfUT2cUPR/l8u1YKCsfcVJ61pPFHjWIQJBAOJDyg3Sr8PQpxBlUOk0yV12g4iCbjZ/OHnAse7Xa8h9rSeelBtA2u0w7e8hWJTu8UIgDo1qDhqe02+zo4IeS60CQQDL4QMlrRmiT4aSbH2iqWAY6F2LJbBsrWwo9cfnn6RiWl+4/BFEFEGppxvvj8Irx7g20hrXCkU7EG67qlqlaFuVAkAkfwC5aBF3yVEZQO5jH2OXksU2oKs8WRr38JAC0XaOOU9Tw61qE/T8K1dFqiBKrZv2qYsPXlr2CkZajyRBmbdlAkAMS1VVQEr7brA+xCwPUwdoeRqq+WraItsS3T9gyE+xXqHHFew0nsQW5uzYVWLwIOsK40CbFxN1rFgBGRS+RLzBAkEAoiFWAUs/vD6S29SOdfMokEACv7Ad7ti/EoL6M8V45uSY6TjoWwjy5mLxEukEDX1Ua80mhI5rRzb+exSJejNaZA==","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC0MqGVpGBh2FQC70MGTIaYaJygPupNx+WDRsaByc3x9VKE1X+tAz1dJ9uLjxo4j0HnwTV1faeT1oCL2W9GMRJehL4VnHTkAPOequgPT68R9U99Ts6V5aVsO8QAZSl1CnClsKAYqsste3CsQA3obgGv+Lh6iy5YPhycb41Sv9CKsQIDAQAB");
        //公钥加密 私钥解密
        String encrypt = encryption.pubEncryptor().encrypt(str.getBytes());
        System.out.println(encrypt);

       byte[] deByte= encryption.priDecryptor().decrypt(encrypt.getBytes());
        try {
            String deStr=new String(deByte,"UTF-8");
            System.out.println(deStr);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
