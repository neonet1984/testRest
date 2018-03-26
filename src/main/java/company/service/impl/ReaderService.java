package company.service.impl;

import company.service.IReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Этот класс читает файл
 */
public class ReaderService implements IReader {
    @Override
    public String read(String pathFile) throws IOException {
         return FileUtils.readFileToString(new File(pathFile),"UTF-8");
    }
}

