package ru.urfu.exporter;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Класс, создающий и возвращающий {@link Exporter} нужного формата
 */
@Component
public class ExporterFactory {

    private final Map<String, Exporter> exporters;

    /**
     * Конструктор для определения существующих экспортеров с форматами
     */
    public ExporterFactory() {
        exporters = Map.of(
                "txt", new TxtExporter(),
                "pdf", new PdfExporter()
        );
    }

    /**
     * Получение экспортера для нужного формата
     */
    public Exporter getExporterByFormat(String format) {
        return exporters.get(format);
    }
}
