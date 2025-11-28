package ru.urfu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import ru.urfu.document.Document;
import ru.urfu.document.DocumentService;
import ru.urfu.utils.ExportService;
import ru.urfu.utils.ImportService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Основной класс консольного приложения.
 * Реализует ввод команд и взаимодействие с сервисами.
 */
@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    private final ImportService importService;

    private final ExportService exportService;

    private final DocumentService documentService;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ConsoleApp(
            ImportService importService, DocumentService documentService, ExportService exportService) {
        this.importService = importService;
        this.documentService = documentService;
        this.exportService = exportService;
    }

    /**
     * Точка входа приложения.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("=== Консольное приложение ===");

        while (true) {
            System.out.println("\nКоманды: import, list, create, export, exit");
            System.out.print("> ");
            String cmd = scanner.nextLine().trim();

            switch (cmd) {
                case "create" -> createDocument();
                case "import" -> importDocument();
                case "list" -> listDocuments();
                case "export" -> exportDocument();
                case "exit" -> {
                    return;
                }
                default -> System.out.println("Неизвестная команда");
            }
        }
    }

    /**
     * Создаёт документ через ввод данных в консоли.
     * Сначала пользователь вводит имя, затем — содержимое документа.
     * Ввод содержимого продолжается до пустой строки.
     */
    private void createDocument() {
        System.out.print("Введите имя документа: ");
        String name = scanner.nextLine().trim();

        System.out.println("Введите содержимое документа (пустая строка — завершить ввод):");

        StringBuilder content = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break; // окончание ввода
            content.append(line).append(System.lineSeparator());
        }
        documentService.addDocument(new Document(name, content.toString()));
        System.out.println("Документ создан и сохранён в памяти.");
    }

    /**
     * Выполняет импорт документа.
     */
    private void importDocument() {
        System.out.print("Введите путь к txt файлу: ");
        String path = scanner.nextLine();

        try {
            Document document = importService.importTxt(path);
            documentService.addDocument(document);
        } catch (IOException e) {
            System.out.println("Ошибка импорта: " + e.getMessage());
        }
    }

    /**
     * Выводит список загруженных документов.
     */
    private void listDocuments() {
        List<Document> documents = documentService.list();
        if (documents.isEmpty()) {
            System.out.println("Документов нет");
            return;
        }
        int i = 0;
        for (Document doc : documents) {
            System.out.println(i + ": " + doc.name());
            i++;
        }
    }

    /**
     * Выполняет экспорт документа.
     */
    private void exportDocument() {
        System.out.print("Введите номер документа: ");
        int index = Integer.parseInt(scanner.nextLine());

        Optional<Document> documentOptional = documentService.getDocument(index);
        if (documentOptional.isEmpty()) {
            System.out.println("Нет документа с таким номером.");
            return;
        }

        Document document = documentOptional.get();

        System.out.print("Введите формат (txt/pdf): ");
        String format = scanner.nextLine().trim().toLowerCase();

        try {
            exportService.export(document, format);
            System.out.println("Экспорт выполнен!");
        } catch (Exception e) {
            System.out.println("Ошибка экспорта: " + e.getMessage());
        }
    }
}