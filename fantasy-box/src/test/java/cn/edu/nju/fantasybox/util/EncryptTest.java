package cn.edu.nju.fantasybox.util;

public class EncryptTest {

    private RSAEncrypt rsaEncrypt;

    public EncryptTest(RSAEncrypt rsaEncrypt) {
        this.rsaEncrypt = rsaEncrypt;
    }

    public void test(){
        String s = "8r794784";
        String str = rsaEncrypt.encrypt(s);
        String rawStr = rsaEncrypt.decrypt(str);
        System.out.println(s.equals(rawStr));
    }

}
