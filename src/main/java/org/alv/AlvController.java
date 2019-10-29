package org.alv;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.alv.data.AlvMessageRepository;
import org.alv.data.AlvMessage;

@RestController
class AlvController {
    private final AlvMessageRepository repository;

    AlvController(AlvMessageRepository repository) {
        this.repository = repository;
    }

    // Handle Left Endpoint
    @PostMapping("/v1/diff/{id}/left")
    AlvMessage createLeft(@PathVariable Long id, @RequestBody String message) {
        AlvMessage leftRightMessage = new AlvMessage(id, "left", message);
        return repository.save(leftRightMessage);
    }

    // Handle Right Endpoint
    @PostMapping("/v1/diff/{id}/right")
    AlvMessage createRight(@PathVariable Long id, @RequestBody String message) {
        AlvMessage leftRightMessage = new AlvMessage(id, "right", message);
        return repository.save(leftRightMessage);
    }

    // Handle Diff endpoint
    @GetMapping("/v1/diff/{id}")
    List<AlvMessage> diff(@PathVariable Long id) {
        return repository.findByIdRequest(id);
    }
}