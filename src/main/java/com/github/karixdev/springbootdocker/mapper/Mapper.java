package com.github.karixdev.springbootdocker.mapper;

import java.util.List;

public interface Mapper<T, E> {
    E map(T t);
    List<E> map(List<T> ts);
}
