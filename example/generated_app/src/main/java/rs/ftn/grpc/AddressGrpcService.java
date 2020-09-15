package rs.ftn.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import org.lognet.springboot.grpc.GRpcService;
import rs.ftn.*;
import rs.ftn.Void;

import rs.ftn.dto.*;
import rs.ftn.service.*;



@GRpcService
@RequiredArgsConstructor
public class AddressGrpcService extends AddressServiceGrpc.AddressServiceImplBase {
    private final AddressService addressService;

    @Override
    public void getAll(Void request, StreamObserver<Address> responseObserver) {
        addressService.getAll().stream()
                .map(this::convert)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(Address request, StreamObserver<Address> responseObserver) {
        AddressDto saved = addressService.save(convert(request));
        responseObserver.onNext(convert(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<Address> responseObserver) {
        AddressDto one = addressService.getOne(request.getId());
        responseObserver.onNext(convert(one));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        addressService.deleteById(request.getId());
        responseObserver.onCompleted();
    }

    private Address convert(AddressDto dto) {
        return Address.newBuilder()
                .setId(dto.getId())
                .setStreet(dto.getStreet())
                .setNumber(dto.getNumber())
                .setApartment(dto.getApartment())
                .setDescription(dto.getDescription())
                .setZipCode(dto.getZipCode())
                .build();
    }

    private AddressDto convert(Address model) {
        AddressDto dto = new AddressDto();
        dto.setId(model.getId());
        dto.setStreet(model.getStreet());
        dto.setNumber(model.getNumber());
        dto.setApartment(model.getApartment());
        dto.setDescription(model.getDescription());
        dto.setZipCode(model.getZipCode());
        return dto;
    }

}