package cn.edu.nju.fantasybox.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

@Component
public class RSAEncrypt {

    private Map<Integer, String> keyMap = new HashMap<>();  //用于封装随机产生的公钥与私钥

    @Value("${private-key}")
    private String privateKeyPath;

    @Value("${public-key}")
    private String publicKeyPath;

    private FileHelper fileHelper;

    @Autowired
    public RSAEncrypt(FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

    /**
     * 生成密钥对
     */
    private void genKeyPair() {
        String publicKeyString = fileHelper.readFirstLine(publicKeyPath);
        String privateKeyString = fileHelper.readFirstLine(privateKeyPath);
        // 将公钥和私钥保存到Map
        keyMap.put(0, publicKeyString);  //0表示公钥
        keyMap.put(1, privateKeyString);  //1表示私钥
    }

    /**
     * RSA公钥加密
     *
     * @param str 加密字符串
     * @return 密文
     */
    public String encrypt(String str) {
        if (keyMap.size() == 0) {
            genKeyPair();
        }
        String publicKey = keyMap.get(0);
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = null;
        Cipher cipher = null;
        String outStr = null;
        try {
            pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str 加密字符串
     * @return 铭文
     */
    public String decrypt(String str) {
        if (keyMap.size() == 0) {
            genKeyPair();
        }
        String privateKey = keyMap.get(1);
        //64位解码加密后的字符串
        byte[] inputByte = new byte[0];
        String outStr = null;
        try {
            inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
            //base64编码的私钥
            byte[] decoded = Base64.decodeBase64(privateKey);
            RSAPrivateKey priKey =
                    (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
            //RSA解密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, priKey);
            outStr = new String(cipher.doFinal(inputByte));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outStr;
    }

}

