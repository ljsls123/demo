package demo.mapper;

import demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    int insertUser(User user);

    User selectUserByEmail(String email);

    User selectUserByUserId(int userId);

    int updateUserByUserId(User user);

    int updateUserByEmail(User user);
}
