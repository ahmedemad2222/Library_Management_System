package library.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.library_management_system.entity.BorrowingRecord;
import library.library_management_system.repository.BorrowingRecordRepository;

import java.util.List;

@Service
public class BorrowingRecordService {
    
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Transactional(readOnly = true)
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Transactional
    public BorrowingRecord saveBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

}
