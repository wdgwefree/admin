package com.wdg.common.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * @description: 非对称加密-RSA（公钥加密，私钥解密）
 * @author: wdg
 * @create: 2023-11-28 16:47
 */
public class RSAUtil {

    private static String public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCldonmdEgE+IEnA27a8+/GdCiZHcaz0Exq+DiMQFjcfLhGJwk17l5bBfLR3rI+CWpVnXVCV1WYY0oaWNcLOhp5jEpBoIbYFhoomDpwpLcHGDaIdlsX+9mFrl/dzVMEuysLIEL03o35GcXqKMRW7pE7VfwHcCfOHr/IRRNSji0w6wIDAQAB\n";

    private static String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKV2ieZ0SAT4gScDbtrz78Z0KJkdxrPQTGr4OIxAWNx8uEYnCTXuXlsF8tHesj4JalWddUJXVZhjShpY1ws6GnmMSkGghtgWGiiYOnCktwcYNoh2Wxf72YWuX93NUwS7KwsgQvTejfkZxeooxFbukTtV/AdwJ84ev8hFE1KOLTDrAgMBAAECgYAEggXAxxM9O239W50WDwZN0FVY0pRaysaCNZAI1in1m9pDYwXft8ZPUYqriBcwFH6WpYsPyyRUWgQnUptNPYCCGwVFjSQ2k+Bvys+/Kdrnu7frve0kpubMUwuwn7ow8x7kQAJssmyxCs+dE3Pv9fwyanb3TgWji5FAozYybP5pNQJBANT3KM4IzRyNn+neaxi8Bm/nj6lpZsj2spsf55v2Q4bJnf2J9neBWKANYbNmmgaNJPsVX1rDyf0V0792ZRvKNqUCQQDG5gxcYh/lHZt/5lAEQ5xGkydG1Mpa5TrCYg+ndb+niQRXUSi4bo7pUVFSBZLDuAo3bpx4wllEmvFfbTwiMMRPAkEAo58BisrYTAfDxTtNrUNI7tFKXy7L+bsQnn50xC8A0p409Ib7BfYq0U12sw8xZ7cpon9ZM7MZjYknlwKvzBogwQJALs6utAMHnIS2Nog95nL+0QEKajnjkTaG+H3ZT0B/pO6yzdW0roqeKTxQ1eun2VVzWg421phuJ54LFbkAQM+XMwJANBIFUQa+xLRBPW7z9/nTsnPI1FrxCMpmpeJczAqjwxyhfriLaGesbLh9eIb93+0XZFYBY2yemuFa+6Ktd8S8XA==\n";

    private static RSA rsa=new RSA(private_key,public_key);


    //用于生成公钥私钥的base64
    //public static void main(String[] args) {
    //    KeyPair pair = SecureUtil.generateKeyPair("RSA",1024);
    //    PrivateKey aPrivate = pair.getPrivate();
    //    PublicKey aPublic = pair.getPublic();
    //    String private_key = Base64.encode(aPrivate.getEncoded());
    //    String public_key = Base64.encode(aPublic.getEncoded());
    //    System.out.println(public_key);
    //    System.out.println(private_key + "\n");
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
