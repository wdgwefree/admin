package com.wdg.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @description: 非对称加密-RSA（公钥加密，私钥解密）
 * @author: wdg
 * @create: 2023-11-28 16:47
 */
public class RSAUtil {

    private static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI7rW+8NV6BvT6h/7Ib8txRgtOY0eFwsRg1fC3YWvUPEvoB/7V6Um3eCO9CmZcFdJPRx/bCYf8HsA8TpumzcCM1R+ukd7YVhsQ77SMueZJl//INIdK2ZLnT4VJHbvYD4mvC9rJD/pi+PRoHa6FBGDiKJWQn3re9hi+wSESNWfS2bAgMBAAECgYA2GHPVyfEO8tLt7m2yslkBx5DxSY79JGznhsAp1HWS/xc0QDeygwFNfOfzE+iUBhOEytjBTReeaR0MHYPIzIgvzjWOlCGXDE3uK1q0G/aHFjQfpcrgqrhKFnz5BrNucMMsELG75B0AgAXScte9wPFEPNFkToPYAdNdKE7uyRUy4QJBAMHVDbIMLZyY5ONfKqwOPQ4xnNu7uXyHcQQNA5BGJEzmXoIGJW2SFH2JbbBweV974alpCZsztj+j66gi0hxAOOkCQQC8wgMyvxv59uM48tGxG5u9h42B+fchKajMmSF7spZmNsLHshizsTEU7zrBkqUWwWy2rn+hJU3bzFAhJxuVYp/jAkBnqfRbJtw2BidT2w/NWQgaUTpc4jc/fIha7ELdpEXBwusEixj1Us0WnqtFU8H16SE1I3H5yVxNCPP9xjGs3kQRAkA1yYdFx6V+NjcLfE36jOZ1PJI7TnJ4dbmPC7e52ur2l3Yrl3logv58e6sfi90VPervp4vg7JNiJbdTqyQ2DlOHAkEAjLax36g+MVuoMVDXeRQbhEzFqY7Jk6nZN2I1A9e7ilLDLmeQisO9qZpRUmr45FEiV8f/KIRlQ1c8WdDfrcWuBw==\n";

    private static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCO61vvDVegb0+of+yG/LcUYLTmNHhcLEYNXwt2Fr1DxL6Af+1elJt3gjvQpmXBXST0cf2wmH/B7APE6bps3AjNUfrpHe2FYbEO+0jLnmSZf/yDSHStmS50+FSR272A+JrwvayQ/6Yvj0aB2uhQRg4iiVkJ963vYYvsEhEjVn0tmwIDAQAB\n";

    private static RSA rsa=new RSA(private_key,public_key);


    //用于生成公钥私钥的base64
    //public static void main(String[] args) {
    //    KeyPair pair = SecureUtil.generateKeyPair("RSA");
    //    PrivateKey aPrivate = pair.getPrivate();
    //    PublicKey aPublic = pair.getPublic();
    //    String private_key = Base64.encode(aPrivate.getEncoded());
    //    String public_key = Base64.encode(aPublic.getEncoded());
    //    System.out.println(private_key + "\n");
    //    System.out.println(public_key);
    //}

    /**
     * 自己的公钥加密
     * @param text
     * @return
     */
    public static String encryptByPublicKey(String text){
        byte[] encrypt = rsa.encrypt(text, KeyType.PublicKey);
        return Base64.encode(encrypt);
    }

    /**
     * 自己的私钥解密
     * @param text
     * @return
     */
    public static String decryptByPrivateKey (String text){
        byte[] decrypt = rsa.decrypt(Base64.decode(text), KeyType.PrivateKey);
        return new String(decrypt);
    }

    //测试
    //public static void main(String[] args) {
    //    String temp = encryptByPublicKey("测试1a号");
    //    System.out.println(temp+"\n");
    //    System.out.println(decryptByPrivateKey(temp));
    //}




}
