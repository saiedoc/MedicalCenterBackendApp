package ClinicManagement.service;


import ClinicManagement.entity.Attachment;
import ClinicManagement.exception.NotFoundException;
import ClinicManagement.exception.ReplicatedException;
import ClinicManagement.repositories.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    public Attachment addAttachment(Attachment attachment) {

        try {
            return repository.save(attachment);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public Attachment updateAttachment(Attachment attachment)
    {
        try {
            return repository.save(attachment);
        }
        catch (Exception ex)
        {
            throw  new ReplicatedException();
        }
    }

    public Attachment getAttachmentById(int id, boolean isActive) {
        try {
            return repository.findByAttachmentIdAndIsActive(id, isActive).get();
        } catch (NoSuchElementException ex) {
            throw new NotFoundException();
        }
    }


    public List<Attachment> getAttachmentsForSession(int sessionID, boolean isAttachmentActive, boolean isSessionActive) {
        List<Attachment> attachments = repository.findAttachmentsForSession(sessionID, isAttachmentActive, isSessionActive);
        if (attachments.isEmpty()) throw new NotFoundException();
        return attachments;
    }

    public void activeAttachment(int id, boolean isActive) {
        try{
            Attachment attachment = repository.findById(id).get();
            attachment.setActive(isActive);
            repository.save(attachment);
        }catch (NoSuchElementException ex){
            throw new NotFoundException("Can't active or non-active for not found object");
        }

    }

}
