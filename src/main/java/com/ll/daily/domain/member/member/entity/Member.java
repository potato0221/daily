package com.ll.daily.domain.member.member.entity;

import com.ll.daily.global.jpa.entity.BaseEntity;
import com.ll.daily.standard.util.Ut;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Setter
@Getter
@ToString(callSuper = true)
public class Member extends BaseEntity {

    private int registerCount;

    @Column(unique = true, length = 100)
    private String username;

    @Column(length = 72)
    private String password;

    @Column(unique = true, length = 50)
    private String nickname;

    @Column(unique = true)
    private String refreshToken;

    private String profileImgUrl;

    @Builder.Default
    private int dailyAl = 3;

    private int dailyAchievement;

    private String uuid;


    public String getProfileImgUrlOrDefault() {
        return Ut.str.hasLength(profileImgUrl) ? profileImgUrl : "https://placehold.co/640x640?text=O_O";
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesAsStringList()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Transient
    public List<String> getAuthoritiesAsStringList() {
        List<String> authorities = new ArrayList<>();

        authorities.add("ROLE_MEMBER");

        if (List.of("system", "admin").contains(username)) {
            authorities.add("ROLE_ADMIN");
        }

        return authorities;
    }

    public String getName() {
        return username;
    }
}

