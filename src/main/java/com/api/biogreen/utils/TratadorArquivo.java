package com.api.biogreen.utils;

public class TratadorArquivo {

    public static String obterExtensao(String nomeArquivo) {
        if (nomeArquivo == null || !nomeArquivo.contains(".")) return "";
        return nomeArquivo.substring(nomeArquivo.lastIndexOf("."));
    }
}
