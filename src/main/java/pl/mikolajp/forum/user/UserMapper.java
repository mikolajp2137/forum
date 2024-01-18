package pl.mikolajp.forum.user;

class UserMapper {
    UserDao mapDtoToDao(UserDto userDto){
        UserDao userDao = new UserDao();
        userDao.setUsername(userDto.getUsername());
        userDao.setEmail(userDto.getEmail());
        userDao.setPassword(userDto.getPassword());
        userDao.setRole_id(userDto.getRole_id());

        return userDao;
    }

    UserDao mapDtoToDao(UserDto userDto, Integer id){
        UserDao userDao = new UserDao();
        userDao.setId(id);
        userDao.setUsername(userDto.getUsername());
        userDao.setEmail(userDto.getEmail());
        userDao.setPassword(userDto.getPassword());
        userDao.setRole_id(userDto.getRole_id());

        return userDao;
    }

    UserDto mapDaoToDto(UserDao userDao){
        return new UserDto(
                userDao.getUsername(),
                userDao.getEmail(),
                userDao.getPassword(),
                userDao.getRole_id()
        );
    }
}
