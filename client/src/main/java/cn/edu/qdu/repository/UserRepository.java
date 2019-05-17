package cn.edu.qdu.repository;

import cn.edu.qdu.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUsernameAndPassword(String username, String password);

    public User  findByUsername(String username);

    @Query("update User u set u.state = 1 where u.id = :id ")
    @Modifying
    void updateStatusById(@Param("id") Integer id);
}
