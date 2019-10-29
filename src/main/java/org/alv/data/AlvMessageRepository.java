package org.alv.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlvMessageRepository extends JpaRepository<AlvMessage, Long> {
    List<AlvMessage> findByIdRequest(Long idRequest);
}