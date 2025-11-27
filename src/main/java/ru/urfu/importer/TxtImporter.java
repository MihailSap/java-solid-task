package ru.urfu.importer;

import ru.urfu.document.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс, отвечающий за импорт TXT
 */
public class TxtImporter implements Importer {

    @Override
    public Document importDocument(String pathStr) throws IOException {
        Path path = Path.of(pathStr);
        if (!Files.exists(path)) {
            throw new IOException("Файл не найден: " + path);
        }

        String content = Files.readString(path);
        System.out.println("Документ импортирован: " + path.getFileName());
        return new Document(path.getFileName().toString(), content);
    }
}
