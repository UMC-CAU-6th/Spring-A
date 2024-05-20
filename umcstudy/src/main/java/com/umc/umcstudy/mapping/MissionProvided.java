package com.umc.umcstudy.mapping;

import com.umc.umcstudy.domain.Mission;
import com.umc.umcstudy.domain.MissionAlarm;
import com.umc.umcstudy.domain.ReviewAlarm;
import com.umc.umcstudy.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MissionProvided {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @OneToMany(mappedBy = "mission")
    private List<MissionAlarm> missionAlarmList;

    @OneToMany(mappedBy = "mission")
    private List<ReviewAlarm> reviewAlarmList;
}
