package com.libraryapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 5)
    private String ticketCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private Boolean isReturned = false;

    private LocalDateTime borrowedAt;

    @PrePersist
    protected void onCreate() {
        this.borrowedAt = LocalDateTime.now();
    }
}
