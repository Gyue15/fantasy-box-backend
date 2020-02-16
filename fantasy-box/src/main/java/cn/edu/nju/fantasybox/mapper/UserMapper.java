package cn.edu.nju.fantasybox.mapper;

import cn.edu.nju.fantasybox.entity.UserEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    UserEntity select(long id);

    List<UserEntity> selectAll();

    UserEntity findByEmail(String email);

    UserEntity findByUserName(String username);

    void insertUser(UserEntity userEntity);

    void saveUser(UserEntity userEntity);

    void updateAvatar(@Param("userId") long userId, @Param("avatarUrl") String avatarUrl);

    void updateQrCode(@Param("userId") long userId, @Param("qrCodeUrl") String qrCodeUrl);

    void updatePassword(@Param("userId") long userId, @Param("password") String password);
}
