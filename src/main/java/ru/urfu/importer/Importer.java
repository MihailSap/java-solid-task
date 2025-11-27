package ru.urfu.importer;

import ru.urfu.document.Document;

import java.io.IOException;

/**
 * Интерфейс для класса, реализующего импорт файла
 */
public interface Importer {

    /**
     * Импортирует файл в виде {@link Document}
     * @param pathStr путь к файлу
     * @throws IOException если файл не найден или не удаётся прочитать
     */
    Document importDocument(String pathStr) throws IOException;
}
