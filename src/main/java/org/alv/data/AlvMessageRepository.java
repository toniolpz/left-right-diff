package org.alv.data;

import org.alv.entity.AlvMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlvMessageRepository extends JpaRepository<AlvMessage, Long> {
    AlvMessage findByIdRequestAndSide(Long idRequest, String side);
}