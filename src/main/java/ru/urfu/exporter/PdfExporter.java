package ru.urfu.exporter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.nio.file.Path;

/**
 * Экспортёр текста в PDF.
 */
public class PdfExporter implements Exporter {

    @Override
    public void export(Path outputPath, String content)
            throws DocumentException, java.io.IOException {

        try (FileOutputStream outputStream = new FileOutputStream(outputPath.toString())) {
            Document pdf = new Document();
            PdfWriter.getInstance(pdf, outputStream);

            pdf.open();
            pdf.add(new Paragraph(content));
            pdf.close();
        }
    }
}
