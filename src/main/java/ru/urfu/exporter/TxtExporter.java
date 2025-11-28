package ru.urfu.exporter;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Экспортёр текста в TXT.
 */
public class TxtExporter implements Exporter {

    @Override
    public void export(Path outputPath, String content) throws Exception {
        Files.writeString(outputPath, content);
    }

    @Override
    public String getFormat() {
        return "txt";
    }
}
