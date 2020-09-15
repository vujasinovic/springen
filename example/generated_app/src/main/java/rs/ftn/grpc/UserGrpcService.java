package rs.ftn.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import org.lognet.springboot.grpc.GRpcService;
import rs.ftn.*;
import rs.ftn.Void;

import rs.ftn.dto.*;
import rs.ftn.service.*;

import rs.ftn.dto.AddressDto;
import rs.ftn.base.child.dto.HomeDto;


@GRpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {
    private final UserService userService;

    @Override
    public void getAll(Void request, StreamObserver<User> responseObserver) {
        userService.getAll().stream()
                .map(this::convert)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(User request, StreamObserver<User> responseObserver) {
        UserDto saved = userService.save(convert(request));
        responseObserver.onNext(convert(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<User> responseObserver) {
        UserDto one = userService.getOne(request.getId());
        responseObserver.onNext(convert(one));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        userService.deleteById(request.getId());
        responseObserver.onCompleted();
    }

    private User convert(UserDto dto) {
        return User.newBuilder()
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setUsername(dto.getUsername())
                .setPassword(dto.getPassword())
                .setAddress(dto.getAddress().getId())
                .setActive(dto.getActive())
                .setHome(dto.getHome().getId())
                .build();
    }

    private UserDto convert(User model) {
        UserDto dto = new UserDto();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setUsername(model.getUsername());
        dto.setPassword(model.getPassword());

        AddressDto addressDto = new AddressDto();
        addressDto.setId(model.getAddress());
        dto.setAddress(addressDto);

        dto.setActive(model.getActive());

        HomeDto homeDto = new HomeDto();
        homeDto.setId(model.getHome());
        dto.setHome(homeDto);

        return dto;
    }

}