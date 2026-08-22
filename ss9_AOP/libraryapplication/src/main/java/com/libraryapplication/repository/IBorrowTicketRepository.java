package com.libraryapplication.repository;

import com.libraryapplication.entity.BorrowTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBorrowTicketRepository extends JpaRepository<BorrowTicket, Long> {
    Optional<BorrowTicket> findByTicketCode(String ticketCode);
}
