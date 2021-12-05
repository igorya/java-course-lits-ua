package ua.lits.l20spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ResultDumperServiceImpl implements ResultDumperService {

    private static final Logger logger = LoggerFactory.getLogger(ResultDumperService.class);

    @Override
    public void error(String message) {
        logger.error(message);
        logger.error("");
    }

    @Override
    public <T> void dump(Iterable<T> result, String fieldName, String searchValue) {
        dump(result, makeTitlePlaceholder(fieldName, searchValue));
    }

    @Override
    public <T> void dump(Iterable<T> result, String placeholder) {
        logger.info(makeTitle(placeholder));
        int counter = 0;
        for (T entity : result) {
            logger.info(entity.toString());
            counter++;
        }
        logger.info("The number of records is {}", counter);
        logger.info("");
    }

    @Override
    public <T> void dump(T entity, String fieldName, String searchValue) {
        logger.info(makeTitle(makeTitlePlaceholder(fieldName, searchValue)));
        logger.info(entity.toString());
        logger.info("");
    }

    @Override
    public <T> void dump(T entity, String fieldName, Long searchValue) {
        dump(entity, fieldName, String.valueOf(searchValue));
    }

    private String makeTitlePlaceholder(String fieldName, String searchValue) {
        return String.format("%s='%s'", fieldName, searchValue);
    }

    private String makeTitle(String placeholder) {
        return "Search result for "+ placeholder +":";
    }
}
