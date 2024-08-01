package library.library_management_system.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import library.library_management_system.entity.BorrowingRecord;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
}
