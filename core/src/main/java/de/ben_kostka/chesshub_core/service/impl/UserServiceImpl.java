package de.ben_kostka.chesshub_core.service.impl;

import de.ben_kostka.chesshub_core.api.dto.AppRole;
import de.ben_kostka.chesshub_core.api.dto.ClubAffiliation;
import de.ben_kostka.chesshub_core.api.dto.User;
import de.ben_kostka.chesshub_core.api.dto.UserSimple;
import de.ben_kostka.chesshub_core.exception.ResourceNotFoundException;
import de.ben_kostka.chesshub_core.repository.UserRepository;
import de.ben_kostka.chesshub_core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private de.ben_kostka.chesshub_core.model.User getLoggedInUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    @Override
    public List<UserSimple> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> {
            UserSimple dto = new UserSimple();
            dto.setId(userEntity.getId());
            dto.setName(userEntity.getFirstName() + " " + userEntity.getLastName());
            dto.setUserName(userEntity.getUsername());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public User getCurrentUser() {
        de.ben_kostka.chesshub_core.model.User userEntity = getLoggedInUser();
        
        User dto = new User();
        dto.setId(userEntity.getId());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        dto.setUserName(userEntity.getUsername());
        dto.setEmail(userEntity.getEmail());
        dto.setPhoneNumber(userEntity.getPhone());
        
        dto.setFideId(userEntity.getFideId());
        dto.setLichessUsername(userEntity.getLichessUsername());
        dto.setChesscomUsername(userEntity.getChesscomUsername());

        // Map roles to AppRole
        boolean isAdmin = userEntity.getRoles().stream().anyMatch(r -> r.getName().equals("ROLE_ADMIN"));
        dto.setAppRole(isAdmin ? AppRole.SUPER_ADMIN : AppRole.USER);
        
        dto.setClubIds(userEntity.getClubMemberships().stream()
                .map(cm -> cm.getClub().getId())
                .collect(Collectors.toList()));
        dto.setTeamIds(userEntity.getTeamMemberships().stream()
                .map(tm -> tm.getTeam().getId())
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public List<ClubAffiliation> getMyClubs() {
        de.ben_kostka.chesshub_core.model.User userEntity = getLoggedInUser();
        return userEntity.getClubMemberships().stream().map(cm -> {
            ClubAffiliation affiliation = new ClubAffiliation();
            affiliation.setId(cm.getClub().getId());
            affiliation.setName(cm.getClub().getName());
            affiliation.setAddress(cm.getClub().getAddress());
            affiliation.setStatus(de.ben_kostka.chesshub_core.api.dto.ClubMemberStatus.valueOf(cm.getStatus()));
            if (cm.getClub().getPresident() != null) {
                affiliation.setAdminId(cm.getClub().getPresident().getId());
                affiliation.setAdminName(cm.getClub().getPresident().getFirstName() + " " + cm.getClub().getPresident().getLastName());
            }
            return affiliation;
        }).collect(Collectors.toList());
    }
}
