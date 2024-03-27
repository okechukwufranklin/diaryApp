package Services;

import dtos.request.LoginRequest;
import dtos.request.RegisterRequest;
import dtos.request.RemoveUserRequest;

public interface DiaryServices {
    void register(RegisterRequest request);
    void login(LoginRequest request);
    void logout(String username);

    void removeUser(RemoveUserRequest request);
    long getNumberOfUsers();

    long count();
}
