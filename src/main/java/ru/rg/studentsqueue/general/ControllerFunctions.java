package ru.rg.studentsqueue.general;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.rg.studentsqueue.general.types.Func2Args;

import java.util.function.Function;

/**
 * A service class containing controller functions for processing requests.
 **/
@Service
@RequiredArgsConstructor
public class ControllerFunctions {
    /**
     * Search for an object by the specified property.
     *
     * @param <ObjectType> type of the returned object
     * @param <PropType> the type of property that is being searched for
     * @param property property value
     * @param findFunction object search function by specified property
     * @return found object or error 404 if the object is not found
     */
    public <ObjectType, PropType> ResponseEntity<ObjectType> findBy(
            PropType property,
            Function<PropType, ObjectType> findFunction
    ) {
        ObjectType object = findFunction.apply(property);
        if (object != null) {
            return ResponseEntity.ok(object);
        }

        return ResponseEntity.status(404).body(null);
    }

    /**
     * Changing an object by the specified property and object.
     *
     * @param <ObjectType>   type of returned object
     * @param <PropType>     the type of property by which the change is made
     * @param property       property value
     * @param object         the object that needs to be changed
     * @param changeFunction the function of changing an object by the specified property and object
     * @return modified object or error 404 if the object is not found
     */
    public <ObjectType, PropType> ResponseEntity<ObjectType> change(
            PropType property,
            ObjectType object,
            Func2Args<PropType, ObjectType, ObjectType> changeFunction
    ) {
        ObjectType responseObj = changeFunction.apply(property, object);
        if (responseObj != null) {
            return ResponseEntity.ok(responseObj);
        }

        return ResponseEntity.status(404).body(null);
    }
}
