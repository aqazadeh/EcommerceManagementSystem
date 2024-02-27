package com.aqazadeh.ecommerce.service.imp;

import com.aqazadeh.ecommerce.dto.request.CreateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.request.UpdateUserAddressRequest;
import com.aqazadeh.ecommerce.dto.response.UserAddressDto;
import com.aqazadeh.ecommerce.exception.ApplicationException;
import com.aqazadeh.ecommerce.exception.ExceptionType;
import com.aqazadeh.ecommerce.mapper.AddressMapper;
import com.aqazadeh.ecommerce.model.User;
import com.aqazadeh.ecommerce.model.UserAddress;
import com.aqazadeh.ecommerce.repository.UserAddressRepository;
import com.aqazadeh.ecommerce.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Rovshan Aghayev
 * Version: v1.0
 * Date: 26.02.2024
 * Time: 23:49
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserAddressServiceImpl implements UserAddressService {
    private final UserAddressRepository repository;

    private final AddressMapper addressMapper;

    @Override
    public void createUserAddress(User user, CreateUserAddressRequest request) {
        UserAddress address = addressMapper.toAddress(request);
        address.setUser(user);
        repository.save(address);
    }

    @Override
    public void updateUserAddress(User user, Long addressId, UpdateUserAddressRequest request) {
        UserAddress address = findAddressById(addressId);
        if (!address.getUser().equals(user))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);

        UserAddress userMapperAddress = addressMapper.toAddress(address, request);
        repository.save(userMapperAddress);
    }

    @Override
    public void deleteUserAddress(User user, Long addressId) {
        UserAddress address = findAddressById(addressId);
        if (!address.getUser().equals(user))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);
        repository.delete(address);
    }


    @Override
    public UserAddress findAddressById(Long addressId) {
        return repository.findById(addressId)
                .orElseThrow(() -> new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND));
    }

    @Override
    public List<UserAddressDto> getUserAllAddresses(User user) {
        List<UserAddress> addresses = user.getAddresses();
        return addresses.stream().map(addressMapper::toAddressDto).toList();
    }

    @Override
    public UserAddressDto getUserAddress(User user, Long addressId) {
        UserAddress address = findAddressById(addressId);
        if (!address.getUser().equals(user))
            throw new ApplicationException(ExceptionType.ADDRESS_NOT_FOUND);

        return addressMapper.toAddressDto(address);
    }
}
