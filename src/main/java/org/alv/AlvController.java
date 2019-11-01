package org.alv;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Base64;

import org.alv.data.AlvMessageRepository;
import org.alv.entity.AlvMessage;
import org.alv.entity.AlvRequest;
import org.alv.entity.DiffResponse;

@RestController
public class AlvController {
    private final AlvMessageRepository repository;

    AlvController(AlvMessageRepository repository) {
        this.repository = repository;
    }

    // Handle Left Endpoint
    @PostMapping("/v1/diff/{id}/left")
    public AlvMessage createLeft(@PathVariable("id") Long id, @RequestBody AlvRequest alvRequest) {
        AlvMessage leftRightMessage = new AlvMessage(id, "left", alvRequest.getMessage());
        return repository.save(leftRightMessage);
    }

    // Handle Right Endpoint
    @PostMapping("/v1/diff/{id}/right")
    AlvMessage createRight(@PathVariable("id") Long id, @RequestBody AlvRequest alvRequest) {
        AlvMessage leftRightMessage = new AlvMessage(id, "right", alvRequest.getMessage());
        return repository.save(leftRightMessage);
    }

    // Handle Diff endpoint
    @GetMapping("/v1/diff/{id}")
    DiffResponse diff(@PathVariable("id") Long id) {
        AlvMessage leftMessage = repository.findByIdRequestAndSide(id, "left");
        AlvMessage rightMessage = repository.findByIdRequestAndSide(id, "right");

        byte[] bytesLeft = Base64.getDecoder().decode(leftMessage.getMessage().getBytes());
        byte[] bytesRight = Base64.getDecoder().decode(rightMessage.getMessage().getBytes());

        DiffResponse response = new DiffResponse();
        response.setIdRequest(id);
        if (Arrays.equals(bytesLeft, bytesRight)) {
            // Status 200 is for successful comparison between binary data
            response.setStatus(200);
            response.setMessage("The messages for request " + id + " are equals");
        } else if (Integer.compare(bytesLeft.length, bytesRight.length) != 0) {
            // Status 201 is for successful comparison between sizes
            response.setStatus(201);
            response.setMessage("The size of the messages for request " + id + " are equals");
        } else {
            // Status 202 is for successful response with no similiar sizes nor data
            response.setStatus(202);
            response.setMessage("The messages are different each other. The left lenght is " + bytesLeft.length
                    + " The right lenght is " + bytesRight.length);
        }

        return response;
    }
}