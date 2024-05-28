
###登录说明

1. 前端RSA公钥加密,后端私钥解密

2. 密码MD5+salt加密存储

3. jwt+redis 实现登录状态校验


##授权

1.JWT生成token返回给前端（载荷中存储redisKey,用户id）

2.前端每次请求携带token

3.后端校验token并解析获取redisKey


