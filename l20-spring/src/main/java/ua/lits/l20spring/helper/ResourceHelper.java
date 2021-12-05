package ua.lits.l20spring.helper;

import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceHelper {

    /**
     * Reads spring resource by line and returns result as List of strings
     * @param resource Source
     * @return Result
     */
    public static List<String> readAsList(Resource resource) {
        List<String> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream())
        )) {
            while(reader.ready()) {
                result.add(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
