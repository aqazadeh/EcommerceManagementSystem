package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.dto.response.UserDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.UserMapper;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.UserAddress;
import com.aqazadeh.ecommerce.repository.UserAddressRepository;
import com.aqazadeh.ecommerce.repository.UserRepository;
import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserPasswordRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserRequest;
import com.aqazadeh.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 2.02.2024
 * Time: 15:20
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserMapper userMapper;

    @Value("${pagination.limit}")
    private Integer paginationLimit;

    @Override
    public UserDto getUserById(Long id) {
        User user = findUserById(id);
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getUsers(Integer page) {
        Pageable pageable = PageRequest.of(page, paginationLimit);
        List<User> all = userRepository.findAll(pageable).stream().toList();
        return all.stream().map(userMapper::toUserDto).toList();
    }

    @Override
    public void updateUser(UpdateUserRequest request, Long userId) {
        User user = findUserById(userId);
        User convertedUser = userMapper.toUser(user, request);
        userRepository.save(convertedUser);
    }

    @Override
    public void updateUserPassword(Long userId, UpdateUserPasswordRequest request) {
        User user = findUserById(userId);
        if(!user.getPassword().equals(request.oldPassword()))
            throw new ApplicationException(ExceptionType.USER_INVALID_PASSWORD);
        if (!request.newPassword().equals(request.newRepeatedPassword()))
            throw new ApplicationException(ExceptionType.PASSWORD_NOT_MATCH);
        user.setPassword(request.newPassword());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = findUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserAddressDto getUserAddress(Long userId, Long addressId) {
        UserAddress address = findAddressById(addressId);
        if(!address.getUser().getId().equals(userId))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);

        return userMapper.toAddressDto(address);
    }

    @Override
    public List<UserAddressDto> getUserAllAddresses(Long userId) {
        List<UserAddress> addresses = findUserById(userId).getAddresses();
        return addresses.stream().map(userMapper::toAddressDto).toList();
    }

    @Override
    public void createUserAddress(Long userId, CreateUserAddressRequest request) {
        User user = findUserById(userId);
        UserAddress address = userMapper.toAddress(request);
        address.setUser(user);
        userAddressRepository.save(address);
    }

    @Override
    public void updateUserAddress(Long userId, Long addressId, UpdateUserAddressRequest request) {
        UserAddress address = findAddressById(addressId);
        if (!address.getUser().getId().equals(userId))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);

        UserAddress userMapperAddress = userMapper.toAddress(address, request);
        userAddressRepository.save(userMapperAddress);
    }

    @Override
    public void deleteUserAddress(Long userId, Long addressId) {
        UserAddress address = findAddressById(addressId);
        if (!address.getUser().getId().equals(userId))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);
        userAddressRepository.delete(address);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public UserAddress findAddressById(Long addressId) {
        return userAddressRepository.findById(addressId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND));
    }

    @Override
    public User findByConfirmationToken(String token) {
        return userRepository.findByConfirmationToken(token).orElseThrow(() -> new ApplicationException(ExceptionType.INVALID_ACTIVATION_TOKEN));
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ApplicationException(ExceptionType.USER_NOT_FOUND));
    }
}
