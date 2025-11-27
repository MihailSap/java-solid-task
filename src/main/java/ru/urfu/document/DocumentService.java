package ru.urfu.document;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Сервис для управления документами. Позволяет импортировать файлы
 *  и хранит документы в оперативной памяти.
 */
@Service
public class DocumentService {

    private final List<Document> documents = new ArrayList<>();

    /**
     * Возвращает список всех импортированных документов.
     */
    public List<Document> list() {
        return Collections.unmodifiableList(documents);
    }

    /**
     * Возвращает документ по индексу.
     *
     * @param index индекс документа
     * @return Optional с документом или пустой Optional, если индекс неверен
     */
    public Optional<Document> getDocument(int index) {
        if (index < 0 || index >= documents.size()) {
            return Optional.empty();
        }
        return Optional.of(documents.get(index));
    }

    /**
     * Сохранение документа в памяти
     */
    public void addDocument(Document document) {
        documents.add(document);
    }
}