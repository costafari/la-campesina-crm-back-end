package sv.com.mksoft.lacampesina.api.auth;

public class JwtConfig {
	
	public static final String LLAVE_SECRETA = "__$la#%campesina1#$";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApebNsefLv6+n2sslyxVL\n" + 
			"4+qG4OuIprMMzENCcPf5qRepxaC7yP/6PG946vMnpUoHl/2Jp/lcgb3qcAP4h4jN\n" + 
			"IbBG1yTkFONG/vIwNlQ8ti+2qeOy30HtA3MxI4NG/voM6Sf0+50nA9rJq17Nqrem\n" + 
			"CNHx2W5Onyt9Vz1/ZW3maHnPGbK1tmHNjADNVTzQmzSdc7eGLuFHs0pwNXpB6ZfN\n" + 
			"ToAh0jKqQPYBxWu5MH/PlOpJ8LbRPAzaXdmhZ801aJsAueqvuI9d6WHoqNH/hkzx\n" + 
			"37ZedJjnaOJQ5KEnd0uo7iCDzQat2117IPT0CI4NbUMlUbg5dRDEcMT6Eb14ncIm\n" + 
			"twIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEApebNsefLv6+n2sslyxVL4+qG4OuIprMMzENCcPf5qRepxaC7\n" + 
			"yP/6PG946vMnpUoHl/2Jp/lcgb3qcAP4h4jNIbBG1yTkFONG/vIwNlQ8ti+2qeOy\n" + 
			"30HtA3MxI4NG/voM6Sf0+50nA9rJq17NqremCNHx2W5Onyt9Vz1/ZW3maHnPGbK1\n" + 
			"tmHNjADNVTzQmzSdc7eGLuFHs0pwNXpB6ZfNToAh0jKqQPYBxWu5MH/PlOpJ8LbR\n" + 
			"PAzaXdmhZ801aJsAueqvuI9d6WHoqNH/hkzx37ZedJjnaOJQ5KEnd0uo7iCDzQat\n" + 
			"2117IPT0CI4NbUMlUbg5dRDEcMT6Eb14ncImtwIDAQABAoIBAA+3sys0u7/4Y12+\n" + 
			"O2FnLh50vLyYdATfF8pOYQAU7klP3ZzEoqXjYpDROtJ/oPTu85M5yQ3p+RKtrIVa\n" + 
			"SvSESHL1oXCGpTk5YyOKTHkJOAtGr/aehPbT5yGZi0FDEbncdNauG26vL+AUnWCo\n" + 
			"BbIz3f4d7dzcr4Qy+ZgHUoeIhvjE0oCA46IIKMAxsxhs1dRJG5IFeA/r3pxEYEg1\n" + 
			"f4h23n2aINFJ4v5joZzOdRRvRUDbuLP/YQ+7xBlmggeDrGHhpeAnqZXBrmunAUvG\n" + 
			"tfXweyJaO9bpyouM6iCh27M/ktYoRBqdP+ZRER8dB9ewqY7QUMBs/6Xfn+a/5KLw\n" + 
			"pm3PC4ECgYEA1VNUwU7bFjvVegFPDxZ+CxYV81YN2qnZvEi/oTD0h4w9DO48lx1W\n" + 
			"ql9rBobasLAYAJCKKhv+icfNTeALbF/4m6j7jfEA2tunuNlLg7UHIGFP5M9X9JCe\n" + 
			"FCG9Xd7XaxwsSwPSYkM/MSFDGEQLitxSO435dz0KYSnJ713RiaRL1UECgYEAxxbV\n" + 
			"MAbj/QTyQCaJboQ4k82xpmpUvnYf08WRslQE0123dTqZQNo019A1pHvdXzVDW9E5\n" + 
			"mMW6IQpgIU+H5ucnahArdn2pI7cLFURp1t5oM5aBRF6kIVazkqm9Y2F7iSjH+IM4\n" + 
			"uhwZMxvENTVBG7e+gZX9V+6VDfS7/AgaOFiFJfcCgYBGemWCCB9x1QrOaChKf8/c\n" + 
			"sctGvaOOHpeFETPeeWIQdlClOQBjnJnJaATf/yW5QjRr21UKXH1X398AX4jmoT5t\n" + 
			"jwKs/nad1/wtofzE4sYb+IqAudABSNYxKIR7wufAx2oKzpRvSnBSNv8HwVNe6TNx\n" + 
			"4ZINxujq+PdyUbislM9JAQKBgQCLOE9Kb3cc50mUTJ6MVw/NY+jX68FpEud8ysrw\n" + 
			"vkpFmGKDKsAilrSfOi1Y4XlTa8a3vV6ek55uHnhMsvXhJUHtrHMIQCV4ITcx3w54\n" + 
			"QDzz3kp1Cg2SdrPKWsJibbfamVcFVJXlp2KD1+XkI2JtRJeGvOFVjTRUdxDB1NSp\n" + 
			"FFW/zwKBgQCxlq9CtZdJ4p3HZyWd+QbQjfEtFVfnfLtMsz1I6AgW0LaZz2xB7bH5\n" + 
			"BcvXkkILPYGEZYsjVfVHlohLPHCz/xWLY43YuALStYmjQfymW8uRqsGPYk0MnWih\n" + 
			"UVmqJ4PQ2bCCAKTyx5Oq75j9wVmXKDIeSnu5vyQvTLponUoe9wBDzA==\n" + 
			"-----END RSA PRIVATE KEY-----";

}