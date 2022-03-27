package com.project.management.projectmanagement.biz;

import com.project.management.projectmanagement.dao.user.UserDAO;
import com.project.management.projectmanagement.model.CommonResponseWrapper;
import com.project.management.projectmanagement.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public CommonResponseWrapper<User> fetchUserById(Long id) {
        log.info("Getting user details " + id);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        Optional<User> user = null;
        try {
            user = userDAO.findById(id);
            if (user.isPresent()) {

                responseWrapper.setData(user);
            }
        } catch (Exception exception) {
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

    public CommonResponseWrapper createOrUpdateUser(User user) {
        log.info("create/update user", user);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        try {
            userDAO.save(user);
        } catch (Exception e) {
            log.error("error while creating user", e);
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }


    public CommonResponseWrapper<User> deleteById(Long id) {
        log.info("deleting user details " + id);
        CommonResponseWrapper responseWrapper = new CommonResponseWrapper(HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase());
        try {
            userDAO.deleteById(id);
        } catch (Exception exception) {
            responseWrapper = new CommonResponseWrapper(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
        return responseWrapper;
    }

}
