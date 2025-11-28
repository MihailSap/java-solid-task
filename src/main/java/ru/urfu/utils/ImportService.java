package ru.urfu.utils;

import org.springframework.stereotype.Service;
import ru.urfu.document.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Сервис для импорта файлов.
 */
@Service
public class ImportService {

    /**
     * Импортирует текстовый файл и возвращает в виде {@link Document}
     */
    public Document importTxt(String pathStr) throws IOException {
        Path path = Path.of(pathStr);

        if (!Files.exists(path)) {
            throw new IOException("Файл не найден: " + path);
        }

        String content = Files.readString(path);
        return new Document(path.getFileName().toString(), content);
    }
}
