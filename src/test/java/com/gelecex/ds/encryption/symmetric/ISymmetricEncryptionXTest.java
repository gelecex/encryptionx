package com.gelecex.ds.encryption.symmetric;

import com.gelecex.ds.encryption.symmetric.exception.SymmetricEncryptionExceptionX;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by obetron on 7.10.2018
 */
public class ISymmetricEncryptionXTest {

    public SymmetricEncryptionX symmetricEncryption = new ISymmetricEncryptionX();
    private final String defaultKey = "1234567890123456";
    private final Charset defaultEncoding = StandardCharsets.UTF_8;

    @Test
    public void encryptDataAndKeyTest() throws SymmetricEncryptionExceptionX {
        byte[] testDataToBeEncrypting;
        testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
        Assert.assertNotNull(symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey));
    }

    @Test
    public void encryptDataKeyAndCipher() throws SymmetricEncryptionExceptionX {
        byte[] testDataToBeEncrypting;
        testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
        Assert.assertNotNull(symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey, CipherXType.AES_CBC_PKCS5Padding));
    }

    @Test
    public void encryptDataKeyCipherAndAlgorithm() throws SymmetricEncryptionExceptionX {
        byte[] testDataToBeEncrypting;
        testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
        Assert.assertNotNull(symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey, CipherXType.AES_CBC_PKCS5Padding, SymmetricAlgorithmX.AES));
    }

    @Test
    public void encryptDataWithCBC() throws SymmetricEncryptionExceptionX {
        byte[] testDataToBeEncrypting;
        testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
        Assert.assertNotNull(symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey, CipherXType.AES_CBC_PKCS5Padding, SymmetricAlgorithmX.AES));
    }

    @Test
    public void encryptWithAWrongKeySize() {
        Assert.assertThrows(InvalidKeyException.class, () -> {
            byte[] testDataToBeEncrypting;
            String wrongKeySize = "123456";
            testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
            symmetricEncryption.encrypt(testDataToBeEncrypting, wrongKeySize);
        });
    }

    @Test
    public void encryptWithAWrongEncoding() {
        Assert.assertThrows(UnsupportedEncodingException.class, () -> {
            byte[] testDataToBeEncrypting;
            String wrongEncoding = "UTF-12";
            testDataToBeEncrypting = "gelecex.com".getBytes(wrongEncoding);
            symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey, CipherXType.AES_CBC_PKCS5Padding, SymmetricAlgorithmX.AES);
        });
    }

    @Test
    public void encryptWithCBCNOPadding()
            throws SymmetricEncryptionExceptionX {
        byte[] testDataToBeEncrypting;
        testDataToBeEncrypting = "gelecex.com".getBytes(defaultEncoding);
        Assert.assertNotNull(symmetricEncryption.encrypt(testDataToBeEncrypting, defaultKey, CipherXType.AES_CBC_NOPadding, SymmetricAlgorithmX.AES));
    }

}