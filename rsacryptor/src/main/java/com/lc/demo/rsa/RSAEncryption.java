package com.lc.demo.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class RSAEncryption implements Encryption{

    private String privateKeyStr;
    private String publicKeyStr;

    Encoder encoder = Base64.getEncoder();
    Decoder decoder = Base64.getDecoder();

    public RSAEncryption(String privateKeyStr,String publicKeyStr){
        super();
        this.privateKeyStr=privateKeyStr;
        this.publicKeyStr=publicKeyStr;
    }

    protected PrivateKeyRSACryptor getPriCryptor() {
        try {
            return new PrivateKeyRSACryptor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected PublicKeyRSACryptor getPubCryptor() {
        try {
            return new PublicKeyRSACryptor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 私钥加解密
     * @author Li-C 2019年5月6日
     *
     */
    class PrivateKeyRSACryptor implements Encryptor,Decryptor{

        /**
         * 私钥解密
         */
        @Override
        public byte[] decrypt(byte[] cipherText) {
            try {
                byte[] decode=decoder.decode(cipherText);
                return deCipher.doFinal(decode);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        /**
         * 私钥加密
         */
        @Override
        public String encrypt(byte[] clearText) {
            try {
                byte[] byteRSA=enCipher.doFinal(clearText);
                return encoder.encodeToString(byteRSA);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        private final String CIPHER_MODE="RSA";

        private Cipher enCipher = null;

        private Cipher deCipher = null;

        public PrivateKeyRSACryptor() throws Exception {

            byte[] priKeyBytes = decoder.decode(privateKeyStr);
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_MODE);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            deCipher = Cipher.getInstance(keyFactory.getAlgorithm());
            deCipher.init(Cipher.DECRYPT_MODE, privateK);

            enCipher = Cipher.getInstance(keyFactory.getAlgorithm());
            enCipher.init(Cipher.ENCRYPT_MODE, privateK);
        }

    }

    /**
     * 公钥加解密
     * @author Li-C 2019年5月6日
     *
     */
    class PublicKeyRSACryptor implements Encryptor,Decryptor{

        private final String CIPHER_MODE="RSA";

        private Cipher enCipher = null;

        private Cipher deCipher = null;

        public PublicKeyRSACryptor() throws Exception{
            KeyFactory keyFactory = KeyFactory.getInstance(CIPHER_MODE);
            byte[] priKeyBytes = decoder.decode(publicKeyStr);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(priKeyBytes);
            PublicKey publicK = keyFactory.generatePublic(x509KeySpec);
            deCipher = Cipher.getInstance(keyFactory.getAlgorithm());
            deCipher.init(Cipher.DECRYPT_MODE, publicK);

            enCipher = Cipher.getInstance(keyFactory.getAlgorithm());
            enCipher.init(Cipher.ENCRYPT_MODE, publicK);
        }


        @Override
        public byte[] decrypt(byte[] cipherText) {
            try {
                byte[] decode=decoder.decode(cipherText);
                return deCipher.doFinal(decode);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public String encrypt(byte[] clearText) {
            try {
                byte[] byteRSA=enCipher.doFinal(clearText);
                return encoder.encodeToString(byteRSA);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }

    @Override
    public Decryptor pubDecryptor() {
        return getPriCryptor();
    }

    @Override
    public Encryptor pubEncryptor() {
        return getPriCryptor();
    }

    @Override
    public Decryptor priDecryptor() {
        return getPubCryptor();
    }

    @Override
    public Encryptor priEncryptor() {
        return getPubCryptor();
    }


}
