package ru.urfu.exporter;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Интерфейс для класса, реализующего экспорт файла
 */
public interface Exporter {

    /**
     * Экспорт файла в нужный формат
     * @param outputPath путь для сохранения pdf файла
     * @param content    текстовое содержимое
     * @throws DocumentException   если произошла ошибка PDF генерации
     * @throws IOException если не удалось записать файл
     */
    void export(Path outputPath, String content) throws Exception;

    /**
     * Возвращает формат экспортера
     */
    String getFormat();
}
