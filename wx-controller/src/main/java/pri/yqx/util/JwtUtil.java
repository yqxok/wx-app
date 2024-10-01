package pri.yqx.util;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

import java.util.Date;
import java.util.Map;


public class JwtUtil {
    private static String SIGNATURE = "token!@#$%^7890";

    /**
     * 生成token
     * @param map //传入payload
     * @return 返回token
     */
    public static String getToken(Map<String,String> map){
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.SECOND,7);
        builder.withExpiresAt(new Date(new Date().getTime()+1000L*3600*24*7));
        return builder.sign(Algorithm.HMAC256(SIGNATURE)).toString();
    }

    /**
     * 验证token
     * @param token
     */
    public static DecodedJWT verify(String token){
       return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }



}


