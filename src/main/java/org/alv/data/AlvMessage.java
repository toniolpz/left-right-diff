package org.alv.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class AlvMessage {

    private @Id @GeneratedValue Long id;
    private Long idRequest;
    private String side;
    private String message;

    public AlvMessage() {
    }

    public AlvMessage(Long idRequest, String side, String message) {
        this.idRequest = idRequest;
        this.side = side;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public Long getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(Long idRequest) {
        this.idRequest = idRequest;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}