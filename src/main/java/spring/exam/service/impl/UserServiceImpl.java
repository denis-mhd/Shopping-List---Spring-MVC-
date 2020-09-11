package spring.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.exam.model.binding.UserRegisterBindingModel;
import spring.exam.model.entity.User;
import spring.exam.model.service.UserServiceModel;
import spring.exam.repository.UserRepository;
import spring.exam.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterBindingModel userRegisterBindingModel) {
        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        User user = this.modelMapper.map(userServiceModel, User.class);
        this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean checkAvailableUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        boolean isAvailable = true;
        if (user!=null) isAvailable = false;
        return isAvailable;
    }


}
