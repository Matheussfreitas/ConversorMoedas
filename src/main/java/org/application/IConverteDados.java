package org.application;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}