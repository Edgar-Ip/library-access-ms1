package com.example.library_access_ms1.service;


import com.example.library_access_ms1.config.LibraryProperties;
import com.example.library_access_ms1.entity.LibraryUser;
import com.example.library_access_ms1.exception.BusinessException;
import com.example.library_access_ms1.repository.LibraryUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LibraryAccessService {

    private final LibraryUserRepository repository;
    private final LibraryProperties properties;

    public LibraryAccessService(LibraryUserRepository repository,
                                LibraryProperties properties) {
        this.repository = repository;
        this.properties = properties;
    }

    public void admitUserIntoLibrary(String userCode) {
        validateUserCode(userCode);
        validateCapacity();
        validateUserNotAlreadyInside(userCode);

        LibraryUser user = new LibraryUser();
        user.setUserCode(userCode);
        user.setEntryTime(LocalDateTime.now());

        repository.save(user);
    }

    public void releaseUserFromLibrary(String userCode) {
        if (!repository.existsById(userCode)) {
            throw new BusinessException(
                    HttpStatus.NOT_FOUND,
                    "El usuario no existe en la biblioteca"
            );
        }
        repository.deleteById(userCode);
    }

    public List<LibraryUser> getCurrentUsers(boolean applyDelay) {
        applyArtificialDelayIfRequested(applyDelay);
        return repository.findAll();
    }

    /* ================= VALIDACIONES ================= */

    private void validateUserCode(String userCode) {
        if (!userCode.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8}$")) {
            throw new BusinessException(
                    HttpStatus.UNPROCESSABLE_ENTITY,
                    "El código debe tener 8 caracteres alfanuméricos"
            );
        }
    }

    private void validateCapacity() {
        if (repository.count() >= properties.getMaxCapacity()) {
            throw new BusinessException(
                    HttpStatus.UNAUTHORIZED,
                    "Aforo máximo alcanzado"
            );
        }
    }

    private void validateUserNotAlreadyInside(String userCode) {
        if (repository.existsById(userCode)) {
            throw new BusinessException(
                    HttpStatus.UNAUTHORIZED,
                    "El usuario ya está dentro de la biblioteca"
            );
        }
    }

    private void applyArtificialDelayIfRequested(boolean applyDelay) {
        if (!applyDelay) return;

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new BusinessException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al aplicar delay"
            );
        }
    }
}