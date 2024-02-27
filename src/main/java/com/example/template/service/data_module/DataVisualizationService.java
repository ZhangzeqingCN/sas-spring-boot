package com.example.template.service.data_module;


import com.example.template.dao.VisualizationData;
import com.example.template.dao.embed.Position;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface DataVisualizationService extends DataService {
    List<VisualizationData> findAllVisualizationDataOfDevice(Integer deviceId);

    void saveAllVisualizationDataOfDevice(Integer deviceId, List<VisualizationData> visualizationDataList);

    Optional<Position> findDevicePosition(Integer deviceId);

    List<Position> findAllDevicePositions();

    List<Position> findAllDevicePositionsOfUser(Integer userId);

    List<Position> findAllDevicePositionsOfGateway(Integer gatewayId);

    List<Position> findAllDevicePositionsOfFarm(Integer farmId);

    List<VisualizationData> findAllCurrentVisualizationData();

    List<VisualizationData> findAllCurrentVisualizationDataOfUser(Integer userId);

    List<VisualizationData> findAllCurrentVisualizationDataOfGateway(Integer gatewayId);

    List<VisualizationData> findAllCurrentVisualizationDataOfFarm(Integer farmId);

}
