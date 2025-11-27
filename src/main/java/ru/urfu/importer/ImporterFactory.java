package ru.urfu.importer;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Класс, создающий и возвращающий {@link Importer} нужного формата
 */
@Component
public class ImporterFactory {

    private final Map<String, Importer> importers;

    /**
     * Блок для определения классов импорта
     */
    public ImporterFactory() {
        importers = Map.of(
                "txt", new TxtImporter()
        );
    }

    /**
     * Получение класса импорта для нужного формата
     */
    public Importer getImporter(String format) {
        return importers.get(format);
    }
}
