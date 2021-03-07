package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.entity.MacAddresses;
import project.repository.MacAddressesRepository;

@Service
public class MacAddressesServiceImpl implements MacAddressesService {

    @Autowired
    private MacAddressesRepository macAddressesRepository;

    @Override
    public boolean existsByAddress(String address) {
        return macAddressesRepository.existsById(address);
    }

    @Override
    public void addAddress(MacAddresses address) {
        deleteAddress(address.getAddress());
        macAddressesRepository.save(address);
    }

    @Override
    public boolean deleteAddress(String address) {
        if (existsByAddress(address)) {
            macAddressesRepository.deleteById(address);
            return true;
        }
        return false;
    }
}
