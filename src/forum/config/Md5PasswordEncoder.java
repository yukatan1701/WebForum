package forum.config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.password.PasswordEncoder;

import forum.User;

public class Md5PasswordEncoder implements PasswordEncoder {
	
	public static String getMD5(String password) {
		String result = password;
		if (password != null) {
			MessageDigest mda;
			try {
				mda = MessageDigest.getInstance("MD5");
				mda.update(password.getBytes());
			    BigInteger hash = new BigInteger(1, mda.digest());
			    result = hash.toString(16);
			    while (result.length() < 32) { // 40 for SHA-1
			        result = "0" + result;
			    }
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	@Override
    public String encode(CharSequence charSequence) {
        return getMD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return getMD5(charSequence.toString()).equals(s.substring("{noop}".length()));
    }

}
