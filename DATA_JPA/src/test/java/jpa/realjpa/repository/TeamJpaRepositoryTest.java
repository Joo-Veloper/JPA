package jpa.realjpa.repository;

import jpa.realjpa.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class TeamJpaRepositoryTest {
    @Autowired
    TeamJpaRepository teamJpaRepository;

    @Test
    public void testTeam() {
        Team team = new Team("TeamA");
        Team saveTeam = teamJpaRepository.save(team);

        Team findTeam = teamJpaRepository.find(saveTeam.getId());

        assertThat(findTeam.getId()).isEqualTo(team.getId());
        assertThat(findTeam.getName()).isEqualTo(team.getName());
        assertThat(findTeam).isEqualTo(team);
    }

    @Test
    public void basicCRUD() {
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");
        teamJpaRepository.save(teamA);
        teamJpaRepository.save(teamB);

        // 단건 조회 성공
        Team findTeamA = teamJpaRepository.findById(teamA.getId()).get();
        Team findTeamB = teamJpaRepository.findById(teamB.getId()).get();

        assertThat(findTeamA).isEqualTo(teamA);
        assertThat(findTeamB).isEqualTo(teamB);

        // List 조회
        List<Team> all = teamJpaRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        // 카운트 검증
        long count = teamJpaRepository.count();
        assertThat(count).isEqualTo(2);

        // 삭제 검증
        teamJpaRepository.delete(teamA);
        teamJpaRepository.delete(teamB);

        long deleteCount = teamJpaRepository.count();
        assertThat(deleteCount).isEqualTo(0);
    }
}