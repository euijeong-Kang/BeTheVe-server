package com.betheve.betheve.member.domain.entity.id;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MemberId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "멤버 ID", example = "1234")
    private long memberId;

    @Schema(description = "계정 ID", example = "member1@google.com")
    private String accountId;

    public MemberId() {}

}
