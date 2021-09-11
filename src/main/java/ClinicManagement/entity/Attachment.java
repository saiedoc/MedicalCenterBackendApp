package ClinicManagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Attachment")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attachmentId;
    private String name;
    private String extension;

    @Lob
    private byte[] bytesFile;

    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "session_id",nullable = false)
    @JsonIgnoreProperties(value = "attachments",allowSetters = true)
    private Session session ;

    public Attachment() {
    }

    public Attachment(int attachmentId, String name, String extension, byte[] bytesFile) {
        this.attachmentId = attachmentId;
        this.name = name;
        this.extension = extension;
        this.bytesFile = bytesFile;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public byte[] getBytesFile() {
        return bytesFile;
    }

    public void setBytesFile(byte[] bytesFile) {
        this.bytesFile = bytesFile;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
