package ClinicManagement.repositories;

import ClinicManagement.entity.Account;
import ClinicManagement.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
    @Query("SELECT a FROM Session s JOIN s.attachments a " +
            "WHERE s.sessionId =:id " +
            "AND s.isActive =:isSessionActive " +
            "AND s.isActive =:isAttachmentActive")
    List<Attachment> findAttachmentsForSession(@Param("id") int sessionID,@Param("isAttachmentActive")boolean isAttachmentActive,@Param("isSessionActive")boolean isSessionActive);

    Optional<Attachment> findByAttachmentIdAndIsActive(int attachment_id, boolean isActive);
    List<Attachment>findByIsActive(boolean isActive);

}
