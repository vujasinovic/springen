package rs.ftn.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;

import org.lognet.springboot.grpc.GRpcService;
import rs.ftn.*;
import rs.ftn.Void;

import rs.ftn.dto.*;
import rs.ftn.service.*;

import rs.ftn.dto.FooDto;


@GRpcService
@RequiredArgsConstructor
public class BarGrpcService extends BarServiceGrpc.BarServiceImplBase {
    private final BarService barService;

    @Override
    public void getAll(Void request, StreamObserver<Bar> responseObserver) {
        barService.getAll().stream()
                .map(this::convert)
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }

    @Override
    public void save(Bar request, StreamObserver<Bar> responseObserver) {
        BarDto saved = barService.save(convert(request));
        responseObserver.onNext(convert(saved));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(Id request, StreamObserver<Bar> responseObserver) {
        BarDto one = barService.getOne(request.getId());
        responseObserver.onNext(convert(one));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(Id request, StreamObserver<Void> responseObserver) {
        barService.deleteById(request.getId());
        responseObserver.onCompleted();
    }

    private Bar convert(BarDto dto) {
        return Bar.newBuilder()
                .setId(dto.getId())
                .setPff(dto.getPff())
                .build();
    }

    private BarDto convert(Bar model) {
        BarDto dto = new BarDto();
        dto.setId(model.getId());
        dto.setPff(model.getPff());
        return dto;
    }

}