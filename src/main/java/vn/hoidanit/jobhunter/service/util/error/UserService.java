package vn.hoidanit.jobhunter.service.util.error;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User handleCreateUser(User user) {
        return this.userRepository.save(user);
    }

    public void handleDelete(long id) {
        this.userRepository.deleteById(id);
    }

    public User getUser(long id) {
        Optional<User> useroptional = this.userRepository.findById(id);
        if (useroptional.isPresent()) {
            return useroptional.get();
        }
        return null;
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public User updateUser(User putUser) {
        User user = this.getUser(putUser.getId());
        if (user != null) {
            user.setName(putUser.getName());
            user.setEmail(putUser.getEmail());
            user.setPassword(putUser.getPassword());
            user = this.userRepository.save(user);
        }
        return user;
    }

    public User handleGetUserByUsername(String user) {
        return this.userRepository.findByEmail(user);
    }
}
