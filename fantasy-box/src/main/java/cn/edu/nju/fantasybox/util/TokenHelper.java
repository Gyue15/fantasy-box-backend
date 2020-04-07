package cn.edu.nju.fantasybox.util;

import cn.edu.nju.fantasybox.configuration.interceptor.BusinessException;
import cn.edu.nju.fantasybox.model.ResultEnums;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenHelper {

    private static final int TIME_LIMIT = 1000 * 60 * 60 * 24; //token过期时间24小时

    /**
     * 创建一个32-byte的密匙
     */
    private static final byte[] secret = ("g3YGj2ufiyeqkytrahgfy72861ksla9L").getBytes();

    private final Logger logger = LoggerFactory.getLogger(TokenHelper.class);


    public String getToken(long userId) {
        String token = "";
//        token= JWT.create().withAudience(Long.toString(user.getId()))// 将 user id 保存到 token 里面
//                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        Map<String, Object> map = new HashMap<>();
        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", userId);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime() + TIME_LIMIT);
        try {
            token = createToken(map);
        } catch (JOSEException e) {
            logger.error("context",e);
        }
        return token;
    }

    //生成一个token
    public String createToken(Map<String, Object> payloadMap) throws JOSEException {
        //先建立一个头部Header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));
        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);
        //签名
        jwsObject.sign(jwsSigner);
        //生成token
        return jwsObject.serialize();
    }


    //解析一个token
    public JSONObject verifyToken(String token) throws ParseException, JOSEException {
        //解析token
        JWSObject jwsObject = JWSObject.parse(token);
        //获取到载荷
        Payload payload = jwsObject.getPayload();
        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);
        JSONObject jsonObject;
        if (!jwsObject.verify(jwsVerifier)) {
            throw new BusinessException(ResultEnums.TOKEN_PARSE_FAIL);
        } else {
            jsonObject = payload.toJSONObject();
            Long expTime = (Long) jsonObject.get("exp");
            long nowTime = new Date().getTime();
            //判断是否过期
            if (nowTime > expTime) {
                throw new BusinessException(ResultEnums.TOKEN_EXPIRE);
            }
        }
        return jsonObject;
    }

    public static int getTimeLimit() {
        return TIME_LIMIT;
    }
}
