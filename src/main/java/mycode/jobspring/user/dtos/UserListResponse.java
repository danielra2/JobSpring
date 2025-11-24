package mycode.jobspring.user.dtos;

import java.util.List;

public record UserListResponse(List<UserResponse> userList) {
}