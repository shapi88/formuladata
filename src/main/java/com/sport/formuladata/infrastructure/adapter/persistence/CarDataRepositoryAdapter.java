package com.sport.formuladata.infrastructure.adapter.persistence;

import com.sport.formuladata.domain.entity.CarData;
import com.sport.formuladata.domain.entity.Driver;
import com.sport.formuladata.domain.entity.Session;
import com.sport.formuladata.domain.port.outbound.CarDataRepositoryPort;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.CarDataEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.DriverEntity;
import com.sport.formuladata.infrastructure.adapter.persistence.entity.SessionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class CarDataRepositoryAdapter implements CarDataRepositoryPort {
    private static final Logger LOGGER = Logger.getLogger(CarDataRepositoryAdapter.class.getName());
    private final JpaCarDataRepository jpaRepository;

    public CarDataRepositoryAdapter(JpaCarDataRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void saveAll(List<CarData> carData) {
        LOGGER.info("Saving " + carData.size() + " car data entries");
        List<CarDataEntity> entities = carData.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<CarData> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private CarDataEntity toEntity(CarData carData) {
        CarDataEntity entity = new CarDataEntity();
        if (carData.session() != null) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionKey(carData.session().sessionKey());
            entity.setSession(sessionEntity);
        }
        if (carData.driver() != null) {
            DriverEntity driverEntity = new DriverEntity();
            driverEntity.setDriverNumber(carData.driver().driverNumber());
            entity.setDriver(driverEntity);
        }
        entity.setDate(carData.date());
        entity.setRpm(carData.rpm());
        entity.setSpeed(carData.speed());
        entity.setGear(carData.gear());
        entity.setThrottle(carData.throttle());
        entity.setBrake(carData.brake());
        entity.setDrs(carData.drs());
        return entity;
    }

    private CarData toDomain(CarDataEntity entity) {
        Session session = entity.getSession() != null
                ? new Session(entity.getSession().getSessionKey(), null, null, null, null, null)
                : null;
        Driver driver = entity.getDriver() != null
                ? new Driver(entity.getDriver().getDriverNumber(), null, null, null, null)
                : null;
        return new CarData(
                session,
                driver,
                entity.getDate(),
                entity.getRpm(),
                entity.getSpeed(),
                entity.getGear(),
                entity.getThrottle(),
                entity.getBrake(),
                entity.getDrs()
        );
    }
}