package rs.ftn.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import org.lognet.springboot.grpc.GRpcService;
import rs.ftn.*;
import rs.ftn.Void;

import rs.ftn.dto.*;
import rs.ftn.service.*;

import rs.ftn.dto.BarDto;


@GRpcService
@RequiredArgsConstructor
public class FooGrpcService extends FooServiceGrpc.FooServiceImplBase {
    private final FooService fooService;

    @Override
    public void getAll(Void request, StreamObserver<Foo> responseObserver) {
        fooService.getAll().stream()
                .map(this::convert)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(Foo request, StreamObserver<Foo> responseObserver) {
        FooDto saved = fooService.save(convert(request));
        responseObserver.onNext(convert(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<Foo> responseObserver) {
        FooDto one = fooService.getOne(request.getId());
        responseObserver.onNext(convert(one));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        fooService.deleteById(request.getId());
        responseObserver.onCompleted();
    }

    private Foo convert(FooDto dto) {
        return Foo.newBuilder()
                .setId(dto.getId())
                .setData(dto.getData())
                .setBar(dto.getBar().getId())
                .build();
    }

    private FooDto convert(Foo model) {
        FooDto dto = new FooDto();
        dto.setId(model.getId());
        dto.setData(model.getData());

        BarDto barDto = new BarDto();
        barDto.setId(model.getBar());
        dto.setBar(barDto);

        return dto;
    }

}