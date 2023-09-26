package cn.edu.zucc.jwt;

import com.auth0.jwt.interfaces.Claim;

public class PermitUtil {
    public static final int Admin = 3;
    public static final int User = 2;

    public static boolean TokenCheck(String token,int character){
        return (PermitUtil.getPermit(token) == character);
    }

    public static int getPermit (String token) {
        int role = 0;
        try {
            Claim claim = JwtUtil.verifyToken(token).get("Type");
            //System.out.println(claim+"in try");
            if (claim != null)  {
                //System.out.println("claim.asInt() is "+claim.asInt());
                role = claim.asInt();
            }
        } catch (Exception e) {
            System.out.println("ERROR "+e);
        }
        return role;
    }
}
