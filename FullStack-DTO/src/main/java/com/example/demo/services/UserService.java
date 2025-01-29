package com.example.demo.services;


import com.example.demo.dtos.UserDTO;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    /**
     * Retrieves all users from the repository and maps them to UserDTOs.
     *
     * @return List of UserDTOs representing all users
     */
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves users by their role and maps them to UserDTOs.
     *
     * @param role the role to filter users by
     * @return List of UserDTOs representing users with the specified role
     */
    public List<UserDTO> getUsersByRole(Role role) {
        return userRepository.findByRole(role).stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a User entity to a UserDTO.
     *
     * @param user the User entity to convert
     * @return the corresponding UserDTO
     */
    public UserDTO convertEntityToDto(User user) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Converts a UserDTO to a User entity.
     *
     * @param userDTO the UserDTO to convert
     * @return the corresponding User entity
     */
    public User convertDtoToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
