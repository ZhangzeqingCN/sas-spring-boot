package com.example.template.service.data_module;

import com.example.template.dao.*;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface DataService {
    void saveMessage(Message message);

    Optional<Message> findMessage(Message message);

    List<Message> findAllMessages();

    void saveReceivedSignalStrengthIndicator(ReceivedSignalStrengthIndicator receivedSignalStrengthIndicator);

    Optional<ReceivedSignalStrengthIndicator> findReceivedSignalStrengthIndicator(Integer id);

    List<ReceivedSignalStrengthIndicator> findAllReceivedSignalStrengthIndicators();

    void saveGateway(Gateway gateway);

    Optional<Gateway> findGateway(Integer gateway);

    List<Gateway> findAllGateways();

    void saveAP(AP ap);

    Optional<AP> findAP(AP ap);

    List<AP> findAllAPs();

    void saveDevice(Device device);

    Optional<Device> findDevice();
}
