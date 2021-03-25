package demo.mapper;

import java.util.List;

import demo.model.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserCommentMapper {

    void insert(UserComment userComment);

    List<UserComment> getByItemId(int id);
}
