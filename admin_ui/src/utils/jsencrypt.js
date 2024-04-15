import JSEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 密钥对生成 http://web.chacuo.net/netrsakeypair


const publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCldonmdEgE+IEnA27a8+/GdCiZHcaz0Exq+DiMQFjcfLhGJwk17l5bBfLR3rI+CWpVnXVCV1WYY0oaWNcLOhp5jEpBoIbYFhoomDpwpLcHGDaIdlsX+9mFrl/dzVMEuysLIEL03o35GcXqKMRW7pE7VfwHcCfOHr/IRRNSji0w6wIDAQAB"


// Rsa 私钥
const privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKV2ieZ0SAT4gScDbtrz78Z0KJkdxrPQTGr4OIxAWNx8uEYnCTXuXlsF8tHesj4JalWddUJXVZhjShpY1ws6GnmMSkGghtgWGiiYOnCktwcYNoh2Wxf72YWuX93NUwS7KwsgQvTejfkZxeooxFbukTtV/AdwJ84ev8hFE1KOLTDrAgMBAAECgYAEggXAxxM9O239W50WDwZN0FVY0pRaysaCNZAI1in1m9pDYwXft8ZPUYqriBcwFH6WpYsPyyRUWgQnUptNPYCCGwVFjSQ2k+Bvys+/Kdrnu7frve0kpubMUwuwn7ow8x7kQAJssmyxCs+dE3Pv9fwyanb3TgWji5FAozYybP5pNQJBANT3KM4IzRyNn+neaxi8Bm/nj6lpZsj2spsf55v2Q4bJnf2J9neBWKANYbNmmgaNJPsVX1rDyf0V0792ZRvKNqUCQQDG5gxcYh/lHZt/5lAEQ5xGkydG1Mpa5TrCYg+ndb+niQRXUSi4bo7pUVFSBZLDuAo3bpx4wllEmvFfbTwiMMRPAkEAo58BisrYTAfDxTtNrUNI7tFKXy7L+bsQnn50xC8A0p409Ib7BfYq0U12sw8xZ7cpon9ZM7MZjYknlwKvzBogwQJALs6utAMHnIS2Nog95nL+0QEKajnjkTaG+H3ZT0B/pO6yzdW0roqeKTxQ1eun2VVzWg421phuJ54LFbkAQM+XMwJANBIFUQa+xLRBPW7z9/nTsnPI1FrxCMpmpeJczAqjwxyhfriLaGesbLh9eIb93+0XZFYBY2yemuFa+6Ktd8S8XA==";

// 加密
export function encrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPublicKey(publicKey) // 设置公钥
  return encryptor.encrypt(txt) // 对数据进行加密
}

// 解密
export function decrypt(txt) {
  const encryptor = new JSEncrypt()
  encryptor.setPrivateKey(privateKey) // 设置私钥
  return encryptor.decrypt(txt) // 对数据进行解密
}

