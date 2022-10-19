package com.betheve.betheve.auth.domain.repository;

import com.betheve.betheve.auth.Authority;
import com.betheve.betheve.member.domain.entity.MemberAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, MemberAuth> {

    Optional<Authority> findByAuthorityName(MemberAuth memberAuth);
}
