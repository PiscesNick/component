package com.lc.demo.rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 生成公钥 私钥
 * RSA加密算法，【公钥加密，私钥解密】【私钥加密，公钥解密】
 */
public class GenKey {

    public static void main(String[] args){
        try {
            KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
            //加密位数（512/1024/2048/4096 bit）生成私钥与公钥
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey=keyPair.getPublic();
            String priStr=java.util.Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String pubStr=java.util.Base64.getEncoder().encodeToString(publicKey.getEncoded());
            System.out.printf("私钥：%s%n", priStr);
            System.out.printf("公钥钥：%s%n", pubStr);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
