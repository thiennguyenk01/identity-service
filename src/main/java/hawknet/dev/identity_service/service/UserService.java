package hawknet.dev.identity_service.service;

import hawknet.dev.identity_service.dto.request.UserCreationRequest;
import hawknet.dev.identity_service.dto.request.UserUpdateRequest;
import hawknet.dev.identity_service.entity.User;
import hawknet.dev.identity_service.exception.AppException;
import hawknet.dev.identity_service.exception.ErrorCode;
import hawknet.dev.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public User updateUserById(Long id, UserUpdateRequest request){
        User user = getUserById(id);
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}
