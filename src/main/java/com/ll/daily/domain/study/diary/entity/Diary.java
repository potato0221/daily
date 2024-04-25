package com.ll.daily.domain.study.diary.entity;

import com.ll.daily.domain.member.member.entity.Member;
import com.ll.daily.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
@Setter
@Getter
@ToString(callSuper = true)
public class Diary extends BaseEntity {

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne(optional = false)
    private Member writer;

}
