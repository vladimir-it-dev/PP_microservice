package com.bank.account.Entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuditTest {
    private Audit audit1;
    private Audit audit2;

    @BeforeEach
    public void setUp() {
        audit1 = new Audit();
        audit1.setId(1L);
        audit1.setEntityType("AccountDetails");
        audit1.setOperationType("CREATE");
        audit1.setCreatedBy("user1");
        audit1.setModifiedBy("user1");
        audit1.setCreatedAt(LocalDateTime.now());
        audit1.setModifiedAt(LocalDateTime.now());
        audit1.setNewEntityJson("{\"id\":1,\"passportId\":123456,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1000,\"negativeBalance\":false,\"profileId\":1}");
        audit1.setEntityJson("{\"id\":1,\"passportId\":12345678,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1500,\"negativeBalance\":false,\"profileId\":1}");

        audit2 = new Audit();
        audit2.setId(2L);
        audit2.setEntityType("AccountDetails");
        audit2.setOperationType("UPDATE");
        audit2.setCreatedBy("user2");
        audit2.setModifiedBy("user2");
        audit2.setCreatedAt(LocalDateTime.now());
        audit2.setModifiedAt(LocalDateTime.now());
        audit2.setNewEntityJson("{\"id\":1,\"passportId\":123456,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1000,\"negativeBalance\":false,\"profileId\":1}");
        audit2.setEntityJson("{\"id\":1,\"passportId\":12345678,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1500,\"negativeBalance\":false,\"profileId\":1}");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(1L, audit1.getId());
        assertEquals("AccountDetails", audit1.getEntityType());
        assertEquals("CREATE", audit1.getOperationType());
        assertEquals("user1", audit1.getCreatedBy());
        assertEquals("user1", audit1.getModifiedBy());
        assertEquals("{\"id\":1,\"passportId\":123456,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1000,\"negativeBalance\":false,\"profileId\":1}", audit1.getNewEntityJson());
        assertEquals("{\"id\":1,\"passportId\":12345678,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1500,\"negativeBalance\":false,\"profileId\":1}", audit1.getEntityJson());

        assertEquals(2L, audit2.getId());
        assertEquals("AccountDetails", audit2.getEntityType());
        assertEquals("UPDATE", audit2.getOperationType());
        assertEquals("user2", audit2.getCreatedBy());
        assertEquals("user2", audit2.getModifiedBy());
        assertEquals("{\"id\":1,\"passportId\":123456,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1000,\"negativeBalance\":false,\"profileId\":1}", audit2.getNewEntityJson());
        assertEquals("{\"id\":1,\"passportId\":12345678,\"accountNumber\":1234567890,\"bankDetailsId\":1,\"money\":1500,\"negativeBalance\":false,\"profileId\":1}", audit2.getEntityJson());
    }
}