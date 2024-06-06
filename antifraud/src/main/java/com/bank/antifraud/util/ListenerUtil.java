package com.bank.antifraud.util;

import com.bank.antifraud.entity.Audit;
import com.bank.antifraud.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation= Propagation.REQUIRES_NEW)
public class ListenerUtil {
    @Autowired AuditRepository auditRepository;

    public Audit findByTypeAndJsonId(String type, Long jsonId) {
        return auditRepository.findFirstByEntityTypeAndEntityJsonContainingOrderByIdDesc(type, "\"id\":" + jsonId);
    }

    public void save(Audit object) {
        auditRepository.save(object);
    }
}
