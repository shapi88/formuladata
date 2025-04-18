package com.sport.formuladata.domain.port.inbound;

public interface FetchOpenF1DataUseCase {
    void fetchAndStoreAllData();
    void fetchAndStoreMeetings();
    void fetchAndStoreSessions();
    void fetchAndStoreDrivers();
    void fetchAndStoreIntervals();
    void fetchAndStorePositions();
    void fetchAndStoreLaps();
}