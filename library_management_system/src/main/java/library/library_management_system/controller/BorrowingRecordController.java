package library.library_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import library.library_management_system.entity.Book;
import library.library_management_system.entity.BorrowingRecord;
import library.library_management_system.entity.Patron;
import library.library_management_system.service.BookService;
import library.library_management_system.service.BorrowingRecordService;
import library.library_management_system.service.PatronService;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/borrow")
public class BorrowingRecordController {
    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    @PostMapping("/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Optional<Book> bookOpt = bookService.getBookById(bookId);
        Optional<Patron> patronOpt = patronService.getPatronById(patronId);

        if (bookOpt.isEmpty() || patronOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(bookOpt.get());
        borrowingRecord.setPatron(patronOpt.get());
        borrowingRecord.setBorrowDate(LocalDate.now());

        BorrowingRecord savedRecord = borrowingRecordService.saveBorrowingRecord(borrowingRecord);
        return ResponseEntity.ok(savedRecord);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowingRecord> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Optional<BorrowingRecord> recordOpt = borrowingRecordService.getAllBorrowingRecords().stream()
                .filter(record -> record.getBook().getId().equals(bookId) && record.getPatron().getId().equals(patronId) && record.getReturnDate() == null)
                .findFirst();

        if (recordOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BorrowingRecord record = recordOpt.get();
        record.setReturnDate(LocalDate.now());
        BorrowingRecord updatedRecord = borrowingRecordService.saveBorrowingRecord(record);
        return ResponseEntity.ok(updatedRecord);
    }
}