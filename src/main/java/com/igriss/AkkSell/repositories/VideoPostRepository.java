package com.igriss.AkkSell.repositories;

import com.igriss.AkkSell.entities.VideoPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoPostRepository extends JpaRepository<VideoPost, Long> {
}