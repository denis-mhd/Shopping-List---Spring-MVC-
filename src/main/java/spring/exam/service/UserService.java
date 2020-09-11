package spring.exam.service;

import spring.exam.model.binding.UserRegisterBindingModel;
import spring.exam.model.service.UserServiceModel;

public interface UserService {

    void register(UserRegisterBindingModel userRegisterBindingModel);

    UserServiceModel findByUsername(String username);


    boolean checkAvailableUsername(String username);
}
