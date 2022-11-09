package com.automation.io;

import com.automation.model.ICsvModel;
import com.automation.service.CsvColumnMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public final class CsvUtil {
    private final static String COMMA_DELIMITER = ",";

    @SneakyThrows(IOException.class)
    public static ICsvModel initializeTestData(final String path) {
        final List<List<String>> records = new ArrayList<>();

        final BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            final String[] values = line.split(COMMA_DELIMITER);
            records.add(Arrays.asList(values));
        }

        return CsvColumnMapper.map(records);
    }
}
