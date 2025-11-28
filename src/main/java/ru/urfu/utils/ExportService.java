package ru.urfu.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.urfu.document.Document;
import ru.urfu.exporter.Exporter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Сервис для экспорта файлов
 */
@Service
public class ExportService {

    private static final Path OUTPUT_DIR = Path.of(System.getProperty("user.home"), "lessonSOLID");

    private final List<Exporter> exporters;

    /**
     * Конструктор для определения {@code exporters}
     */
    @Autowired
    public ExportService(List<Exporter> exporters) {
        this.exporters = exporters;
    }

    /**
     * Экспорт файла в нужном формате
     */
    public void export(Document document, String format) throws Exception {
        Exporter exporter = null;
        for(Exporter e : exporters) {
            if(e.getFormat().equals(format)) {
                exporter = e;
            }
        }
        if(exporter == null){
            throw new IllegalArgumentException("Неизвестный формат: " + format);
        }

        Files.createDirectories(OUTPUT_DIR);
        Path outputPath = OUTPUT_DIR.resolve(document.name() + "." + format);
        exporter.export(outputPath, document.content());
    }
}
