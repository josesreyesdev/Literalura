package com.jsrdev.literalura.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> genericClass);
}
