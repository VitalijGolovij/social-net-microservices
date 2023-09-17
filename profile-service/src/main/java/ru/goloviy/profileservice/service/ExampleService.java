package ru.goloviy.profileservice.service;

import org.springframework.data.domain.Example;

public interface ExampleService<T, V> {
    Example<T> getExample(V v);
}
