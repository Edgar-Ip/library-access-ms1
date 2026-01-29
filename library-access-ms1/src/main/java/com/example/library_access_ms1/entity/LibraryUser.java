package com.example.library_access_ms1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name= "library_users")
public class LibraryUser {

    @Id
    @Column(length = 8, nullable = false, unique = true)
    private String userCode;

    private LocalDateTime entryTime;

    public String getUserCode(){
        return userCode;
    }

    public void setUserCode(String userCode){
        this.userCode = userCode;

    }

    public LocalDateTime getEntryTime(){
        return  entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime){
        this.entryTime = entryTime;
    }

}
