package edu.labIV.cfg;

public enum ConfigKey {

    DB_USER("user"),
    DB_PASS("password"),
    DB_URL("url"),
    MAIL_USER("user"),
    MAIL_PASS("password"),
    MAIL_PROTOCOL("protocol"),
    MAIL_SERVER("server"),
    MAIL_PORT("port"),
    ENC_SALT("salt"),
    ENC_ITERATIONS("iterations"),
    ENC_KEY_LENGTH("key_length");

    private final String key;

    ConfigKey(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }
}
