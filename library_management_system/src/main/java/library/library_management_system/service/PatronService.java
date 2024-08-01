package library.library_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.library_management_system.entity.Patron;
import library.library_management_system.repository.PatronRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatronService {
    
    @Autowired
    private PatronRepository patronRepository;

    @Transactional(readOnly = true)
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @Transactional
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Transactional
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}
