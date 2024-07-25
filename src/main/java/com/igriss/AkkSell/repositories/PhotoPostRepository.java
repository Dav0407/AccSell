package com.igriss.AkkSell.repositories;

import com.igriss.AkkSell.entities.PhotoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoPostRepository extends JpaRepository<PhotoPost, Long> {
}