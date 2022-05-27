package com.example.socialhelp.repositories;

import com.example.socialhelp.models.Token;
import com.example.socialhelp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);

    Optional<User> findByEmail(String email);

    @Query(value = "select u from User u JOIN Token t on u.id = t.user.id where t.accessToken=:tokenf")
    Optional<User> findUserByAccessToken(String tokenf);

//    User findUserByToken(String token);

    @Query(value = "select u from User u join AdditionalInfoForSpecialist aifs on u.id = aifs.user.id join Specialization s on s.id = aifs.specialization.id join Problem p on s.id = p.specialization.id where p.id =:problem_id")
    List<User> findByProblemId(Long problem_id);

    @Query(value = "select u from User u join AdditionalInfoForSpecialist aifs on u.id = aifs.user.id join Specialization s on s.id = aifs.specialization.id where s.id=:id")
    List<User> findUserBySpecializationId(Long id);


}
