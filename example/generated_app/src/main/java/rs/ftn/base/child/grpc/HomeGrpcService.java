package rs.ftn.base.child.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import org.lognet.springboot.grpc.GRpcService;
import rs.ftn.*;
import rs.ftn.Void;

import rs.ftn.base.child.dto.*;
import rs.ftn.base.child.service.*;

import rs.ftn.dto.AddressDto;
import rs.ftn.dto.UserDto;


@GRpcService
@RequiredArgsConstructor
public class HomeGrpcService extends HomeServiceGrpc.HomeServiceImplBase {
    private final HomeService homeService;

    @Override
    public void getAll(Void request, StreamObserver<Home> responseObserver) {
        homeService.getAll().stream()
                .map(this::convert)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(Home request, StreamObserver<Home> responseObserver) {
        HomeDto saved = homeService.save(convert(request));
        responseObserver.onNext(convert(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<Home> responseObserver) {
        HomeDto one = homeService.getOne(request.getId());
        responseObserver.onNext(convert(one));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        homeService.deleteById(request.getId());
        responseObserver.onCompleted();
    }

    private Home convert(HomeDto dto) {
        return Home.newBuilder()
                .setId(dto.getId())
                .setTitle(dto.getTitle())
                .setDescription(dto.getDescription())
                .setAddress(dto.getAddress().getId())
                .build();
    }

    private HomeDto convert(Home model) {
        HomeDto dto = new HomeDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        AddressDto addressDto = new AddressDto();
        addressDto.setId(model.getAddress());
        dto.setAddress(addressDto);

        return dto;
    }

}