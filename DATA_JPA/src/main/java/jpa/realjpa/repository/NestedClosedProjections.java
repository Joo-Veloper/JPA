package jpa.realjpa.repository;

import jpa.realjpa.entity.Team;

public interface NestedClosedProjections {
    String getUsername();

    TeamInfo getTeam();
    interface TeamInfo {
        String getName();
    }

}
