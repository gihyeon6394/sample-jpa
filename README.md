### 환경

- Java 17
- Spring Boot 3.2.0
- Maven 4.0
- H2 Database

## 테스트 코드

```java
package com.sample.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CrudTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Create")
    @Transactional
    public void create() {

        Member member = new Member();
        member.setName("Karina");
        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();

        Member saved = entityManager.find(Member.class, member.getId());
        assertThat(saved.getName(), is("Karina"));
    }

    public Member setUp() {
        Member member = new Member();
        member.setName("Karina");
        entityManager.persist(member);
        entityManager.flush();
        entityManager.clear();
        return member;
    }

    @Test
    @DisplayName("Read")
    @Transactional(readOnly = true)
    public void read() {

        long generatedId = setUp().getId();

        Member generated = entityManager.find(Member.class, generatedId);

        assertThat(generated, is(notNullValue()));
        assertThat(generated.getId(), is(generatedId));
        assertThat(generated.getName(), is("Karina"));
    }


    @Test
    @DisplayName("Update")
    @Transactional
    public void update() {


        long generatedId = setUp().getId();

        Member generated = entityManager.find(Member.class, generatedId);

        generated.setName("Karina (updated)");
        entityManager.persist(generated);
        entityManager.flush();
        entityManager.clear();

        Member updated = entityManager.find(Member.class, generated.getId());
        assertThat(updated.getName(), is("Karina (updated)"));
    }

    @Test
    @DisplayName("Delete")
    @Transactional
    public void delete() {

        long generatedId = setUp().getId();

        Member generated = entityManager.find(Member.class, generatedId);

        entityManager.remove(generated);
        entityManager.flush();
        entityManager.clear();

        Member deleted = entityManager.find(Member.class, generatedId);

        assertThat(deleted, is(nullValue()));
    }


}
```
