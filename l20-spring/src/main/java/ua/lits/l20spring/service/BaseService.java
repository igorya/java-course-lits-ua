package ua.lits.l20spring.service;

import org.modelmapper.ModelMapper;
import ua.lits.l20spring.exception.NotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Common functionality for service classes. Can be used as parent
 */
abstract class BaseService {

    final ModelMapper modelMapper;
    final Validator validator;

    BaseService(ModelMapper modelMapper, Validator validator) {
        this.modelMapper = modelMapper;
        this.validator = validator;
    }

    /**
     * Transform collection with ModelMapper to the collection of another type
     *
     * @param srcCollection Collection of source entities
     * @param destClass class for Collection of destination type
     * @param supplier Exception supplier
     * @param <T> source type
     * @param <R> destination (return) type
     * @return Collection
     */
    <T, R> Collection<R> transformCollection(Collection<T> srcCollection, Class<R> destClass, Supplier<? extends NotFoundException> supplier) {
        if (srcCollection.size() == 0) {
            throw supplier.get();
        }
        return srcCollection.stream()
                .map(element -> modelMapper.map(element, destClass))
                .collect(Collectors.toList());
    }

    /**
     * @param object Object to be validated
     * @param <T> Object type
     */
    <T> void validate(T object) {
        Set<ConstraintViolation<T>> cvSet = validator.validate(object);

        if (!cvSet.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> cv : cvSet) {
                sb.append(cv.getMessage()).append("; ");
            }
            throw new ConstraintViolationException("Validation errors: "+ sb, cvSet);
        }
    }
}
