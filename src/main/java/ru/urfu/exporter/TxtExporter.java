package ru.urfu.exporter;

import com.itextpdf.text.DocumentException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Экспортёр текста в TXT.
 */
public class TxtExporter implements Exporter {

    @Override
    public void export(Path outputPath, String content) throws DocumentException, IOException {
        Files.writeString(outputPath, content);
    }
}
