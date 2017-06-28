package com.nm.util;
/**
 * Created by xlh on 2016/10/20.
 */
public enum SDKConstants {
    // 商户 私钥
    PRIVATE_KEY("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI5ad0zEHlEnQIddAtwuMZIBOpuEwGz4OrAOV8LaRcUyt1oT08clM1XoHKEvBaO8N630ygqXyySbtkms8CrrNL2/xFvdFMzSeaQye6Pllj1KwOYsMBn3v2eCuEfa7jCIWcKotuRZ1irQGo34sLkLGIr4Oysq1DBff/YOl5sdDj0FAgMBAAECgYEAiV9r+TzZNN94Oijb9bbuR0PAVwwI1h/GItf0gSk6Y07J+bdgi7GulJxl5HYILzYXVVwBDa/mbfGGPBsTuZc5UN4zQqbblE5TeE0lP5aNKZjoyfw0oggsZaLHJ5XnY7AEO0jWfQIb7puCrWu6TvSVYMkwIDjsOmE22igDugYadJECQQDg5AfjlNgi2eUfOVJ/JRg0GLg/GfpKxbm1D5KnSBk923STdCH/pHhx29nr1VTxVH+y3P4yMAGcVslGxOIN1Ft/AkEAoguSSUPfmOiYruphWW/2WiYOAlW/DyY2uFDUCrSLF+E8hwNtfdPI2AfHX3Mf6FUg3BavgD3GmR+TeFlL39k5ewJAESPwIf1fN9DJsMaKrhwiqx3TNSQCup41WEJ2l/9bxSYrzW1FhhmAAJWUF3BsB/S7wuMrEKIuDT9kGdu5qvorewJBAJbi73QJH0riSkmVNNBy0JFgnerOrPkOPCWkHc1aD6pMsG+HYYyoh3ugwqS+LhfILLDC7uganbI3FLoba+sj+cECQHdzZ8C9If2Ez/w2U1OddJ3rvVYLfB+TqNHTVjJ4KaH65eAqiyecToxlRDfOle4k6S6UNKjQhPtD6L/Wykfcjtk="),
    // 商户  公钥
    PUBLIC_KEY("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOWndMxB5RJ0CHXQLcLjGSATqbhMBs+DqwDlfC2kXFMrdaE9PHJTNV6ByhLwWjvDet9MoKl8skm7ZJrPAq6zS9v8Rb3RTM0nmkMnuj5ZY9SsDmLDAZ979ngrhH2u4wiFnCqLbkWdYq0BqN+LC5CxiK+DsrKtQwX3/2DpebHQ49BQIDAQAB"),
    //平台公钥
    PLATFORM_PUB_KEY("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBXB4kmLH1i3BbwyHPzi2MqACrvgHQWq0q5WsQtryYLQedaENoDU8q86A5+x4YJU9JKBgPaP8pYlgEpQ1+TNVIqrGpJc7/lCStnC+/Jevi5Y/eS9rTxkkb0OLXL6eBlNGkGmwO7jUrhIptlmi8n8jdvnS5+68ledi6yjBXP8W+iQIDAQAB");

    private String value;

    private SDKConstants(String value) {
        this.value = value;
    }

    public String value(){
        return value;
    }


}
