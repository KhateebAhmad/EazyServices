package edu.nci.eazyserve.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.nci.eazyserve.constants.Constants;

public class PasswordEncryptionUtil {
	private static final Logger logger = LoggerFactory.getLogger(PasswordEncryptionUtil.class);

	/**
	 * Convert plan password text, hash it and then encrypt it using SHA-256
	 * hashing and Base64 encryption.
	 * 
	 * @param password
	 *            plain text password
	 * @return hashed and encrypted password
	 */
	public static String encrypt(String password){
		if(password == null){
			logger.warn("Given password cannot be empty.");
			return null;
		}
		logger.debug("Encrypting plain text password.");
		String encryptedPassword = null;
		try{
			MessageDigest digest = MessageDigest.getInstance(Constants.HASH_ALGORITHM);
	        byte[] hash = digest.digest(password.getBytes());
	        logger.debug("Message digest created using "+Constants.HASH_ALGORITHM+" algorithm.");
	        
	        Base64.Encoder encoder = Base64.getEncoder();
	        byte[] output =  encoder.encode(hash);
	        logger.debug("Plain text password is encrypted using Base64 encoder.");
	        
	        encryptedPassword = new String(output);	        
		} catch (NoSuchAlgorithmException ex){
			logger.error(ex.getMessage());
			ex.printStackTrace();
		}
		logger.debug("Encrypted password is "+encryptedPassword);
        return encryptedPassword;
	}
}
