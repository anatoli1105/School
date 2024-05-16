package test333.Test333.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test333.Test333.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar,Long> {

    Optional<Avatar> findAllByStudentId(Long id);
}
