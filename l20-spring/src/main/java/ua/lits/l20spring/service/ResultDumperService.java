package ua.lits.l20spring.service;

public interface ResultDumperService {

    void error(String message);

    <T> void dump(Iterable<T> result, String fieldName, String searchValue);

    <T> void dump(Iterable<T> result, String message);

    <T> void dump(T entity, String fieldName, String searchValue);

    <T> void dump(T entity, String fieldName, Long searchValue);
}
